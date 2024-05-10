package project.senior.module.impl.visual;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import project.senior.Client;
import project.senior.event.impl.render.Event2D;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.module.ModuleInfo;
import project.senior.util.color.ColorUtil;

import java.awt.*;
import java.util.Comparator;

@ModuleInfo(
        name = "HUD",
        description = "Heads up display.",
        category = Category.Visual
)
public class HUD extends Module {
    private FontRenderer fr = null;

    public HUD() {
        fr = mc.fontRendererObj;

        setKey(Keyboard.KEY_N);
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
    private final Listener<Event2D> onUpdate = new Listener<>(event -> {
        GL11.glPushMatrix();
        GL11.glScaled(2, 2, 2);
        fr.drawString(Client.INSTANCE.getName(), 80, 5, Color.BLUE.getRGB());
        GL11.glPopMatrix();

        char[] PSChars = ("FPS:" + Minecraft.getDebugFPS() + " :BPS:" + getBPS()).toCharArray();

        int charOffset = 185;
        int charColorOffset = 0;
        for (char c : PSChars) {
            fr.drawString(String.valueOf(c) , charOffset, 30, ColorUtil.chroma(charColorOffset, 1, 1).getRGB(), false);
            charOffset += 6;
            charColorOffset -= 100;
        }

        float offset = 10;
        int colorOffset = 0;
        for (Object module : Client.INSTANCE.getMm().getModules().values().stream().sorted(Comparator.comparing(m -> fr.getStringWidth(m.toString())).reversed()).toArray()) {
            Module mod = (Module) module;
            if (!mod.isToggled()) continue;

            fr.drawString(mod.getName(), 10, offset, ColorUtil.chroma(colorOffset, 1, 1).getRGB(), false);
            offset += 10;
            colorOffset -= 300;
        }
    });

    private final String getBPS() {
        final float ticks = mc.timer.ticksPerSecond * mc.timer.timerSpeed;
        final double bps = mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.lastTickPosY, mc.thePlayer.lastTickPosZ) * ticks;
        return String.format("%.0f", bps);
    }
}
