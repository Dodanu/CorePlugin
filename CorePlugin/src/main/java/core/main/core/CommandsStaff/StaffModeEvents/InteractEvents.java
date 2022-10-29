package core.main.core.CommandsStaff.StaffModeEvents;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static core.main.core.CommandsStaff.Vanish.vanished;
import static core.main.core.Core.AlertsPrefix;
import static core.main.core.Core.CorePrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class InteractEvents implements Listener {

    public void sendFreezeMessage(Player p) {

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f████&c█&f████"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f███&c█&6█&c█&f███ &4&lDo NOT log out!"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f██&c█&6█&0█&6█&c█&f██ &cIf you do, you will be banned!"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f██&c█&6█&0█&6█&c█&f██ &ePlease download &d&lDiscord &eand join"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f█&c█&6██&0█&6██&c█&f█ &d&nhttps://discord.gg/YkjmNKzrgJ"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f█&c█&6█████&c█&f█"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c█&6███&0█&6███&c█"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c█████████"));

    }

    @EventHandler
    public void onStaffIntercat(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Profile pP = profiles.get(p.getUniqueId());

        if(pP.getPlayerState().equals(Profile.PlayStates.Staff)) {

            ItemStack i = e.getItem();

            ItemStack i1 = new ItemStack(Material.TORCH);

            ItemMeta m1 = i1.getItemMeta();
            m1.setDisplayName(ChatColor.GREEN + "Vanish");
            List<String> lores = new ArrayList<>();
            lores.add(ChatColor.GREEN + "Right+Click to vanish!");
            m1.setLore(lores);
            i1.setItemMeta(m1);

            pP.setPlayerState(Profile.PlayStates.Staff);
            p.sendMessage(CorePrefix + ChatColor.RED + "You have activated StaffMode!");

            if(i.equals(i1)) {

                if (vanished.contains(p) == false) {

                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.hidePlayer(p);
                    }

                    vanished.add(p);

                } else {

                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.showPlayer(p);
                    }

                    vanished.remove(p);

                }

            }

        }

    }

    @EventHandler
    public void onPlayerStaffHit(EntityDamageByEntityEvent e) {

        if(e.getDamager() instanceof Player) {

            Player p = (Player) e.getDamager();
            Profile pP = profiles.get(p.getUniqueId());

            if(pP.getPlayerState().equals(Profile.PlayStates.Staff)) {

                e.setCancelled(true);

                ItemStack i = p.getItemInHand();


                ItemStack i2 = new ItemStack(Material.ICE);
                ItemStack i3 = new ItemStack(Material.CHEST);

                ItemMeta m2 = i2.getItemMeta();
                m2.setDisplayName(ChatColor.AQUA + "Freeze");
                List<String> lores2 = new ArrayList<>();
                lores2.add(ChatColor.GREEN + "Right+Click on someone to Freeze them!");
                m2.setLore(lores2);
                i2.setItemMeta(m2);

                ItemMeta m3 = i2.getItemMeta();
                m3.setDisplayName(ChatColor.GOLD + "SeeInv");
                List<String> lores3 = new ArrayList<>();
                lores3.add(ChatColor.GOLD + "Right+Click on someone to see their Inventory!");
                m3.setLore(lores3);
                i3.setItemMeta(m3);

                pP.setPlayerState(Profile.PlayStates.Staff);
                p.sendMessage(CorePrefix + ChatColor.RED + "You have activated StaffMode!");

                if(i.equals(i2)) {

                    Player target = (Player) e.getEntity();
                    Profile tProfile = profiles.get(target.getUniqueId());

                    if(tProfile.getRank().getStaffRank() != true || target.isOp() == false) {

                        if (tProfile.getPlayerState() != Profile.PlayStates.Frozen) {

                            tProfile.setPlayerState(Profile.PlayStates.Frozen);

                            sendFreezeMessage(target);

                            for (Player staff : Bukkit.getOnlinePlayers()) {

                                Profile sProfile = profiles.get(staff.getUniqueId());
                                String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + target.getName() + ChatColor.AQUA + "has been forzen by " + ChatColor.WHITE + p.getName();

                                if (sProfile.getRank().getStaffRank() == true) {
                                    staff.sendMessage(staffFinalMessage);
                                }

                            }

                        } else {

                            tProfile.setPlayerState(Profile.PlayStates.Normal);
                            p.sendMessage(CorePrefix + ChatColor.AQUA + "You have benn unfrozen!");

                        }
                    }

                }
                if(i.equals(i3)) {

                    Player target = (Player) e.getEntity();
                    Inventory tInventory = target.getInventory();
                    tInventory.addItem(target.getInventory().getArmorContents());

                    p.openInventory(tInventory);

                }

            }

        }

    }

}
