package core.main.core.Chat;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

import static core.main.core.Chat.ChatManager.chatManager;
import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.StaffPrefix;
import static core.main.core.Profile.ProfileManager.profiles;

public class ChatHandler implements Listener {

    public static Boolean on = true;

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        String message = e.getMessage();
        Player p = e.getPlayer();
        Chat chat = chatManager.get(p.getUniqueId());

        e.setCancelled(true);

        Profile profile = profiles.get(p.getUniqueId());

        String finalMessage = profile.getDisplayName();

        if (profile.getMuted() == false) {

            if (profile.getRank().getId() == 0) {
                finalMessage = finalMessage + ChatColor.GRAY + message;
            } else {
                finalMessage = finalMessage + ChatColor.WHITE + message;
            }

            if (chat.getAllChat() == true) {
                if (on == true) {
                    Bukkit.broadcastMessage(finalMessage);
                } else {
                    p.sendMessage(CorePrefix + ChatColor.RED + "Global chat is off!");
                }
            }

            if (chat.getStaffChat() == true) {

                for (Player staff : Bukkit.getOnlinePlayers()) {

                    Profile sProfile = profiles.get(staff.getUniqueId());
                    String staffFinalMessage = StaffPrefix + ChatColor.AQUA + finalMessage;

                    if (sProfile.getRank().getStaffRank() == true) {
                        staff.sendMessage(staffFinalMessage);
                    }

                }

            }
        } else {

            p.sendMessage(CorePrefix + ChatColor.RED + "You are currently muted, you can not talk on the CoreNetwork While Muted!");

        }
    }
}