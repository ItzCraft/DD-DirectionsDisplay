package dd;

import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.production.*;
import mindustry.ctype.*;
import mindustry.world.blocks.distribution.*;

public class DirectionOverlay {
    public void load() {
        Core.scene.add(new Table() {{
            update(() -> {
                for (Building build : Vars.world.build) {
                    if (build instanceof Sorter) {
                        Sorter sorter = (Sorter) build;
                        Draw.z(Layer.overlayUI + 1);
                        float x = build.x;
                        float y = build.y;

                        if (sorter.getConfig() != null) {
                            Draw.rect("dd-directionsdisplay-icons-arrow", x, y - 8);
                            Draw.rect(sorter.getConfig().icon(Cicon.full), x, y - 16);
                        }
                        Draw.rect("dd-directionsdisplay-icons-arrow", x - 8, y);
                        Draw.rect("dd-directionsdisplay-icons-arrow", x + 8, y);
                    }
                }
            });
        }});
    }

    private boolean isConveyorConnected(Sorter sorter, int tileX, int tileY) {
        Building nearby = Vars.world.build(tileX, tileY);
        return nearby != null && nearby.block() instanceof Conveyor;
    }
}
