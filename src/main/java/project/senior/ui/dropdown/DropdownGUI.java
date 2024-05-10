package project.senior.ui.dropdown;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import project.senior.Client;
import project.senior.module.Category;
import project.senior.module.impl.visual.ClickGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DropdownGUI extends GuiScreen {

    private final List<Frame> frames;

    int offset = 20;
    public DropdownGUI() {
        frames = new ArrayList<>();
        for(Category cat : Category.values()) {
            frames.add(new Frame(cat, offset, 20, 85, 15));
            offset += 90;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Frame frame : frames) {
            frame.drawScreen(mouseX, mouseY, partialTicks);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for(Frame frame : frames) {
            frame.keyTyped(typedChar, keyCode);
        }
        if (keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_RSHIFT) {
            Client.INSTANCE.getMm().getModule(ClickGUI.class).toggle();
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
