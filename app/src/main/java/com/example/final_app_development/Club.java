package com.example.final_app_development;

public class Club {
    String name;
    int wins;
    int loses;
    int points;

    public Club (String name, int wins, int loses, int points) {
        this.name = name;
        this.wins = wins;
        this.loses = loses;
        this.points = points;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getWins () {
        return wins;
    }

    public void setWins (int wins) {
        this.wins = wins;
    }

    public int getLoses () {
        return loses;
    }

    public void setLoses (int loses) {
        this.loses = loses;
    }

    public int getPoints () {
        return points;
    }

    public void setPoints (int points) {
        this.points = points;
    }
}
