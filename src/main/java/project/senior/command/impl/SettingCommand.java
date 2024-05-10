package project.senior.command.impl;

import project.senior.Client;
import project.senior.command.Command;
import project.senior.command.CommandInfo;
import project.senior.exception.CommandException;
import project.senior.module.Module;
import project.senior.settings.Setting;
import project.senior.settings.impl.BooleanSetting;
import project.senior.settings.impl.ModeSetting;
import project.senior.settings.impl.DoubleSetting;

@CommandInfo(
        name = "setting",
        usage = "setting <module> <setting> <value>",
        description = "Change a saved setting",
        aliases = {"s", "set", "settings"}
)
public class SettingCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        Module mod = Client.INSTANCE.getMm().getModule(args[0]);

        if (args.length != 3 && args.length != 6)
            throw new CommandException("Invalid usage.");

        if (mod == null)
            throw new CommandException("No module found.");

        Setting setting = Client.INSTANCE.getSm().getSetting(mod, args[1]);

        if (setting == null) {
            throw new CommandException("No setting found.");
        }

        try {
            if (setting instanceof BooleanSetting) {
                BooleanSetting booleanSetting = (BooleanSetting) setting;
                if(!(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")))
                    throw new CommandException("Expected true or false.");

                booleanSetting.setState(args[2].equalsIgnoreCase("true"));
                return;
            }

            if (setting instanceof DoubleSetting) {
                DoubleSetting doubleSetting = (DoubleSetting) setting;

                double myDouble = Double.parseDouble(args[2]);
                doubleSetting.setVal(myDouble);
                return;
            }

            if (setting instanceof ModeSetting) {
                ModeSetting modeSetting = (ModeSetting) setting;

                if (modeSetting.getModes().stream().noneMatch(s -> s.equalsIgnoreCase(args[2])))
                    throw new CommandException("Invalid settings option.");

                modeSetting.setCurrentMode(args[2]);
            }

        } catch (NumberFormatException e) {
            throw new CommandException("Invalid number provided.");
        }
    }
}
