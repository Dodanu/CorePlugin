package core.main.core.CommandsBasic;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Message.MessageManager.messages;
import static core.main.core.Profile.ProfileManager.profiles;

public class reply implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("reply")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());
            UUID tUUID = messages.get(p.getUniqueId());
            Player target = Bukkit.getPlayer(tUUID);
            Profile tProfile = profiles.get(target.getUniqueId());

            if(args.length >= 1) {

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

                p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /reply <Message>");

            }

        }

        return false;
    }

}
