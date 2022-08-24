package me.jack.projectvanguardcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class misc {

    public static void Error(Player p, String reason) {
        p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!) " + ChatColor.RED + reason);
    }
}
