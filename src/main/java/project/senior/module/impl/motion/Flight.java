package project.senior.module.impl.motion;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import project.senior.event.impl.update.EventUpdate;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.module.ModuleInfo;

@ModuleInfo(
        name = "Flight",
        description = "Fly around the world.",
        category = Category.Motion
)
public final class Flight extends Module {

    public Flight() {
        setKey(Keyboard.KEY_K);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.capabilities.isFlying = false;
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(event -> {
        mc.thePlayer.capabilities.isFlying = true;
    });

}
