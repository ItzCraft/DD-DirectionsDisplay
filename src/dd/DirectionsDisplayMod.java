package dd;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.*;

public class DirectionsDisplayMod extends Mod {
    
    public DirectionsDisplayMod() {
        Log.info("Loaded DirectionsDisplayMod constructor.");
        
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                Core.app.post(() -> {
                    Vars.ui.showInfo(Core.bundle.get("dd.startup.message"));
                });
            });
        });
    }

    @Override
    public void loadContent() {
        Log.info("Loading some example content.");
    }
    }
