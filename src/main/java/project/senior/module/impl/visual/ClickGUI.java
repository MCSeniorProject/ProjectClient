package project.senior.module.impl.visual;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.potion.Potion;
import org.lwjgl.input.Keyboard;
import project.senior.Client;
import project.senior.event.impl.render.Event2D;
import project.senior.event.impl.update.EventUpdate;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.module.ModuleInfo;
import project.senior.ui.dropdown.DropdownGUI;

@ModuleInfo(
        name = "ClickGUI",
        description = "Graphical user interface.",
        category = Category.Visual
)
public final class ClickGUI extends Module {

    public ClickGUI() {
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }



    @Override
    public void onDisable() {
        mc.displayGuiScreen(null);
        super.onDisable();
    }

    @Subscribe
    private final Listener<Event2D> on2D = new Listener<>(event -> {
        mc.displayGuiScreen(Client.INSTANCE.getGui());
    });
}
