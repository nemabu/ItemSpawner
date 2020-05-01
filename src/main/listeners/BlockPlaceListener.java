package main.listeners;

import main.ItemSpawner;
import main.runnables.StockChestRunnable;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    ItemSpawner main;

    public BlockPlaceListener(ItemSpawner main) {
        this.main = main;
    }

    StockChestRunnable reload;


    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();
        ItemStack placedItem = event.getItemInHand();


        net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(placedItem);
        NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();


        if (compound.getBoolean("isItemSpawnerChest") == true) {


            Material chestMaterial = Material.matchMaterial(compound.getString("material"));
            double time = compound.getDouble("timeInterval");
            ItemStack itemStack = new ItemStack(chestMaterial);
            int timeInt = (int) time;

            Chest chest = (Chest) event.getBlock().getState();

            reload = new StockChestRunnable(chest, itemStack);

            reload.runTaskTimer(main, 0, 20 * timeInt);


            ArmorStand hologram1 = (ArmorStand) event.getPlayer().getWorld().spawnEntity(b.getLocation().add(0.5, 0.25, 0.5), EntityType.ARMOR_STAND);
            hologram1.setVisible(false);
            hologram1.setCustomNameVisible(true);
            hologram1.setCustomName("Owner: " + p.getName());
            hologram1.setGravity(false);

            ArmorStand hologram2 = (ArmorStand) event.getPlayer().getWorld().spawnEntity(b.getLocation().add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
            hologram2.setVisible(false);
            hologram2.setCustomNameVisible(true);
            hologram2.setCustomName("Material: " + chestMaterial.name());
            hologram2.setGravity(false);

            ArmorStand hologram3 = (ArmorStand) event.getPlayer().getWorld().spawnEntity(b.getLocation().add(0.5, -0.25, 0.5), EntityType.ARMOR_STAND);
            hologram3.setVisible(false);
            hologram3.setCustomNameVisible(true);
            hologram3.setCustomName("Time: " + time);
            hologram3.setGravity(false);


        } else {
            p.sendMessage("Failure");
        }


    }
}
