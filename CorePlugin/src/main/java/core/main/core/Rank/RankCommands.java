package core.main.core.Rank;

import core.main.core.Chat.Chat;
import core.main.core.Profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static core.main.core.Core.CorePrefix;
import static core.main.core.Core.DenyCommand;
import static core.main.core.Profile.ProfileManager.profiles;
import static core.main.core.Rank.RankManager.ranks;

public class RankCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("rank")) {

            Player p = (Player) sender;
            Profile pProfile = profiles.get(p.getUniqueId());

            if(pProfile.getRank().getStaffRank() == true || p.isOp() == true) {

                if(args.length == 3) {

                    if (args[0].equalsIgnoreCase("set")) {

                        Player target = Bukkit.getPlayer(args[1]);
                        Profile tprofile = profiles.get(target.getUniqueId());

                        String rString = args[2];
                        Rank rank = new Rank();
                        for(int i=0; i < ranks.size(); i++) {

                            if(ranks.get(i).getName().equalsIgnoreCase(rString)) {

                                rank = ranks.get(i);
                                tprofile.setRank(ranks.get(i));
                                break;

                            }

                        }

                        String name = ChatColor.translateAlternateColorCodes('&',ChatColor.DARK_GRAY + "(" + rank.getDisplayName() + ChatColor.DARK_GRAY + ")" + rank.getColor() + target.getName() + ChatColor.WHITE + ": ");
                        tprofile.setDisplayName(name);
                        profiles.put(target.getUniqueId(), tprofile);

                        p.sendMessage(CorePrefix + ChatColor.GREEN + "You have promoted " + ChatColor.WHITE + p.getName() + ChatColor.GREEN + " to " + rank.getColor() + rank.getName());
                        target.sendMessage(CorePrefix + ChatColor.GREEN + "You have been promoted to " + rank.getColor() + rank.getName());
                    }

                }
                if(args[0].equalsIgnoreCase("remove")) {

                    Player target = Bukkit.getPlayer(args[1]);
                    Profile tprofile = profiles.get(target.getUniqueId());
                    tprofile.setRank(ranks.get(1));
                    Rank basic = ranks.get(1);
                    String displayName = basic.getColor() + p.getName() + ChatColor.WHITE + ": ";
                    tprofile.setDisplayName(displayName);
                    profiles.put(target.getUniqueId(), tprofile);

                }
            }

            else {

                p.sendMessage(CorePrefix + DenyCommand);
                return true;

            }

        }

        return false;
    }

}
