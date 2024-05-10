package project.senior.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import project.senior.Client;

public final class ChatUtil {

    public static String fix(String string) {
        return string
                .replace("&", "§");
    }

    public static void addChatMessage(final String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(fix(msg)));
    }

    public static void addChatMessage(final String msg, final boolean prefix) {
        if (prefix) {
            addChatMessage(Client.INSTANCE.getClientPrefix()+msg);
        } else {
            addChatMessage(msg);
        }
    }
}
