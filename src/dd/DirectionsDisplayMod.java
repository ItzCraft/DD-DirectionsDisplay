package dd;

import arc.Core;
import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class DirectionsDisplayMod extends Mod {

    public DirectionsDisplayMod() {
        Log.info("Loaded DirectionsDisplayMod constructor.");
        
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                Core.app.post(() -> {
                    BaseDialog dialog = new BaseDialog("info");
                    dialog.cont.add(Core.bundle.get("dd.startup.message")).row();
                    dialog.buttons.defaults().size(210f, 64f);
                    dialog.setFillParent(true);
                    dialog.buttons.addButton("OK", dialog::hide);
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
