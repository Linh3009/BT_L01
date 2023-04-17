package model;

public enum Level {
    POOR("Kém"),
    WEAK("Yếu"),
    AVERAGE("Trung bình"),
    GOOD("Khá"),
    VERYGOOD("Giỏi"),
    EXCELLENT("Xuất sắc");

    private final String levelName;
    Level(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }
}
