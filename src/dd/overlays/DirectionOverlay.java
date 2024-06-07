package dd.overlays;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;
import mindustry.Vars;

public class DirectionOverlay {
    public void load() {
        Core.scene.add(new Table() {{
            update(() -> {
                for (Building build : Vars.world.build) {
                    if (build instanceof Sorter sorter) {
                        Draw.z(Layer.overlayUI + 1);
                        float x = build.x;
                        float y = build.y;

                        // Проверяем стороны подключения конвейера
                        boolean top = isConveyorConnected(sorter, sorter.tile.x, sorter.tile.y + 1);
                        boolean bottom = isConveyorConnected(sorter, sorter.tile.x, sorter.tile.y - 1);
                        boolean left = isConveyorConnected(sorter, sorter.tile.x - 1, sorter.tile.y);
                        boolean right = isConveyorConnected(sorter, sorter.tile.x + 1, sorter.tile.y);

                        // Рисуем стрелки в зависимости от стороны подключения
                        if (top) {
                            Draw.rect("dd-directionsdisplay-icons-arrow", x, y - 8);
                            if (sorter.config() != null) {
                                Draw.rect(sorter.config().icon(Cicon.full), x, y - 16);
                            }
                        }
                        if (bottom) {
                            Draw.rect("dd-directionsdisplay-icons-arrow", x, y + 8, 0, 180); // Поворачиваем стрелку на 180 градусов
                            if (sorter.config() != null) {
                                Draw.rect(sorter.config().icon(Cicon.full), x, y + 16);
                            }
                        }
                        if (left) {
                            Draw.rect("dd-directionsdisplay-icons-arrow", x + 8, y, 0, 90); // Поворачиваем стрелку на 90 градусов
                            if (sorter.config() != null) {
                                Draw.rect(sorter.config().icon(Cicon.full), x + 16, y);
                            }
                        }
                        if (right) {
                            Draw.rect("dd-directionsdisplay-icons-arrow", x - 8, y, 0, -90); // Поворачиваем стрелку на -90 градусов
                            if (sorter.config() != null) {
                                Draw.rect(sorter.config().icon(Cicon.full), x - 16, y);
                            }
                        }
                    }
                }
            });
        }});
    }

    // Метод для проверки, подключен ли конвейер к сортировщику с заданной стороны
    private boolean isConveyorConnected(Building sorter, int tileX, int tileY) {
        Building nearby = Vars.world.build(tileX, tileY);
        return nearby != null && (nearby.block() instanceof Conveyor || nearby.block() instanceof Router || nearby.block() instanceof Junction);
    }
                          }
