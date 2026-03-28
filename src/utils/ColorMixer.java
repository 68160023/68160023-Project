package utils;
import java.util.*;

public class ColorMixer {
    private static final Map<String, String> recipes = new HashMap<>();

    static {
        // --- LEVEL: EASY (ผสม 2 แม่สีหลัก) ---
        addRecipe("red", "blue", "Purple");
        addRecipe("red", "yellow", "Orange");
        addRecipe("blue", "yellow", "Green");

        // --- LEVEL: MEDIUM (สีพาสเทล / สีเข้ม) ---
        addRecipe("red", "white", "Pink");
        addRecipe("blue", "white", "Sky Blue");
        addRecipe("green", "white", "Mint");
        addRecipe("purple", "white", "Lavender");
        addRecipe("orange", "white", "Coral");
        addRecipe("yellow", "white", "Cream");
        addRecipe("red", "black", "Brick");
        addRecipe("blue", "black", "Navy");
        addRecipe("yellow", "black", "Olive");
        addRecipe("white", "black", "Gray");

        // --- LEVEL: HARD (ผสมสีรอง) ---
        addRecipe("orange", "red", "Vermilion");
        addRecipe("green", "blue", "Turquoise");
        addRecipe("purple", "red", "Magenta");
        addRecipe("orange", "black", "Brown");
        addRecipe("green", "yellow", "Lime");
    }

    private static void addRecipe(String c1, String c2, String result) {
        recipes.put(c1.toLowerCase().trim() + "+" + c2.toLowerCase().trim(), result);
        recipes.put(c2.toLowerCase().trim() + "+" + c1.toLowerCase().trim(), result);
    }

    public String mix(List<String> colors, String targetName) {
        int size = colors.size();
        List<String> c = colors.stream().map(s -> s.toLowerCase().trim()).toList();

        // สูตรผสม 3 สี (ADVANCED)
        if (size == 3) {
            // แดง + น้ำเงิน + เหลือง = ทอง (หรือดำ)
            if (c.contains("red") && c.contains("blue") && c.contains("yellow")) {
                return targetName.equalsIgnoreCase("Gold") ? "Gold" : "Black";
            }
            // แดง + ขาว + ดำ = สีแดงเลือดหมู/น้ำตาลเข้ม
            if (c.contains("red") && c.contains("white") && c.contains("black")) {
                return "Maroon";
            }
            // น้ำเงิน + เขียว + ขาว = ฟ้าเทอร์ควอยซ์อ่อน
            if (c.contains("blue") && c.contains("green") && c.contains("white")) {
                return "Cyan";
            }
        } 
        // สูตรผสม 2 สี
        else if (size == 2) {
            String key = c.get(0) + "+" + c.get(1);
            return recipes.getOrDefault(key, "Unknown");
        }
        
        return "Unknown";
    }
}
