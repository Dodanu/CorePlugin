package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static core.main.core.Core.AlertsPrefix;
import static core.main.core.Core.StaffPrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class FreezeListener implements Listener {

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
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        Profile pProfile = profiles.get(p.getUniqueId());

        if(pProfile.getPlayerState().equals(Profile.PlayStates.Frozen)) {

            e.setCancelled(true);
            sendFreezeMessage(p);

            String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + p.getName() + ChatColor.AQUA + "has been forzen by ";

            for(Player staff : Bukkit.getOnlinePlayers()) {

                Profile sProfile = profiles.get(staff.getUniqueId());

                if (sProfile.getRank().getStaffRank() == true){

                    staff.sendMessage(staffFinalMessage);
                }

            }

        }

    }

    @EventHandler
    public void onPlayerHit(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Profile pProfile = profiles.get(p.getUniqueId());

        if(pProfile.getPlayerState().equals(Profile.PlayStates.Frozen)) {

            e.setCancelled(true);
            sendFreezeMessage(p);

            String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + p.getName() + ChatColor.AQUA + " is frozen at location " + p.getLocation();

            for(Player staff : Bukkit.getOnlinePlayers()) {

                Profile sProfile = profiles.get(staff.getUniqueId());

                if (sProfile.getRank().getStaffRank() == true){

                    staff.sendMessage(staffFinalMessage);
                }

            }

        }

    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        Profile pProfile = profiles.get(p.getUniqueId());

        if(pProfile.getPlayerState().equals(Profile.PlayStates.Frozen)) {

            String staffFinalMessage = AlertsPrefix + ChatColor.WHITE + p.getName() + ChatColor.RED + " has left while frozen at: " + ChatColor.WHITE + p.getLocation();

            for(Player staff : Bukkit.getOnlinePlayers()) {

                Profile sProfile = profiles.get(staff.getUniqueId());

                if (sProfile.getRank().getStaffRank() == true){

                    staff.sendMessage(staffFinalMessage);
                }

            }

        }

    }

}
