package models;

import java.awt.Color;

public class ColorTarget {
    private String colorName;
    private Color targetColor;

    // ต้องมี Constructor แบบนี้เป๊ะๆ (รับ String หนึ่งตัว และ Color หนึ่งตัว)
    public ColorTarget(String colorName, Color targetColor) {
        this.colorName = colorName;
        this.targetColor = targetColor;
    }

    public String getColorName() { return colorName; }
    public Color getTargetColor() { return targetColor; }
}