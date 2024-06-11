package dd;

import arc.Events;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.util.Align;
import arc.util.Time;
import mindustry.Vars;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.*;
import static mindustry.game.EventType.*;

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
        // Schedule the text to be displayed for a certain duration
        Time.run(textDuration, () -> {
            Draw.color(color);
            Fonts.def.draw(text, x, y, textScale, Align.center, false);
            Draw.reset();
        });
    }

    private boolean isConveyorConnected(Building build, int tileX, int tileY) {
    Tile tile = Vars.world.tile(tileX, tileY);
    if (tile != null) {
        Block block = tile.block();
        if (block instanceof Conveyor || block instanceof Junction) {
            return true;
        }
    }
    return false;
    }
}
