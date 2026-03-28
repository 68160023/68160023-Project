package mains;

import models.ColorTarget;
import models.Level;
import utils.ColorMixer;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class Game {
    private ColorTarget targetColor;
    private Level level = new Level(1);
    private ColorMixer colorMixer = new ColorMixer();
    private int health = 3, score = 0, currentLevel = 1;
    private GamePanel gui;
    private Timer gameTimer;
    private int timeLeft;
    private Difficulty selectedDifficulty = Difficulty.EASY; // ค่าเริ่มต้น

    public Game() {
        gameTimer = new Timer(100, e -> {
            timeLeft -= 1;
            if (gui != null) gui.updateTimer(timeLeft);
            if (timeLeft <= 0) handleTimeout();
        });
    }

    // ฟังก์ชันสำหรับตั้งค่าความยากจากเมนู
    public void setDifficulty(Difficulty diff) {
        this.selectedDifficulty = diff;
        if (diff == Difficulty.HARD) health = 2; // ถ้า Hard ให้เลือดน้อยลง
        else health = 3;
    }

    public void setGui(GamePanel gui) {
        this.gui = gui;
        startNewRound();
    }

    public void startNewRound() {
        // สุ่มสีตามความยากที่ผู้เล่นเลือกไว้
        targetColor = level.getRandomTargetColor(selectedDifficulty);
        
        // ปรับเวลาตามความยาก
        int baseTime = switch (selectedDifficulty) {
            case EASY -> 120;
            case MEDIUM -> 80;
            case HARD -> 50;
        };
        timeLeft = Math.max(20, baseTime - (currentLevel * 3));
        
        gameTimer.start();
        if (gui != null) {
            gui.updateUI(targetColor.getTargetColor(), targetColor.getColorName(), score, health, currentLevel);
        }
    }

    private void handleTimeout() {
        health--;
        if (!checkGameOver()) startNewRound();
    }

    public void processMixing(List<String> selectedColors) {
        String result = colorMixer.mix(selectedColors, targetColor.getColorName());
        if (result.equalsIgnoreCase(targetColor.getColorName())) {
            score += 20;
            if (score % 100 == 0) currentLevel++;
        } else {
            health--;
        }
        if (!checkGameOver()) startNewRound();
    }

    private boolean checkGameOver() {
        if (health <= 0) {
            gameTimer.stop();
            showGameOverDialog();
            return true;
        }
        return false;
    }

    private void showGameOverDialog() {
        String msg = "☠️ Game Over ☠️\nScore: " + score;
        Object[] options = {"🔄 Play Again", "❌ Exit"};
        int choice = JOptionPane.showOptionDialog(null, msg, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            score = 0; currentLevel = 1;
            setDifficulty(selectedDifficulty); // รีเซ็ตเลือดตามโหมดเดิม
            startNewRound();
        } else {
            System.exit(0);
        }
    }

    public ColorTarget getTargetColor() { return targetColor; }
    public enum Difficulty { EASY, MEDIUM, HARD }
}