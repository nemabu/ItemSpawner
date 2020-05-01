package main.commands;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSpawnerCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (args.length == 3) {
                if (player.isOp() || player.hasPermission("itemspawner.createitemspawner")) {
                    if (args[0].equalsIgnoreCase("create")) {
                        try {
                            Material isItMaterial = Material.matchMaterial(args[1]);

                            if (isItMaterial.isItem()) {

                                ItemStack customChest = new ItemStack(Material.CHEST);
                                ItemMeta itemMeta = customChest.getItemMeta();
                                itemMeta.setDisplayName("Item Spawner (" + isItMaterial.toString() + ")");
                                customChest.setItemMeta(itemMeta);

                                net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(customChest);
                                NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                                tag.setBoolean("isItemSpawnerChest", true);
                                tag.setString("material", args[1]);
                                tag.setDouble("timeInterval", Double.valueOf(args[2]));
                                nmsItem.setTag(tag);
                                customChest = CraftItemStack.asBukkitCopy(nmsItem);
                                player.getInventory().addItem(customChest);

                            }

                        } catch (Exception e) {
                            player.sendMessage("Invalid material entered");
                        }

                    } else {
                        return false;

                    }
                }


                if (!(player.isOp() || player.hasPermission(""))) {
                    player.sendMessage("You must be an operator or have permission to perform this command.");
                }
            }

            if (args.length == 0) {
                player.sendMessage("ItemSpawner is a plugin that allows for the creation of item spawning chests.");
            }


        }
        return false;
    }


}
