package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;

public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warp")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if (pProfile.getRank().getStaffRank() == true) {

                if (args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);

                    target.teleport(p.getLocation());

                } else {

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /warp <playerName>");

                }

            } else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }

}
