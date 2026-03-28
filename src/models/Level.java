package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mains.Game; // นำเข้า Enum Difficulty

public class Level {
    private List<ColorTarget> basicColors = new ArrayList<>();
    private List<ColorTarget> interColors = new ArrayList<>();
    private List<ColorTarget> advColors = new ArrayList<>();
    private Random random = new Random();

    public Level(int level) {
        loadColors();
    }

    private void loadColors() {
        // 1. กลุ่มสีง่าย (Basic) - เน้น Purple, Orange, Green
        basicColors.add(new ColorTarget("Purple", new Color(128, 0, 128)));
        basicColors.add(new ColorTarget("Orange", new Color(255, 165, 0)));
        basicColors.add(new ColorTarget("Green", new Color(0, 128, 0)));
        basicColors.add(new ColorTarget("Pink", new Color(255, 182, 193)));

        // 2. กลุ่มสีปานกลาง (Intermediate) - เพิ่มสีที่ผสมกับ ขาว/ดำ
        interColors.add(new ColorTarget("Sky Blue", new Color(135, 206, 235)));
        interColors.add(new ColorTarget("Navy", new Color(0, 0, 128)));
        interColors.add(new ColorTarget("Gray", Color.GRAY));
        interColors.add(new ColorTarget("Mint", new Color(152, 255, 152)));
        interColors.add(new ColorTarget("Brown", new Color(139, 69, 19)));
        interColors.add(new ColorTarget("Lime", new Color(191, 255, 0)));

        // 3. กลุ่มสีซับซ้อน (Advanced) - ต้องใช้ความจำและความเร็ว
        advColors.add(new ColorTarget("Gold", new Color(255, 215, 0)));
        advColors.add(new ColorTarget("Maroon", new Color(128, 0, 0)));
        advColors.add(new ColorTarget("Turquoise", new Color(64, 224, 208)));
        advColors.add(new ColorTarget("Magenta", new Color(255, 0, 255)));
        advColors.add(new ColorTarget("Olive", new Color(128, 128, 0)));
        advColors.add(new ColorTarget("Cyan", new Color(0, 255, 255)));
    }

    // ฟังก์ชันสุ่มตามเปอร์เซ็นต์
    public ColorTarget getRandomTargetColor(Game.Difficulty diff) {
        int chance = random.nextInt(100); // สุ่ม 0-99

        if (diff == Game.Difficulty.EASY) {
            // Easy: Basic 80%, Intermediate 20%
            if (chance < 80) return getRandomFrom(basicColors);
            return getRandomFrom(interColors);
        } 
        else if (diff == Game.Difficulty.MEDIUM) {
            // Medium: Basic 30%, Intermediate 50%, Advanced 20%
            if (chance < 30) return getRandomFrom(basicColors);
            if (chance < 80) return getRandomFrom(interColors);
            return getRandomFrom(advColors);
        } 
        else {
            // Hard: Intermediate 30%, Advanced 70%
            if (chance < 30) return getRandomFrom(interColors);
            return getRandomFrom(advColors);
        }
    }

    private ColorTarget getRandomFrom(List<ColorTarget> list) {
        return list.get(random.nextInt(list.size()));
    }

    public void nextLevel() {}
}