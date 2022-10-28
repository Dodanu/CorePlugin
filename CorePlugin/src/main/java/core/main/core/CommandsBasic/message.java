package core.main.core.CommandsBasic;

import core.main.core.Chat.Chat;
import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Message.MessageManager.messages;
import static core.main.core.Profile.ProfileManager.profiles;

public class message implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("msg")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(args.length >= 2) {

                Player target = Bukkit.getPlayer(args[0]);
                Profile tProfile = profiles.get(target.getUniqueId());

                StringBuilder sb = new StringBuilder();
                for(int i=1; i < args.length; i++) {
                    sb = sb.append(args[i]);
                }
                String message = ChatColor.WHITE + sb.toString();

                p.sendMessage(ChatColor.WHITE + "(" + ChatColor.GRAY + "To " + tProfile.getRank().getColor() + target.getName() + ChatColor.WHITE + ")" + message);
                target.sendMessage(ChatColor.WHITE + "(" + ChatColor.GRAY + "From " + pProfile.getRank().getColor() + p.getName() + ChatColor.WHITE + ")" + message);
                messages.put(target.getUniqueId(), p.getUniqueId());

            }
            else {

                p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /msg <PlayerName> <Message>");

            }

        }

        return false;
    }

}
