package christmas.domain;

import java.util.List;

public class OrderList {
    private final List<Order> orderList;

    public OrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("<주문 메뉴>\n");
        orderList.forEach(order -> message.append(orderToString(order)));
        return message.toString();
    }

    private String orderToString(Order order) {
        return String.format("%s %d개\n", order.getMenuName(), order.getCount());
    }

    public int getTotalOrderAmount() {
        return orderList.stream()
                .map(order -> {
                    Menu menu = Menu.findMenuByMenuName(order.getMenuName());
                    return order.getCount() * menu.getPrice();
                })
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getWorkingDayDiscountPrice(VisitDay visitDay) {
        return orderList.stream()
                .map(order -> {
                    Menu menu = Menu.findMenuByMenuName(order.getMenuName());
                    return order.getCount() * menu.getDiscountedPrice(visitDay);
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}
