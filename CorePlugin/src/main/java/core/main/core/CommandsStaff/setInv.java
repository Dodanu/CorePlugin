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

public class setInv implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("setInv")) {

            Player p = (Player) sender;
            Profile pP = profiles.get(p.getUniqueId());

            if(pP.getRank().getStaffRank()) {

                if(args.length == 1) {

                    Player t = Bukkit.getPlayer(args[0]);
                    Inventory tInv = t.getInventory();
                    ItemStack[] tArm = t.getInventory().getArmorContents();

                    p.getInventory().setContents(tInv.getContents());
                    p.getInventory().setArmorContents(tArm);
                    p.setHealth(t.getHealth());
                    p.addPotionEffects(t.getActivePotionEffects());


                }
                else {
                    p.sendMessage(CorePrefix + ChatColor.YELLOW + "correct usage: /setInv <player>");
                }

            }

        }

        return false;

    }

}
