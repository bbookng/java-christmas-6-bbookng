package christmas.domain;

public enum MenuType {
    APPETIZER("에피타이저"), MAIN("메인"), DESERT("디저트"), DRINK("음료");
    private final String typeName;

    MenuType(String typeName) {
        this.typeName = typeName;
    }
}
