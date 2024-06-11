package dd;

import arc.*;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.util.Align;
import arc.util.Time;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.distribution.*;

public class DirectionOverlay {
    private static final float textDuration = 2f;
    private static final float textScale = 1f;
    private static final Color textColor = Color.red;

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

                        if (top) {
                            showText("Top", x, y + 8, textColor);
                        }
                        if (bottom) {
                            showText("Bottom", x, y - 8, textColor);
                        }
                        if (left) {
                            showText("Left", x - 8, y, textColor);
                        }
                        if (right) {
                            showText("Right", x + 8, y, textColor);
                        }
                    }
                }
            });
        }});
    }

    private void showText(String text, float x, float y, Color color) {
        Time.runTask(textDuration, () -> {
            Draw.color(color);
            Fonts.def.draw(text, x, y, Align.center);
            Draw.reset();
        });
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
