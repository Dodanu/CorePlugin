package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("fly")) {
            Player p = (Player) sender;
            Profile profile = profiles.get(p.getUniqueId());

            if(profile.getRank().getStaffRank() == true || p.isOp()) {

                if(args.length == 0) {

                    if (p.isFlying() == false) {

                        p.setAllowFlight(true);
                        p.setFlying(true);

                        p.sendMessage(CorePrefix + ChatColor.GREEN + "You have enabled flight");

                    } else {

                        p.setAllowFlight(true);
                        p.setFlying(false);

                        p.sendMessage(CorePrefix + ChatColor.RED + "You have disabled flight");

                    }

                }

                else {
                    if(args.length == 1) {

                        Player target = Bukkit.getPlayer(args[0]);

                        if (p.isFlying() == false) {

                            target.setAllowFlight(true);
                            target.setFlying(true);

                            p.sendMessage(CorePrefix + ChatColor.GREEN + "You have enabled flight for: " + target.getName());
                            target.sendMessage(CorePrefix + ChatColor.RED + "Your flight has been enabled by " + p.getName());

                        } else {

                            p.setAllowFlight(false);
                            p.setFlying(false);

                            p.sendMessage(CorePrefix + ChatColor.RED + "You have disabled flight for: " + target.getName());
                            target.sendMessage(CorePrefix + ChatColor.RED + "Your flight has been disabled by " + p.getName());

                        }

                    }
                    else{

                        p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /fly (<PlayerName>)");

                    }

                }

            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

                return true;
            }
        }

        return false;

    }

}
