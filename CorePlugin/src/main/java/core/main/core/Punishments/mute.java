package core.main.core.Punishments;

import core.main.core.Chat.Chat;
import core.main.core.Core;
import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Chat.StriketroughCreator.Striketrough;
import static core.main.core.Core.*;
import static core.main.core.Profile.ProfileManager.profiles;

public class mute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Profile pProfile = profiles.get(p.getUniqueId());

        if(command.getName().equalsIgnoreCase("mute")) {

            if(pProfile.getRank().getStaffRank() == true || p.isOp() == true) {

                if(args.length >= 1) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Profile tProfile = profiles.get(target.getUniqueId());

                    if(target.isOp() == false || tProfile.getRank().getStaffRank() == false) {

                        StringBuilder sb = new StringBuilder();
                        if (args.length > 1) {

                            for (int i = 1; i < args.length; i++) {
                                sb = sb.append(args[i]);
                            }

                        }

                        String reason = sb.toString();
                        String message = CorePrefix + "You have been muted by: " + p.getName() + "for: " + reason;
                        String strike = Striketrough(message);

                        tProfile.setMuted(true);

                        target.sendMessage(CorePrefix + ChatColor.RED + ChatColor.RED + "You have been muted by: " + ChatColor.YELLOW + p.getName() + ChatColor.RED + "for: " + ChatColor.YELLOW + reason);
                    }
                    else {

                        p.sendMessage(CorePrefix + DoToStaff);

                    }

                }
                else {
                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /mute <playerName> (<reason>)");
                }

            }

        }
        else{
           p.sendMessage(CorePrefix + DenyCommand);
        }

        return false;

    }

}
