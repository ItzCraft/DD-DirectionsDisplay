package dd;

import mindustry.mod.*;
import dd.overlays.*;

public class DirectionsDisplayMod extends Mod {
    @Override
    public void init() {
        new DirectionOverlay().load();
    }
}
