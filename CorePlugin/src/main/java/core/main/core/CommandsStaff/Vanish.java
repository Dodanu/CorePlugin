package core.main.core.CommandsStaff;

import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.util.ArrayList;
import java.util.HashMap;

import static core.main.core.Profile.ProfileManager.profiles;

public class Vanish implements CommandExecutor {

    public static ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("vanish")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if (pProfile.getRank().getStaffRank() == true) {

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

        return false;

    }

}
