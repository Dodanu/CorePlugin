package core.main.core.CommandsStaff;

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

public class Freeze implements CommandExecutor {

    public void sendFreezeMessage(Player p) {

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f████&c█&f████"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f███&c█&6█&c█&f███ &4&lDo NOT log out!"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f██&c█&6█&0█&6█&c█&f██ &cIf you do, you will be banned!"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f██&c█&6█&0█&6█&c█&f██ &ePlease download &d&lDiscord &eand join"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f█&c█&6██&0█&6██&c█&f█ &d&nhttps://discord.gg/YkjmNKzrgJ"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f█&c█&6█████&c█&f█"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c█&6███&0█&6███&c█"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c█████████"));

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("freeze")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true || p.isOp()) {

                if(args.length == 1){

                    Player target = Bukkit.getPlayer(args[0]);
                    Profile tProfile = profiles.get(target.getUniqueId());

                    if(tProfile.getRank().getStaffRank() != true || target.isOp() == false) {

                        if(tProfile.getPlayerState() != Profile.PlayStates.Frozen) {

                            tProfile.setPlayerState(Profile.PlayStates.Frozen);

                            sendFreezeMessage(target);

                            for(Player staff : Bukkit.getOnlinePlayers()) {

                                Profile sProfile = profiles.get(staff.getUniqueId());
                                String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + target.getName() + ChatColor.AQUA + "has been forzen by " + ChatColor.WHITE + p.getName();

                                if (sProfile.getRank().getStaffRank() == true){
                                    staff.sendMessage(staffFinalMessage);
                                }

                            }

                        }
                        else {

                            tProfile.setPlayerState(Profile.PlayStates.Normal);
                            p.sendMessage(CorePrefix + ChatColor.AQUA + "You have benn unfrozen!");

                        }

                    }
                    else{

                        p.sendMessage(CorePrefix + DoToStaff);

                    }

                }
                else{

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /freeze <playerName>");

                }

            }
            else{

                p.sendMessage(CorePrefix + DenyCommand);

            }
        }

        return false;

    }
}
