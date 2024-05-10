package project.senior.command.impl;

import net.minecraft.util.EnumChatFormatting;
import project.senior.Client;
import project.senior.command.Command;
import project.senior.command.CommandInfo;
import project.senior.exception.CommandException;
import project.senior.util.ChatUtil;

@CommandInfo(
        name = "help",
        usage = "help <command>",
        description = "Get information on all the commands.",
        aliases = {"h", "?"}
)
public final class HelpCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length > 0) {
            ChatUtil.addChatMessage("\n");
            final Command command = Client.INSTANCE.getCm().getCommand(args[0])
                    .orElseThrow(() ->
                            new CommandException(String.format(EnumChatFormatting.RED + "Error: Command not found.")));
            return;
        }

        ChatUtil.addChatMessage("\n");
        Client.INSTANCE.getCm()
                .getCommands()
                .values()
                .stream()
                // .filter(command -> !(command instanceof HelpCommand))
                .forEach(command -> ChatUtil.addChatMessage(
                        String.format(EnumChatFormatting.YELLOW + "%s " + EnumChatFormatting.WHITE + "- " + EnumChatFormatting.GRAY + "%s", " " + command.getName(), command.getDescription() + "\n")
                , true));

        ChatUtil.addChatMessage("\n");
    }
}
