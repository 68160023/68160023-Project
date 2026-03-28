package utils;

public class ScoreCalculator {
    private int score;
    private int attempts;

    public ScoreCalculator() {
        this.score = 0;
        this.attempts = 0;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    /**
     * คำนวณคะแนนตามผลลัพธ์
     * @param isCorrect true ถ้าผสมสีถูก, false ถ้าผิด
     */
    public void calculateScore(boolean isCorrect) {
        if (isCorrect) {
            score += 10; // ตอบถูกบวก 10
        } else {
            score -= 5;  // ตอบผิดลบ 5
            
            // ป้องกันคะแนนติดลบ (ไม่ควรให้ผู้เล่นมีคะแนนน้อยกว่า 0)
            if (score < 0) {
                score = 0;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void reset() {
        this.score = 0;
        this.attempts = 0;
    }
}