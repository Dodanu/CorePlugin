package core.main.core.Punishments;

import core.main.core.Core;
import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.*;
import static core.main.core.Profile.ProfileManager.profiles;

/**
 " "
 "&f████&c█&f████"
 "&f███&c█&6█&c█&f███"
 "&f██&c█&6█&0█&6█&c█&f██"
 "&f██&c█&6█&0█&6█&c█&f██ &4&lYou have been warned by %STAFF%&4&l."
 "&f█&c█&6██&0█&6██&c█&f█"
 "&f█&c█&6█████&c█&f█"
 "&c█&6███&0█&6███&c█"
 "&c█████████"
 " "
 */

public class warn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warn")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true || p.isOp() == true) {

                if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Profile tProfile = profiles.get(target.getUniqueId());

                    tProfile.setWarns(tProfile.warns++);

                    target.sendMessage(ChatColor.translateAlternateColorCodes( '&'," \n" + "&f████&c█&f████\n" + "&f███&c█&6█&c█&f███\n" + "&f██&c█&6█&0█&6█&c█&f██\n" + "&f██&c█&6█&0█&6█&c█&f██ &4&lYou have been warned by " + ChatColor.RED + p.getName() + "\n" + "&f█&c█&6██&0█&6██&c█&f█\n" + "&f█&c█&6█████&c█&f█\n" + "&c█&6███&0█&6███&c█\n" + " \n"));

                    if(tProfile.getWarns() == 3) {

                        String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + p.getName() + ChatColor.AQUA + " has 3 warns!";

                        for(Player staff : Bukkit.getOnlinePlayers()) {

                            Profile sProfile = profiles.get(staff.getUniqueId());

                            if (sProfile.getRank().getStaffRank() == true){

                                staff.sendMessage(staffFinalMessage);
                            }

                        }

                    }

                }
                else{

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /warn <playerName>");

                }

            }
            else{

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }

}
