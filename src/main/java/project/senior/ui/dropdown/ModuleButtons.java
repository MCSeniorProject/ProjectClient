package project.senior.ui.dropdown;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import project.senior.Client;
import project.senior.module.Module;
import project.senior.util.render.RenderUtil;
import project.senior.util.render.hover.HoverUtil;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ModuleButtons {
    public Module mod;
    public Frame parent;
    public int offset;
    public ModuleButtons(Module mod, Frame parent, int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = Client.INSTANCE.getMC().fontRendererObj;

        RenderUtil.rect(parent.x, offset+20, parent.width, offset, Color.BLACK);
        RenderUtil.rect(parent.x, offset+19, parent.width, 1, new Color(50, 50, 50, 255));

        RenderUtil.rect(parent.x + 70, offset + 25, 5, 5, mod.isToggled() ? Color.GREEN : Color.RED);
        fr.drawString(mod.getName(), parent.x+5, offset+25, -1);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(HoverUtil.rectHovered(parent.x, offset+20, parent.width, offset, mouseX, mouseY) && mouseButton == 0) {
            mod.toggle();
        }
    }
}
