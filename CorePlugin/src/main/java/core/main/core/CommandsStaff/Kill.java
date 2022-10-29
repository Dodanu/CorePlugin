package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class Kill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("kill")) {

            Player p = (Player) sender;
            Profile pP = profiles.get(p.getUniqueId());

            if(pP.getRank().getStaffRank()) {

                if(args.length == 1) {

                    Player t = Bukkit.getPlayer(args[0]);
                    Profile tP = profiles.get(t.getUniqueId());

                    if(tP.getRank().getStaffRank() == false) {
                        t.setHealth(0);
                    }

                }
                else {
                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /kill <player>");
                }

            }

        }

        return false;

    }

}
