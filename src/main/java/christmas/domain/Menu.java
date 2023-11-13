package christmas.domain;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    TBONE_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BARBEQUE_RIB("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESERT),
    ICECREAM("아이스크림", 5000, MenuType.DESERT),
    ZERO_COKE("제로콜라", 3000, MenuType.DRINK),
    RED_WINE("레드와인", 60000, MenuType.DRINK),
    CHAMPAIGN("샴페인", 25000, MenuType.DRINK);

    private final String menuName;
    private final int price;
    private final MenuType menuType;

    Menu(String menuName, int price, MenuType menuType) {
        this.menuName = menuName;
        this.price = price;
        this.menuType = menuType;
    }

    public int getPrice() {
        return this.price;
    }
    public String getMenuName() {
        return this.menuName;
    }

    public int getDiscountedPrice(VisitDay day) {
        if (day.isWorkingDay() && this.menuType == MenuType.DESERT) {
            return this.price - 2023;
        }
        if (!day.isWorkingDay() && this.menuType == MenuType.MAIN) {
            return this.price - 2023;
        }
        return this.price;
    }

    public static Menu findMenuByMenuName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenuName().equalsIgnoreCase(menuName))
                .findFirst()
                .orElse(null);
    }
}
