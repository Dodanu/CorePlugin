package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.*;
import static core.main.core.Profile.ProfileManager.profiles;

public class Broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("broadcast")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true || p.isOp() == true) {

                if(args.length >= 1) {

                    StringBuilder sb = new StringBuilder();
                    sb.delete(0, sb.length());
                    for (int i = 0; i < args.length; i++) {
                        sb = sb.append(args[i]);
                    }
                    String message = "";
                    message = ChatColor.WHITE + sb.toString();

                    Bukkit.broadcastMessage(BroadcastPrefix + message);

                }
                else {

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /broadcast <message>");

                }

            }
            else{

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;
    }

}
