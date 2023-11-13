package christmas.domain;

public enum Badge {
    STAR(5000, "별"), TREE(10000, "트리"), SANTA(20000, "산타"), NONE(0, "없음");
    private final int price;
    private final String badgeName;

    Badge(int price, String badgeName) {
        this.price = price;
        this.badgeName = badgeName;
    }

    public static Badge getBadge(int benefitAmount) {
        if (benefitAmount >= SANTA.price) return SANTA;
        if (benefitAmount >= TREE.price) return TREE;
        if (benefitAmount >= STAR.price) return STAR;
        return NONE;
    }

    public String getBadgeName() {
        return this.badgeName;
    }
}
