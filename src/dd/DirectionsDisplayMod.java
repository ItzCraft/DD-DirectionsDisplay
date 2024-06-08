package dd;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class DirectionsDisplayMod extends Mod {

    public DirectionsDisplayMod() {
        Log.info("Loaded DirectionsDisplayMod constructor.");

        // Add post-load initialization code here
        Events.on(ClientLoadEvent.class, e -> {
            // Wait a bit before showing the message
            Time.runTask(10f, () -> {
                Core.app.post(() -> {
                    // Show an info dialog with the localized message
                    BaseDialog dialog = new BaseDialog("info");
                    dialog.cont.add(Core.bundle.get("dd.startup.message")).row();
                    dialog.buttons.defaults().size(210f, 64f);
                    dialog.setFillParent(true);
                    dialog.cont.button("OK", dialog::hide);
                    dialog.show();
                });
            });
        });
    }

    @Override
    public void loadContent() {
        Log.info("Loading some example content.");
    }
            }
