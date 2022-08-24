package me.jack.projectvanguardcore.events;

import me.jack.projectvanguardcore.Projectvanguardcore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinEvents implements Listener {

    private final Projectvanguardcore pl;
    public joinEvents(Projectvanguardcore pl) {

        this.pl = pl;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        Player p = (Player)e.getPlayer();

        e.setJoinMessage("hi");

        pl.data.createPlayer(p);
    }
}
