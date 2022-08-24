package me.jack.projectvanguardcore;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.jack.projectvanguardcore.cmds.punishments.history;
import me.jack.projectvanguardcore.events.joinEvents;
import me.jack.projectvanguardcore.utils.SQL;
import me.jack.projectvanguardcore.utils.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Projectvanguardcore extends JavaPlugin {

    public SQL sql;
    public SQLGetter data;
    private static Projectvanguardcore plugin;


    @Override
    public void onEnable() {
        // Plugin startup logic
        this.sql = new SQL();
        this.data = new SQLGetter(this);
        plugin = this;

        try {
            sql.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("Database not connected.");
        }

        if(sql.isConnected()) {
            Bukkit.getLogger().info("Database is connected.");
            data.createTable();
            this.getServer().getPluginManager().registerEvents(new joinEvents(this), this);
        }
        ProtocolManager man = ProtocolLibrary.getProtocolManager();
        Commands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if(sql.isConnected()) {
            sql.disconnect();
        }
    }


    public static Projectvanguardcore getPlugin() {
        return plugin;
    }

    public void Commands() {
        this.getCommand("history").setExecutor(new history(this));
    }
}
