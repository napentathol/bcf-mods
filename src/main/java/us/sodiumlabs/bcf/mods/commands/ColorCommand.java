package us.sodiumlabs.bcf.mods.commands;

import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormat;
import net.minecraft.command.arguments.ColorArgumentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Optional;

public class ColorCommand {
    public static void register(final CommandManager commandManager) {
        commandManager.getDispatcher().register(CommandManager.literal("color")
            .then(CommandManager.argument("value", ColorArgumentType.create()).executes(context -> {
                final ServerCommandSource serverCommandSource = context.getSource();
                final ChatFormat chatFormat = ColorArgumentType.getColor(context, "value");
                final MinecraftServer minecraftServer = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
                final Scoreboard scoreboard = minecraftServer.getScoreboard();

                final Team team = Optional.ofNullable(scoreboard.getTeam(chatFormat.getName())).orElseGet(() -> {
                    final Team out = scoreboard.addTeam(chatFormat.getName());
                    out.setColor(chatFormat);
                    return out;
                });

                final String playerName = serverCommandSource.getPlayer().getName().getString();
                scoreboard.addPlayerToTeam(playerName, team);

                final Component component =
                    new TextComponent("Set player color: " )
                        .append(new TextComponent(playerName).applyFormat(chatFormat));
                serverCommandSource.sendFeedback(component, false);
                return Command.SINGLE_SUCCESS;
            })));
    }
}
