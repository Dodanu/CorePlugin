package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class ClearInv implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("clearInv")) {

            Player p = (Player) sender;
            Profile pP = profiles.get(p.getUniqueId());

            if(pP.getRank().getStaffRank()) {

                if(args.length == 1) {

                    Player t = Bukkit.getPlayer(args[0]);

                    t.getInventory().setContents(null);
                    t.getInventory().setArmorContents(null);
                    t.updateInventory();

                }
                else {
                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /clearInv <player>");
                }

            }

        }

        return false;

    }

}
