package mains;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import ui.ColorButton;

public class GamePanel extends JPanel {
    private final Game game;
    private final List<String> selectedColors = new ArrayList<>();
    private JPanel targetBox;
    private JLabel scoreLabel, selectionLabel, headerLabel;
    private JProgressBar timeBar;

    // เลือกใช้ Font ที่รองรับทั้งไทยและสัญลักษณ์
    private final Font thaiFont = new Font("Tahoma", Font.BOLD, 18);
    private final Font emojiFont = new Font("Segoe UI Symbol", Font.BOLD, 18);

    public GamePanel(Game game) {
        this.game = game;
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(33, 37, 41));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- ส่วนบน: ข้อมูลภาษาไทยและหัวใจ ---
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setOpaque(false);

        JPanel infoRow = new JPanel(new BorderLayout());
        infoRow.setOpaque(false);

        headerLabel = new JLabel("🎯 เป้าหมาย: -");
        headerLabel.setFont(thaiFont); 
        headerLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("คะแนน: 0 | หัวใจ: ❤❤❤");
        scoreLabel.setFont(thaiFont); 
        scoreLabel.setForeground(Color.YELLOW);

        infoRow.add(headerLabel, BorderLayout.WEST);
        infoRow.add(scoreLabel, BorderLayout.EAST);
        
        timeBar = new JProgressBar(0, 100);
        timeBar.setForeground(new Color(40, 167, 69));
        
        topPanel.add(infoRow);
        topPanel.add(timeBar);
        add(topPanel, BorderLayout.NORTH);

        // --- ส่วนกลาง: TARGET & SELECTED ---
        JPanel centerArea = new JPanel(new GridBagLayout());
        centerArea.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 15, 0, 15);

        targetBox = new JPanel();
        targetBox.setPreferredSize(new Dimension(100, 100));
        targetBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

        selectionLabel = new JLabel("-", SwingConstants.CENTER);
        selectionLabel.setPreferredSize(new Dimension(200, 50));
        selectionLabel.setOpaque(true);
        selectionLabel.setBackground(new Color(60, 63, 65));
        selectionLabel.setForeground(Color.CYAN);
        selectionLabel.setFont(thaiFont); // ใช้ Tahoma เพื่อให้อ่านไทยออก

        gbc.gridx = 0; centerArea.add(new JLabel("<html><font color='white' face='Tahoma'>เป้าหมาย:</font></html>"), gbc);
        gbc.gridx = 1; centerArea.add(targetBox, gbc);
        gbc.gridx = 2; centerArea.add(new JLabel("<html><font color='white' size='6'>➔</font></html>"), gbc);
        gbc.gridx = 3; centerArea.add(new JLabel("<html><font color='white' face='Tahoma'>สีที่เลือก:</font></html>"), gbc);
        gbc.gridx = 4; centerArea.add(selectionLabel, gbc);

        // --- ส่วนปุ่มสี ---
        JPanel grid = new JPanel(new GridLayout(2, 4, 12, 12));
        grid.setOpaque(false);
        String[] colors = {"Red", "Blue", "Yellow", "Green", "Orange", "Purple", "White", "Black"};
        for (String c : colors) {
            grid.add(new ColorButton(c, e -> {
                if (!selectedColors.contains(e.getActionCommand())) {
                    selectedColors.add(e.getActionCommand());
                    updateDisplay();
                    checkMixingAction();
                }
            }));
        }

        JPanel midPanel = new JPanel(new BorderLayout(20, 20));
        midPanel.setOpaque(false);
        midPanel.add(centerArea, BorderLayout.NORTH);
        midPanel.add(grid, BorderLayout.CENTER);
        add(midPanel, BorderLayout.CENTER);

        JButton clearBtn = new JButton("ล้างค่าที่เลือก 🗑️");
        clearBtn.setFont(thaiFont);
        clearBtn.addActionListener(e -> { selectedColors.clear(); updateDisplay(); });
        add(clearBtn, BorderLayout.SOUTH);
    }

    private void checkMixingAction() {
        String target = game.getTargetColor().getColorName().toLowerCase();
        int limit = (target.equals("black") || target.equals("gold")) ? 3 : 2;
        if (selectedColors.size() >= limit) {
            game.processMixing(new ArrayList<>(selectedColors));
            selectedColors.clear();
            updateDisplay();
        }
    }

    private void updateDisplay() {
        selectionLabel.setText(selectedColors.isEmpty() ? "-" : String.join(" + ", selectedColors));
    }

    public void updateTimer(int value) {
        timeBar.setValue(value);
        timeBar.setForeground(value < 30 ? Color.RED : new Color(40, 167, 69));
    }

    // ในส่วน updateUI ของ GamePanel.java
public void updateUI(Color target, String name, int score, int hp, int lv) {
    targetBox.setBackground(target);
    headerLabel.setText("🎯 เป้าหมาย: " + name + " (Lv." + lv + ")");
    
    // แสดงหัวใจตามจำนวนเลือดที่เหลือ
    String heartEmoji = (hp <= 1) ? "💔" : "❤️";
    scoreLabel.setText("คะแนน: " + score + " | หัวใจ: " + heartEmoji.repeat(Math.max(0, hp)));
    
    targetBox.repaint();
}
}


