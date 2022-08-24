package me.jack.projectvanguardcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Pages {

    public void paginate(CommandSender sender, ArrayList<String> list, int page, int countAll)
    {
        int contentLinesPerPage = 5;
        int totalPageCount = 1;

        if((list.size() % contentLinesPerPage) == 0)
        {
            if(list.size() > 0)
            {
                totalPageCount = list.size() / contentLinesPerPage;
            }
        }
        else
        {
            totalPageCount = (list.size() / contentLinesPerPage) + 1;
        }

        if(page <= totalPageCount)
        {
            String paginatefirstline =  String.valueOf(page) +  "/" + String.valueOf(totalPageCount);

            sender.sendMessage(paginatefirstline);
            //beginline

            if(list.isEmpty())
            {
                sender.sendMessage(ChatColor.WHITE + "The list is empty!");
            }
            else
            {
                int i = 0, k = 0;
                page--;

                for (String entry : list)
                {
                    k++;
                    if ((((page * contentLinesPerPage) + i + 1) == k) && (k != ((page * contentLinesPerPage) + contentLinesPerPage + 1)))
                    {
                        i++;
                        sender.sendMessage(entry);
                    }
                }
            }
            //endline
        }
        else
        {
            sender.sendMessage(ChatColor.YELLOW + "There are only " + ChatColor.WHITE + totalPageCount + ChatColor.YELLOW + " pages!");
        }
    }
    
}
