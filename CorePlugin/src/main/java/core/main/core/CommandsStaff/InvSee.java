package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;

public class InvSee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("invsee")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true) {

                if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);
                    Inventory tInventory = target.getInventory();
                    tInventory.addItem(target.getInventory().getArmorContents());

                    p.openInventory(tInventory);


                }
                else{

                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /invsee <playerName>");

                }

            }
            else {

                p.sendMessage(CorePrefix + DenyCommand);

            }

        }

        return false;

    }

}
