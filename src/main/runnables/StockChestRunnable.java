package main.runnables;

import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class StockChestRunnable extends BukkitRunnable {

    Chest chest;
    ItemStack itemStack;

    public StockChestRunnable(Chest chest, ItemStack itemStack) {
        this.chest = chest;
        this.itemStack = itemStack;
    }

    @Override
    public void run() {
        chest.getInventory().addItem(itemStack);
    }
}
