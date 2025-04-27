package com.coldnights.duels;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private DuelManager duelManager;

    @Override
    public void onEnable() {
        duelManager = new DuelManager(this);
        getCommand("duel").setExecutor(new DuelCommand(duelManager));
        getServer().getPluginManager().registerEvents(new DuelListener(duelManager), this);
    }

    @Override
    public void onDisable() {
        duelManager.savePlayerStats();
    }
}
