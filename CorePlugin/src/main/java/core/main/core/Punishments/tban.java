package core.main.core.Punishments;

import core.main.core.Profile.Profile;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static core.main.core.Chat.StriketroughCreator.Striketrough;
import static core.main.core.Core.*;
import static core.main.core.Profile.ProfileManager.profiles;
import static org.bukkit.Bukkit.getServer;

public class tban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("tban")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true) {

                if(args.length >= 3) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Profile tProfile = profiles.get(target.getUniqueId());

                    Long time = Long.parseLong(args[1]);
                    Long finalTime = TimeUnit.DAYS.toMillis(time);

                    Long today = System.currentTimeMillis();
                    Long until = today + time;
                    Date untilDate = new Date(until);
                    String sUntilDate = untilDate.toString();

                    if(tProfile.getRank().getStaffRank() == false || target.isOp() == false) {

                        StringBuilder sb = new StringBuilder();
                        for(int i=2; i < args.length; i++){
                            sb = sb.append(args[0]);
                        }

                        String reason = sb.toString();

                        tProfile.setBanned(true);
                        tProfile.setLastBanReason(ChatColor.RED + reason);
                        tProfile.setBanTime(until);
                        target.kickPlayer(ChatColor.RED + "You have been banned form the CoreNetwork for " + reason + " until " + ChatColor.YELLOW + sUntilDate);

                        String message = ChatColor.YELLOW + target.getName() + ChatColor.RED + " has been banned by " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " for " + reason;
                        String strike = Striketrough(target.getName() + " has been banned by " + p.getName() + " for " + reason + "        ");
                        Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + strike);
                        Bukkit.broadcastMessage(message);
                        Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + message);


                    }
                    else {

                        p.sendMessage(CorePrefix + DoToStaff);

                    }


                }
                else {

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /tban <playerName> <timeInDays> <Reason>");

                }
            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }
}
