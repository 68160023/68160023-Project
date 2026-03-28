package models;

public class Player {
    private int score;
    private int attempts;

    public Player() {
        this.score = 0;
        this.attempts = 0;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void reset() {
        this.score = 0;
        this.attempts = 0;
    }
}

