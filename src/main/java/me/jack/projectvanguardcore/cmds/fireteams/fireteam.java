package me.jack.projectvanguardcore.cmds.fireteams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class fireteam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        Player p = (Player)s;

        if(!(p instanceof Player))return false;

        if(args.length == 1) {

        }

        if(args[0].toString().toLowerCase() == "help"){
            p.sendMessage("----------[ FireTeams ]----------");
            p.sendMessage("/ft help - Displays this message");
            p.sendMessage("/ft create <name> - Create a new FireTeam");
            p.sendMessage("/ft invite <Player Name> - Invite a player to your FireTeam (Must be in same alliance)");
            p.sendMessage("/ft remove <Player Name> - Removes player from your FireTeam");
            p.sendMessage("/ft promote <Player Name> - Gives the other user more permissions find out more HERE");
            p.sendMessage("/ft disband - Disband your FireTeam");
            p.sendMessage("/ft info - View information about your FireTeam");
        }


        return false;
    }
}
