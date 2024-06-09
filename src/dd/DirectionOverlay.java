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
                        Draw.z(Layer.overlayUI + 1);
                        float x = build.x;
                        float y = build.y;

                        boolean top = isConveyorConnected(build, build.tileX(), build.tileY() + 1);
                        boolean bottom = isConveyorConnected(build, build.tileX(), build.tileY() - 1);
                        boolean left = isConveyorConnected(build, build.tileX() - 1, build.tileY());
                        boolean right = isConveyorConnected(build, build.tileX() + 1, build.tileY());

                        if (top) {
                            Draw.color(Pal.gray);
                            Drawf.label("Top", x, y + 8);
                            Draw.reset();
                        }
                        if (bottom) {
                            Draw.color(Pal.gray);
                            Drawf.label("Bottom", x, y - 8);
                            Draw.reset();
                        }
                        if (left) {
                            Draw.color(Pal.gray);
                            Drawf.label("Left", x - 8, y);
                            Draw.reset();
                        }
                        if (right) {
                            Draw.color(Pal.gray);
                            Drawf.label("Right", x + 8, y);
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
            return block instanceof Conveyor;
        } else {
            return false;
        }
    }
}
