package com.coldnights.duels;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class DuelCommand implements CommandExecutor {

    private DuelManager duelManager;

    public DuelCommand(DuelManager duelManager) {
        this.duelManager = duelManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                String targetName = args[0];
                Player target = sender.getServer().getPlayer(targetName);
                if (target != null) {
                    duelManager.sendDuelRequest(player, target);
                } else {
                    player.sendMessage("Player not found.");
                }
                return true;
            }
            player.sendMessage("Usage: /duel <player>");
        }
        return false;
    }
}
