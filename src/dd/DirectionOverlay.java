package dd;

import arc.Events;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fonts;
import arc.scene.ui.layout.Table;
import arc.util.Align;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Block;
import mindustry.ui.Cicon;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Sorter;
import mindustry.game.EventType.BlockBuildEndEvent;
import mindustry.game.EventType.Trigger;

public class DirectionOverlay {
    private static final float textDuration = 2f; // Duration for which the text is displayed (seconds)
    private static final float textScale = 1f; // Text scale
    private static final Color textColor = Color.red; // Text color

    public void load() {
        // Handle block build end event
        Events.on(BlockBuildEndEvent.class, event -> {
            Building build = event.tile.build;
            if (build != null && build.block instanceof Sorter) {
                checkAndShowDirections(build);
            }
        });

        // Handle game update event
        Events.run(Trigger.update, () -> {
            Groups.build.each(build -> {
                if (build.block instanceof Sorter) {
                    checkAndShowDirections(build);
                }
            });
        });
    }

    private void checkAndShowDirections(Building build) {
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

    private void showText(String text, float x, float y, Color color) {
        Draw.color(color);
        Fonts.def.draw(text, x, y, Align.center);
        Draw.reset();
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
