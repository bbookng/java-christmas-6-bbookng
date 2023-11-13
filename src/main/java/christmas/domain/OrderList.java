package christmas.domain;

import java.util.List;

public class OrderList {
    List<Order> orderList;

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
        return String.format("%s %d개\n", order.getMenu(), order.getCount());
    }
}
