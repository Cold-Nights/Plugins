package com.coldnights.duels;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class DuelManager {

    private Map<Player, Player> duelRequests = new HashMap<>();
    private Map<Player, PlayerStats> playerStats = new HashMap<>();

    private Main plugin;

    public DuelManager(Main plugin) {
        this.plugin = plugin;
    }

    public void sendDuelRequest(Player sender, Player target) {
        if (duelRequests.containsKey(sender)) {
            sender.sendMessage(ChatColor.RED + "You are already in a duel.");
            return;
        }

        duelRequests.put(sender, target);
        sender.sendMessage(ChatColor.GREEN + "Duel request sent to " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + sender.getName() + " has challenged you to a duel! Type /duelaccept to accept.");

        target.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (duelRequests.containsKey(sender)) {
                sender.sendMessage(ChatColor.RED + "Duel request to " + target.getName() + " has expired.");
                target.sendMessage(ChatColor.RED + "Duel request from " + sender.getName() + " has expired.");
                duelRequests.remove(sender);
            }
        }, 20 * 30); // 30 seconds to accept the duel
    }

    public void acceptDuel(Player player) {
        if (duelRequests.containsValue(player)) {
            Player opponent = duelRequests.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(player))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (opponent != null) {
                startDuel(player, opponent);
                duelRequests.remove(opponent);
                duelRequests.remove(player);
            }
        }
    }

    private void startDuel(Player player1, Player player2) {
        // Placeholder code for starting a duel (teleport players, give them gear, etc.)
        player1.sendMessage(ChatColor.GREEN + "Duel started with " + player2.getName() + "!");
        player2.sendMessage(ChatColor.GREEN + "Duel started with " + player1.getName() + "!");

        // Code for ending the duel and determining the winner (you can customize this)
    }

    public void savePlayerStats() {
        // Implement saving of player stats (e.g., wins, losses, etc.)
    }
}
