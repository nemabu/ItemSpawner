package main;

import main.commands.ItemSpawnerCmd;
import main.listeners.BlockBreakListener;
import main.listeners.BlockPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemSpawner extends JavaPlugin {

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public void onEnable() {
        this.getCommand("itemspawner").setExecutor(new ItemSpawnerCmd());
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {

    }

}
