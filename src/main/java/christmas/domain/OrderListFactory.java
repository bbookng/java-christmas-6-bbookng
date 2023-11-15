package christmas.domain;

import christmas.utils.OrderListValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderListFactory {
    private final OrderListValidator orderListValidator = new OrderListValidator();

    public OrderList createOrderList(String input) {
        List<Order> orderList = new ArrayList<>();

        splitInputString(input).forEach(inputString -> {
            Order order = orderListValidator.validateFormat(inputString, orderList);
            orderList.add(order);
        });

        orderListValidator.validateOrders(orderList);

        return new OrderList(orderList);
    }

    private List<String> splitInputString(String input) {
        return Arrays.stream(input.split(",")).toList();
    }
}

