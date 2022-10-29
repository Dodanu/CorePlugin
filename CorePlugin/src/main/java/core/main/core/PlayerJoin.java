package core.main.core;

import core.main.core.Chat.Chat;
import core.main.core.Profile.Profile;
import core.main.core.Rank.Rank;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;

import static core.main.core.Chat.ChatManager.chatManager;
import static core.main.core.Chat.StriketroughCreator.Striketrough;
import static core.main.core.Core.StaffJoin;
import static core.main.core.Core.StaffPrefix;
import static core.main.core.Profile.ProfileManager.profiles;
import static core.main.core.Rank.RankManager.ranks;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        Chat chat = new Chat();
        chatManager.put(p.getUniqueId(), chat);


        if(!profiles.containsKey(p.getUniqueId())){     /**Asta inseamna ca acest player a intra pt prima oara pe server*/

            Profile profile = new Profile();
            profile.setMuted(false);
            profile.setRank(ranks.get(0));
            Rank basic = ranks.get(0);
            String displayName = ChatColor.translateAlternateColorCodes('&', basic.getColor() + p.getName() + "&7: ");
            profile.setDisplayName(displayName);

            profiles.put(p.getUniqueId(), profile);

        }

        Profile profile = profiles.get(p.getUniqueId());

        if(profile.getBanned() == true) {

            Long now = System.currentTimeMillis();
            Date nowDate = new Date(now);
            Date unbannedDate = new Date(profile.getBanTime());

            if(nowDate == unbannedDate) {

                Long zero = 0L;

                profile.setBanned(false);
                profile.banTime = zero;

            }
            else {

                p.kickPlayer(ChatColor.RED + "You have been permanently banned form the CoreNetwork for " + profile.getLastBanReason());

            }


        }

        String msg1 = ChatColor.RED + "Welcome to the " + ChatColor.WHITE + "CoreNetwork" + ChatColor.RED + "!";
        String discord = "" + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "https://discord.gg/YkjmNKzrgJ";
        String strike = "                                       ";

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&n&7" + strike));
        p.sendMessage(msg1);
        p.sendMessage(discord);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&n&7" + strike));

        if(profile.getRank().getStaffRank() == true){
            for(Player plr : Bukkit.getOnlinePlayers()){
                Profile plrProfile = profiles.get(plr.getUniqueId());
                if(plrProfile.getRank().getStaffRank() == true){
                    plr.sendMessage(StaffPrefix + ChatColor.AQUA + plr.getName() + StaffJoin);
                }
            }
        }

    }

}
