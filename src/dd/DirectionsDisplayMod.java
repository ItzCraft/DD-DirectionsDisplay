package dd;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class DirectionsDisplayMod extends Mod {

    public DirectionsDisplayMod() {
        Log.info("Loaded DirectionsDisplayMod loader.");
        new DirectionOverlay();
        
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                Core.app.post(() -> {
                    BaseDialog dialog = new BaseDialog("WARNING");
                    dialog.cont.add(Core.bundle.get("directd.startup.message")).row();
                    dialog.buttons.defaults().size(190f, 75f);
                    dialog.setFillParent(true);
                    dialog.cont.button("OK", dialog::hide);
                    dialog.show();
                });
            });
        });
    }
        }
