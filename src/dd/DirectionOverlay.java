package dd;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.util.Align;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.distribution.*;

public class DirectionOverlay {
    public void load() {
        Core.scene.add(new Table() {{
            update(() -> {
                for (Building build : Groups.build) {
                    if (build.block instanceof Sorter) {
                        Draw.z(Layer.effect);
                        float x = build.x;
                        float y = build.y;

                        boolean top = isConveyorConnected(build, build.tileX(), build.tileY() + 1);
                        boolean bottom = isConveyorConnected(build, build.tileX(), build.tileY() - 1);
                        boolean left = isConveyorConnected(build, build.tileX() - 1, build.tileY());
                        boolean right = isConveyorConnected(build, build.tileX() + 1, build.tileY());

                        Call.infoMessage("Checking sorter at (" + x + ", " + y + ")");
                        Call.infoMessage("Connections - Top: " + top + ", Bottom: " + bottom + ", Left: " + left + ", Right: " + right);

                        if (top) {
                            Call.infoMessage("Drawing text at the top of (" + x + ", " + y + ")");
                            Draw.color(Pal.gray);
                            Fonts.def.draw("Top", x, y + 8, Align.center);
                            Draw.reset();
                        }
                        if (bottom) {
                            Call.infoMessage("Drawing text at the bottom of (" + x + ", " + y + ")");
                            Draw.color(Pal.gray);
                            Fonts.def.draw("Bottom", x, y - 8, Align.center);
                            Draw.reset();
                        }
                        if (left) {
                            Call.infoMessage("Drawing text to the left of (" + x + ", " + y + ")");
                            Draw.color(Pal.gray);
                            Fonts.def.draw("Left", x - 8, y, Align.center);
                            Draw.reset();
                        }
                        if (right) {
                            Call.infoMessage("Drawing text to the right of (" + x + ", " + y + ")");
                            Draw.color(Pal.gray);
                            Fonts.def.draw("Right", x + 8, y, Align.center);
                            Draw.reset();
                        }
                    }
                }
            });
        }});
    }

    private boolean isConveyorConnected(Building build, int tileX, int tileY) {
        Tile tile = Vars.world.tile(tileX, tileY);
        if (tile != null) {
            Block block = tile.block();
            Call.infoMessage("Tile found at (" + tileX + ", " + tileY + ") with block: " + block.name);
            return block instanceof Conveyor;
        } else {
            Call.infoMessage("No tile found at (" + tileX + ", " + tileY + ")");
            return false;
        }
    }
                                                             }
