package dd;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
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
                        Draw.z(Layer.overlayUI + 1);
                        float x = build.x;
                        float y = build.y;

                        boolean top = isConveyorConnected(build, build.tileX(), build.tileY() + 1);
                        boolean bottom = isConveyorConnected(build, build.tileX(), build.tileY() - 1);
                        boolean left = isConveyorConnected(build, build.tileX() - 1, build.tileY());
                        boolean right = isConveyorConnected(build, build.tileX() + 1, build.tileY());

                        // Вывод уведомлений для проверки
                        Call.infoMessage("Checking sorter at (" + x + ", " + y + ")");
                        Call.infoMessage("Connections - Top: " + top + ", Bottom: " + bottom + ", Left: " + left + ", Right: " + right);

                        if (top) {
                            Call.infoMessage("Drawing arrow at the top of (" + x + ", " + y + ")");
                            Draw.rect("assets/icons/copper", x, y + 8);
                        }
                        if (bottom) {
                            Call.infoMessage("Drawing arrow at the bottom of (" + x + ", " + y + ")");
                            Draw.rect("assets/icons/copper", x, y - 8);
                        }
                        if (left) {
                            Call.infoMessage("Drawing arrow to the left of (" + x + ", " + y + ")");
                            Draw.rect("assets/icons/copper", x - 8, y);
                        }
                        if (right) {
                            Call.infoMessage("Drawing arrow to the right of (" + x + ", " + y + ")");
                            Draw.rect("assets/icons/copper", x + 8, y);
                        }
                    }
                }
            });
        }});
    }

    private boolean isConveyorConnected(Building build, int tileX, int tileY) {
        Call.infoMessage("Checking conveyor connection at (" + tileX + ", " + tileY + ")");
        Tile tile = Vars.world.tile(tileX, tileY);
        if (tile != null) {
            Call.infoMessage("Tile found at (" + tileX + ", " + tileY + ") with block: " + tile.block().name);
        } else {
            Call.infoMessage("No tile found at (" + tileX + ", " + tileY + ")");
        }
        return tile != null && tile.block() instanceof Conveyor;
    }
                    }
