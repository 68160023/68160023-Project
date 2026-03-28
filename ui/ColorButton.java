package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColorButton extends JButton {
    public ColorButton(String colorName, ActionListener actionListener) {
        super(colorName);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(true);
        setFocusable(false);
        setBackground(getColorFromName(colorName));
        
        // ถ้าเป็นสีอ่อน ให้ใช้ตัวหนังสือสีดำ
        if (isLightColor(colorName)) {
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.WHITE);
        }
        
        setFont(new Font("Tahoma", Font.BOLD, 12));
        addActionListener(actionListener);
    }

    private boolean isLightColor(String name) {
        String n = name.toLowerCase();
        return n.equals("yellow") || n.equals("white") || n.equals("pink") || n.equals("sky blue");
    }

    private Color getColorFromName(String name) {
        return switch (name.toLowerCase()) {
            case "red" -> new Color(220, 53, 69);
            case "blue" -> new Color(0, 123, 255);
            case "yellow" -> new Color(255, 193, 7);
            case "green" -> new Color(40, 167, 69);
            case "orange" -> new Color(253, 126, 20);
            case "purple" -> new Color(111, 66, 193);
            case "white" -> Color.WHITE;
            case "black" -> Color.BLACK;
            case "pink" -> new Color(255, 182, 193); // สีชมพู
            default -> Color.GRAY;
        };
    }
}
