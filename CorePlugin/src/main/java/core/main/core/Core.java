package core.main.core;

import core.main.core.Chat.ChatHandler;
import core.main.core.CommandsBasic.message;
import core.main.core.CommandsBasic.reply;
import core.main.core.CommandsBasic.report;
import core.main.core.CommandsStaff.*;
import core.main.core.Profile.Profile;
import core.main.core.Punishments.ban;
import core.main.core.Punishments.mute;
import core.main.core.Punishments.tban;
import core.main.core.Punishments.warn;
import core.main.core.Rank.Rank;
import core.main.core.Rank.RankCommands;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static core.main.core.Profile.ProfileManager.profiles;
import static core.main.core.Rank.RankManager.ranks;

public final class Core extends JavaPlugin {

    //stringuri folosite ca sa nu le rescriu de miliarde de ori

    public static String CorePrefix = ChatColor.GOLD + "[" + ChatColor.RED + "Core" + ChatColor.GOLD + "] ";

    public static String BroadcastPrefix = ChatColor.GOLD + "[" + ChatColor.GREEN + "Broadcast" + ChatColor.GOLD + "] ";

    public static String AlertsPrefix = ChatColor.YELLOW + "[" + ChatColor.RED + "⚠" + ChatColor.YELLOW + "] ";

    public static String HCFPrefix = ChatColor.GOLD + "[" + ChatColor.RED + "HCSquads" + ChatColor.GOLD + "] ";

    public static String DenyCommand = ChatColor.RED + "You don't have permission to do that!";

    public static String DoToStaff = ChatColor.RED + "You can't do that to staff!";

    public static String StaffPrefix = ChatColor.BLUE + "[Staff] ";

    public static String StaffJoin = ChatColor.BLUE + " has joined the server";

    @Override
    public void onEnable() {

        this.saveDefaultConfig();   /**Aici se face config.yml (nici eu nush ce inseamna asta da e fisierul de salvare)*/

        this.loadRanks();   /**Aici se da load la rankuri*/

        if(this.getConfig().contains("Profiles")) {  /**Aici se da load la profiluri*/
            this.loadProfiles();
        }

        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        this.saveProfiles();
    }

    public void registerCommands() {

        getCommand("msg").setExecutor(new message());
        getCommand("reply").setExecutor(new reply());
        getCommand("report").setExecutor(new report());
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("clearchat").setExecutor(new ClearChat());
        getCommand("togglechat").setExecutor(new DisableGlobalChat());
        getCommand("fly").setExecutor(new Fly());
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("invsee").setExecutor(new InvSee());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("warp").setExecutor(new Warp());
        getCommand("ban").setExecutor(new ban());
        getCommand("mute").setExecutor(new mute());
        getCommand("tban").setExecutor(new tban());
        getCommand("warn").setExecutor(new warn());
        getCommand("rank").setExecutor(new RankCommands());


    }

    public void registerEvents() {

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChatHandler(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new FreezeListener(), this);

    }

    public void saveProfiles() {
        for(Map.Entry<UUID, Profile> entry : profiles.entrySet()){
            this.getConfig().set("Profiles." + entry.getKey() + ".displayName", entry.getValue().getDisplayName());
            this.getConfig().set("Profiles." + entry.getKey() + ".iRank", entry.getValue().getiRank());
            this.getConfig().set("Profiles." + entry.getKey() + ".warns", entry.getValue().getWarns());
            this.getConfig().set("Profiles." + entry.getKey() + ".muted", entry.getValue().getMuted());
            this.getConfig().set("Profiles." + entry.getKey() + ".lastBanReason", entry.getValue().getLastBanReason());
            this.getConfig().set("Profiles." + entry.getKey() + ".banned", entry.getValue().getBanned());
            this.getConfig().set("Profiles." + entry.getKey() + ".deathBanned", entry.getValue().getDeathBanned());
            this.getConfig().set("Profiles." + entry.getKey() + ".banTime", entry.getValue().getBanTime());
            this.getConfig().set("Profiles." + entry.getKey() + ".deathBanTime", entry.getValue().getDeathBanTime());
        }
        this.saveConfig();
    }

    public void loadProfiles() {
        this.getConfig().getConfigurationSection("Profiles.").getKeys(false).forEach(key->{
            Profile profile = new Profile();
            profile.displayName = ((String) this.getConfig().get("Profiles." + key + ".displayName"));
            profile.iRank = ((Integer) this.getConfig().get("Profiles." + key + ".iRank"));
            profile.muted = ((Boolean) this.getConfig().get("Profiles." + key + ".muted"));
            profile.warns = ((Integer) this.getConfig().get("Profiles." + key + ".warns"));
            profile.lastBanReason = ((String) this.getConfig().get("Profiles." + key + ".lastBanReason"));
            profile.banned = ((Boolean) this.getConfig().get("Profiles." + key + ".banned"));
            profile.deathBanned = ((Boolean) this.getConfig().get("Profiles." + key + ".deathBanned"));
            profile.banTime = ((Long) this.getConfig().get("Profiles." + key + ".banTime"));
            profile.deathBanTime = ((Long) this.getConfig().get("Profiles." + key + ".deathBanTime"));
            profile.rank = ranks.get(profile.iRank);
            profiles.put(UUID.fromString(key), profile);
        });
    }

    public void loadRanks() {    /**Aici pun rankurile ca imi e lene sa fac un command de creere :/ */

        Rank Player = new Rank();
        Player.id = 1;
        Player.name = "Player";
        Player.displayName = "";
        Player.color = "&7";
        Player.staffRank = false;
        ranks.add(Player);

        Rank Owner = new Rank();
        Owner.id = 2;
        Owner.name = "Owner";
        Owner.displayName = "&4Owner";
        Owner.staffRank = true;
        Owner.color = "&4";
        //ranks.set(Owner.id, Owner);
        ranks.add(Owner);

        Rank Moderator = new Rank();
        Moderator.id = 3;
        Moderator.name = "Moderator";
        Moderator.displayName = "&5Moderator";
        Moderator.staffRank = true;
        Moderator.color = "&5";
        //ranks.set(Moderator.id, Moderator);
        ranks.add(Moderator);

        Rank Builder = new Rank();
        Builder.id = 4;
        Builder.name = "Builder";
        Builder.displayName = "&9Builder";
        Builder.staffRank = true;
        Builder.color = "&9";
        //ranks.set(Builder.id, Builder);
        ranks.add(Builder);

        Rank Helper = new Rank();
        Helper.id = 5;
        Helper.name = "Helper";
        Helper.displayName = "&dHelper";
        Helper.staffRank = true;
        Helper.color = "&d";
        //ranks.set(Helper.id, Helper);
        ranks.add(Helper);

        Rank Basic = new Rank();
        Basic.id = 6;
        Basic.name = "Basic";
        Basic.displayName = "&bBasic";
        Basic.staffRank = false;
        Basic.color = "&b";
        //ranks.set(Basic.id, Basic);
        ranks.add(Basic);

        Rank HipCool = new Rank();
        HipCool.id = 7;
        HipCool.name = "HipCool";
        HipCool.displayName = "&aHipCool";
        HipCool.staffRank = false;
        HipCool.color = "&a";
        //ranks.set(HipCool.id, HipCool);
        ranks.add(HipCool);

        Rank HipCoolPlus = new Rank();
        HipCoolPlus.id = 8;
        HipCoolPlus.name = "HipCool+";
        HipCoolPlus.displayName = "&2HipCool+";
        HipCoolPlus.staffRank = false;
        HipCoolPlus.color = "&2";
        //ranks.set(HipCoolPlus.id, HipCoolPlus);
        ranks.add(HipCoolPlus);

        Rank Sharp = new Rank();
        Sharp.id = 9;
        Sharp.name = "Sharp";
        Sharp.displayName = "&cSharp";
        Sharp.staffRank = false;
        Sharp.color = "&c";
        //ranks.set(Sharp.id, Sharp);
        ranks.add(Sharp);

        Rank SharpPlus = new Rank();
        SharpPlus.id = 10;
        SharpPlus.name = "Sharp+";
        SharpPlus.displayName = "&4Sharp+";
        SharpPlus.staffRank = false;
        SharpPlus.color = "&4";
        //ranks.set(SharpPlus.id, SharpPlus);
        ranks.add(SharpPlus);


    }

}
