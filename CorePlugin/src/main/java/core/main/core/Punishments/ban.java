package core.main.core.Punishments;

import core.main.core.Core;
import core.main.core.Profile.Profile;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Chat.StriketroughCreator.Striketrough;
import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;
import static org.bukkit.Bukkit.getServer;

public class ban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("ban")) {

            Player p = (Player) sender;
            Profile profile = profiles.get(p.getUniqueId());

            if(profile.getRank().getStaffRank() == true || p.isOp()) {

                if(args.length >= 2) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Profile targetProfile = profiles.get(target.getUniqueId());

                    if(targetProfile.getRank().staffRank == false || p.isOp() == false) {

                        StringBuilder sb = new StringBuilder();
                        for(int i=1; i < args.length; i++){
                            sb = sb.append(args[0]);
                        }

                        String reason = sb.toString();

                        targetProfile.setBanned(true);
                        targetProfile.setLastBanReason(ChatColor.RED + reason);
                        target.kickPlayer(ChatColor.RED + "You have been permanently banned form the CoreNetwork for " + reason);

                        String message = ChatColor.YELLOW + target.getName() + ChatColor.RED + " has been banned by " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " for " + reason;
                        String strike = Striketrough(target.getName() + " has been banned by " + p.getName() + " for " + reason + "        ");
                        Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + strike);
                        Bukkit.broadcastMessage(message);
                        Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + message);
                    }
                    else {

                        p.sendMessage(CorePrefix + DenyCommand);

                    }


                }
                else {

                    p.sendMessage(CorePrefix + ChatColor.YELLOW +"Correct usage: /ban <playerName> <reason>");

                }

            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }

}
