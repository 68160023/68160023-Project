package mains;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Game game;
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("Color Mixing Master");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null);
        showDifficultyMenu(); // เรียกหน้าเมนูก่อน
    }

    private void showDifficultyMenu() {
        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        menuPanel.setBackground(new Color(33, 37, 41));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("เลือกความยาก 🎯", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JButton easyBtn = createMenuButton("EASY (ง่าย )", Color.GREEN);
        JButton medBtn = createMenuButton("MEDIUM (ปกติ )", Color.YELLOW);
        JButton hardBtn = createMenuButton("HARD (ยากมาก )", Color.RED);

        easyBtn.addActionListener(e -> startGame(Game.Difficulty.EASY));
        medBtn.addActionListener(e -> startGame(Game.Difficulty.MEDIUM));
        hardBtn.addActionListener(e -> startGame(Game.Difficulty.HARD));

        menuPanel.add(title);
        menuPanel.add(easyBtn);
        menuPanel.add(medBtn);
        menuPanel.add(hardBtn);

        setContentPane(menuPanel);
        revalidate();
    }

    private JButton createMenuButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn.setBackground(color);
        btn.setFocusable(false);
        return btn;
    }

    private void startGame(Game.Difficulty diff) {
        this.game = new Game();
        this.game.setDifficulty(diff); // ตั้งค่าความยากที่เลือก
        this.gamePanel = new GamePanel(game);
        this.game.setGui(gamePanel);

        setContentPane(gamePanel); // เปลี่ยนจากหน้าเมนูเป็นหน้าเกม
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }
}