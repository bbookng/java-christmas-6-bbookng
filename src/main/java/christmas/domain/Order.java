package christmas.domain;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenu() {
        return this.menu.getMenuName();
    }

    public int getCount() {
        return this.count;
    }
}
