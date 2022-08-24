package me.jack.projectvanguardcore.cmds.punishments;

import me.jack.projectvanguardcore.Projectvanguardcore;
import me.jack.projectvanguardcore.utils.misc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class history implements CommandExecutor {

    private Projectvanguardcore pl;

    public history(Projectvanguardcore projectvanguardcore) {
        pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        Player p2 = (Player)Bukkit.getServer().getPlayer(args[0]);
        if(!(sender instanceof Player))return false;
        if(!p.hasPermission("staff.history"))return false;

        if(args.length == 0 || args.length == 1) {
            misc.Error(p, "Correct Usage: /history <name>");
            return false;
        }
        if(pl.data.exists(p2.getUniqueId())) {
            misc.Error(p, "Player doesn't exist.");
            return false;
        }

        Inventory main = Bukkit.getServer().createInventory(null, 26, "§9Player Punish Profile");

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);

        ItemMeta headm = head.getItemMeta();

        ArrayList HeadLore = new ArrayList();

        HeadLore.add(ChatColor.GRAY + "Rank: " + pl.data.getRankColour(p2.getUniqueId()) + "" + pl.data.getRank(p2.getUniqueId()));

        headm.setLore(HeadLore);

        headm.setDisplayName(Projectvanguardcore.getPlugin().data.getRank(p2.getUniqueId()) + args[0]);





        main.setItem(9, head);




        p.openInventory(main);






        return false;
    }
}
