package project.senior.exception;

import net.minecraft.util.EnumChatFormatting;
import project.senior.command.Command;
import project.senior.util.ChatUtil;

public class CommandException extends IllegalArgumentException {

    public CommandException(String message) {
        super(message);
        ChatUtil.addChatMessage(EnumChatFormatting.RED + message, false);
    }
}
