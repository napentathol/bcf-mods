package us.sodiumlabs.bcf.mods;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import us.sodiumlabs.bcf.mods.commands.ColorCommand;
import us.sodiumlabs.bcf.mods.commands.HelloCommand;

public class Initializer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("Initializing BCF mods...");
        final MinecraftServer minecraftServer = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
        HelloCommand.register(minecraftServer.getCommandManager());
        ColorCommand.register(minecraftServer.getCommandManager());
        System.out.println("Initialized!");
    }
}
