package project.senior.ui.dropdown;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import project.senior.Client;
import project.senior.module.Category;
import project.senior.module.Module;
import project.senior.util.render.RenderUtil;
import project.senior.util.render.hover.HoverUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<ModuleButtons> moduleButtons;

    public Category cat;
    public boolean extended = true;
    int x, y, width, height;

    public Frame(Category cat, int x, int y, int width, int height) {
        this.cat = cat;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        moduleButtons = new ArrayList<>();

        int offset = height;
        for(Module mod : Client.INSTANCE.getMm().getModules(cat)) {
            moduleButtons.add(new ModuleButtons(mod, this, offset));
            offset += height;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = Client.INSTANCE.getMC().fontRendererObj;

        // 80 25 10 5, 30, 70
        RenderUtil.rect(x, y, width, height, Color.BLACK);
        fr.drawString(cat.name(),  x + 30, y + 4, -1);

        fr.drawString(extended ? "-" : "+", x + 70, y + 4, -1);

        if (extended) {
            for (ModuleButtons mb : moduleButtons) {
                mb.drawScreen(mouseX, mouseY, partialTicks);
            }
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {

    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(ModuleButtons mb : moduleButtons) {
            mb.mouseClicked(mouseX, mouseY, mouseButton);
        }

        if(HoverUtil.rectHovered(x, y, width, height, mouseX, mouseY) && mouseButton == 1) {
            extended = !extended;
        }
    }
}
