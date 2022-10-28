package core.main.core.CommandsBasic;

import core.main.core.Chat.StriketroughCreator;
import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Chat.StriketroughCreator.Striketrough;
import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.StaffPrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class report implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("report")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(args.length >= 2) {

                Player target = Bukkit.getPlayer(args[0]);

                StringBuilder sb = new StringBuilder();
                for(int i=1; i < args.length; i++) {
                    sb = sb.append(args[i]);
                }
                String reason = ChatColor.WHITE + sb.toString();

                for(Player staff : Bukkit.getOnlinePlayers()) {

                    Profile sProfile = profiles.get(staff.getUniqueId());
                    String staffFinalMessage = StaffPrefix  + ChatColor.AQUA + " has reported " + ChatColor.WHITE + target.getName() + ChatColor.AQUA + " for " + ChatColor.WHITE + reason;
                    String strike = Striketrough(StaffPrefix + " has reported " + target.getName() + " for " + reason + "         ");

                    if (sProfile.getRank().getStaffRank() == true){

                        staff.sendMessage(strike);
                        staff.sendMessage(staffFinalMessage);
                        staff.sendMessage(strike);

                    }

                }

            }
            else {
                String strike = Striketrough(CorePrefix + "correct usage: /report <playerName> <reason>");

                p.sendMessage(ChatColor.STRIKETHROUGH + strike);
                p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /report <playerName> <reason>");
                p.sendMessage(ChatColor.STRIKETHROUGH + strike);

            }

        }

        return false;
    }

}
