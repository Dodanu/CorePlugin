package core.main.core.CommandsStaff;

import core.main.core.Core;
import core.main.core.Profile.Profile;
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

public class ClearChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("clearchat")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true || p.isOp()) {

                for(int i = 0; i < 130; i++) {
                    Bukkit.getServer().broadcastMessage("");
                }

                String message = CorePrefix + ChatColor.RED + "Chat has been cleared by " + p.getName();
                String strike = Striketrough(CorePrefix + "Chat has been cleared by " + p.getName() + "            ");

                Bukkit.getServer().broadcastMessage(ChatColor.STRIKETHROUGH + strike + "\n" + message + "\n" + ChatColor.STRIKETHROUGH + strike);

            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }

}
