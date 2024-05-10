package project.senior.module.impl.player;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import project.senior.event.impl.update.EventUpdate;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.module.ModuleInfo;

@ModuleInfo(
        name = "FastPlace",
        description = "Place blocks extremely fast.",
        category = Category.Player
)
public final class FastPlace extends Module {

    public FastPlace() {
        setKey(Keyboard.KEY_PERIOD);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.rightClickDelayTimer = 6;
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(event -> {
        mc.rightClickDelayTimer = 0;
    });

}
