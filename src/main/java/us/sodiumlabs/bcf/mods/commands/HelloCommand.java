package us.sodiumlabs.bcf.mods.commands;

import com.mojang.brigadier.Command;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class HelloCommand {
    public static void register(final CommandManager commandManager) {
        commandManager.getDispatcher().register(CommandManager.literal("hello").executes(context -> {
            final ServerCommandSource serverCommandSource = context.getSource();
            final TextComponent textComponent =
                new TextComponent("Hello " + serverCommandSource.getDisplayName().getFormattedText());
            serverCommandSource.sendFeedback(textComponent, false);
            return Command.SINGLE_SUCCESS;
        }));
    }
}
