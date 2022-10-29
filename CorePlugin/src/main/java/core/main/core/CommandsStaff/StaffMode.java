package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class StaffMode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("sm")) {

            Player p = (Player) sender;
            Profile pP = profiles.get(p.getUniqueId());

            if(pP.getRank().getStaffRank() == true) {

                ItemStack i1 = new ItemStack(Material.TORCH);
                ItemStack i2 = new ItemStack(Material.ICE);
                ItemStack i3 = new ItemStack(Material.CHEST);

                ItemMeta m1 = i1.getItemMeta();
                m1.setDisplayName(ChatColor.GREEN + "Vanish");
                List<String> lores = new ArrayList<>();
                lores.add(ChatColor.GREEN + "Right+Click to vanish!");
                m1.setLore(lores);
                i1.setItemMeta(m1);

                ItemMeta m2 = i2.getItemMeta();
                m2.setDisplayName(ChatColor.AQUA + "Freeze");
                List<String> lores2 = new ArrayList<>();
                lores2.add(ChatColor.GREEN + "Left+Click on someone to Freeze them!");
                m2.setLore(lores2);
                i2.setItemMeta(m2);

                ItemMeta m3 = i2.getItemMeta();
                m3.setDisplayName(ChatColor.GOLD + "SeeInv");
                List<String> lores3 = new ArrayList<>();
                lores3.add(ChatColor.GOLD + "Left+Click on someone to see their Inventory!");
                m3.setLore(lores3);
                i3.setItemMeta(m3);

                pP.setPlayerState(Profile.PlayStates.Staff);
                p.sendMessage(CorePrefix + ChatColor.RED + "You have activated StaffMode!");

                Inventory pInv = p.getInventory();
                pInv.clear();
                pInv.addItem(i1, i2, i3);

            }

        }
        return false;

    }

}
