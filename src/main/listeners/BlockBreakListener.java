package main.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    UUID hologramId1;
    UUID hologramId2;
    UUID hologramId3;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();

        Material blockMaterial = b.getType();
        ItemStack brokenItem = new ItemStack(blockMaterial);

        if (event.getBlock().getType().equals(Material.CHEST)) {
            p.sendMessage(ChatColor.RED + "Chest broken!");

            Collection<Entity> entities = event.getBlock().getWorld().getNearbyEntities(b.getLocation(), 1, 1, 1);
            for (Entity e : entities) {
                e.remove();

            }

        }

    }
}
