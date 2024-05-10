package project.senior.command;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;
import project.senior.Client;
import project.senior.exception.CommandException;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Command {

    protected static final Minecraft mc = Client.INSTANCE.getMC();

    private final String name;
    private final String description;
    private final String usage;
    private final List<String> aliases;

    public Command() {
        final CommandInfo info = this.getClass().getAnnotation(CommandInfo.class);
        Validate.notNull(info, "CONFUSED ANNOTATION EXCEPTION");

        this.name = info.name();
        this.description = info.description();
        this.usage = info.usage();
        this.aliases = Arrays.asList(info.aliases());
    }

    public boolean isAlias(final String str) {
        return aliases.stream()
                .anyMatch(s -> s.equalsIgnoreCase(str));
    }

    public abstract void execute(String... args) throws CommandException;
}
