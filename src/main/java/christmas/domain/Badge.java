package christmas.domain;

public enum Badge {
    STAR(5000, "별"), TREE(10000, "트리"), SANTA(20000, "산타");
    private final int price;
    private final String badgeName;

    Badge(int price, String badgeName) {
        this.price = price;
        this.badgeName = badgeName;
    }
}
