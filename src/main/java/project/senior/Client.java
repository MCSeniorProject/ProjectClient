package project.senior;

import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import project.senior.command.CommandManager;
import project.senior.event.impl.input.EventKey;
import project.senior.module.ModuleManager;
import project.senior.settings.SettingManager;
import project.senior.ui.dropdown.DropdownGUI;

@Getter
public enum Client implements Subscriber {
    INSTANCE;

    private final Minecraft MC = Minecraft.getMinecraft();


    public static final EventBus BUS = EventManager.builder()
            .setName("root/Client")
            .setSuperListeners()
            .build();

    private String
            name = "Project Client",
            version = "v0.01a",
            commandPrefix = "!",
            clientPrefix = "[Client] ",
            authors = "Ethan";

    private ModuleManager mm;
    private CommandManager cm;
    private SettingManager sm;

    private DropdownGUI gui;

    public final void init() {
        BUS.subscribe(this);
        Display.setTitle(name + " - " + version);

        mm = new ModuleManager();
        cm = new CommandManager();
        sm = new SettingManager();

        gui = new DropdownGUI();
    }

    public final void shutdown() {
        BUS.unsubscribe(this);
    }

    @Subscribe
    private final Listener<EventKey> listener = new Listener<>(event -> {
        if (this.mm != null) {
            mm.getModules().values().forEach(module -> {
                if (module.getKey() == event.getKey()) {
                    module.toggle();
                }
            });
        }
    });

}
