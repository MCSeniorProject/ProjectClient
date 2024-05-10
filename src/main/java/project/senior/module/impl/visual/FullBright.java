package project.senior.module.impl.visual;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import project.senior.event.impl.update.EventUpdate;
import project.senior.module.ModuleInfo;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.settings.impl.ModeSetting;
import project.senior.settings.impl.DoubleSetting;

@ModuleInfo(
        name = "FullBright",
        description = "Lighten up your day.",
        category = Category.Visual
)
public final class FullBright extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Gamma", "NightVision");
    private final DoubleSetting gamma = new DoubleSetting("Brightness", 3, 1, 3, 0.1);

    private float oldGamma;

    public FullBright() {
        addSettings(mode, gamma);
        setKey(Keyboard.KEY_M);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        oldGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        mc.gameSettings.gammaSetting = oldGamma;
        mc.thePlayer.removePotionEffect(Potion.nightVision.id);
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(event -> {
        switch(mode.getCurrentMode()) {
            case "Gamma":
                mc.thePlayer.removePotionEffect(Potion.nightVision.id);
                mc.gameSettings.gammaSetting = (float) gamma.getVal();
                break;
            case "NightVision":
                mc.gameSettings.gammaSetting = oldGamma;
                mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE, 0));
                break;
        }
    });
}
