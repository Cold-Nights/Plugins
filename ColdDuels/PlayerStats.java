package com.coldnights.duels;

public class PlayerStats {
    private int wins;
    private int losses;

    public PlayerStats() {
        this.wins = 0;
        this.losses = 0;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementLosses() {
        this.losses++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}
