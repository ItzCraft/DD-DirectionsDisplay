package dd;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
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

                        // Проверяем, подключен ли конвейер к сортировщику
                        boolean top = isConveyorConnected(build, build.tileX(), build.tileY() + 1);
                        boolean bottom = isConveyorConnected(build, build.tileX(), build.tileY() - 1);
                        boolean left = isConveyorConnected(build, build.tileX() - 1, build.tileY());
                        boolean right = isConveyorConnected(build, build.tileX() + 1, build.tileY());

                        // Отображаем стрелки в зависимости от стороны подключения конвейера
                        if (top) Draw.rect("dd-directionsdisplay-icons-arrow", x, y + 8);
                        if (bottom) Draw.rect("dd-directionsdisplay-icons-arrow", x, y - 8);
                        if (left) Draw.rect("dd-directionsdisplay-icons-arrow", x - 8, y);
                        if (right) Draw.rect("dd-directionsdisplay-icons-arrow", x + 8, y);
                    }
                }
            });
        }});
    }

    private boolean isConveyorConnected(Building build, int tileX, int tileY) {
        Tile tile = Vars.world.tile(tileX, tileY);
        return tile != null && tile.block() instanceof Conveyor;
    }
                   }
