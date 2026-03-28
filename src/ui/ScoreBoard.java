package ui;
import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private final JLabel scoreLabel;
    private final JLabel attemptsLabel;

    public ScoreBoard() {
        // ตั้งค่าหน้าตาของ ScoreBoard
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        setBackground(new Color(45, 45, 45)); // สีเทาเข้มดูโมเดิร์น
        
        scoreLabel = createStyledLabel("Score: 0");
        attemptsLabel = createStyledLabel("Attempts: 0");

        add(scoreLabel);
        add(attemptsLabel);
    }

    // Helper Method สำหรับตกแต่งตัวอักษร
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        return label;
    }

    /**
     * อัปเดตการแสดงผลโดยรับค่าจาก Class Player
     */
    public void updateStatus(int score, int attempts) {
        scoreLabel.setText("Score: " + score);
        attemptsLabel.setText("Attempts: " + attempts);
    }

    public void reset() {
        scoreLabel.setText("Score: 0");
        attemptsLabel.setText("Attempts: 0");
    }
}