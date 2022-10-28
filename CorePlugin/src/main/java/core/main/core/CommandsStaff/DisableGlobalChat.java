package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Chat.ChatHandler.on;
import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;

public class DisableGlobalChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("togglechat")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true) {

                if(on == false) {

                    p.sendMessage(CorePrefix + ChatColor.GREEN + "You have enabled global chat");

                    on = true;

                }
                else {

                    p.sendMessage(CorePrefix + ChatColor.RED + "You have disabled global chat");

                    on = false;

                }

            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }
}
