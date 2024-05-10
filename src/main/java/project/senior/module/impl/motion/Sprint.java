package project.senior.module.impl.motion;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import project.senior.event.impl.update.EventUpdate;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.module.ModuleInfo;

@ModuleInfo(
        name = "Sprint",
        description = "Faster.",
        category = Category.Motion
)
public final class Sprint extends Module {

    public Sprint() {
        setKey(Keyboard.KEY_B);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(event -> {
        // if (mc.thePlayer.isCollidedHorizontally) return;
        // if (mc.thePlayer.moveForward <= 0) return;
        // if (mc.thePlayer.isUsingItem()) return;
        // if (mc.thePlayer.isSneaking()) return;

        // mc.thePlayer.setSprinting(true);
        mc.gameSettings.keyBindSprint.setPressed(true);
    });

}
