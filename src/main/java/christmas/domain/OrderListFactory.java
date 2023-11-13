package christmas.domain;

import christmas.common.ErrorMessages;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderListFactory {
    public OrderList createOrderList(String input) {
        List<Order> orderList = new ArrayList<>();

        splitInputString(input).forEach(inputString -> {
            Order order = validateFormat(inputString, orderList);
            orderList.add(order);
        });

        return new OrderList(orderList);
    }

    // inputString "," 로 분리
    private List<String> splitInputString(String input) {
        return Arrays.stream(input.split(",")).toList();
    }

    // 주문 형식 확인
    private Order validateFormat(String input, List<Order> orderList) {
        if (!input.contains("-")) {
            throw new IllegalArgumentException(ErrorMessages.validateOrderMenu);
        }

        String[] inputSplit = input.split("-");
        Menu menu = validateMenu(inputSplit[0], orderList);
        int count = validateIsNumber(inputSplit[1]);

        return new Order(menu, count);
    }

    // 메뉴 유효성 확인 통합
    private Menu validateMenu(String input, List<Order> orderList) {
        validateString(input);
        validateDuplicatedMenu(input, orderList);
        return Menu.findMenuByMenuName(input);
    }

    // 유효한 입력값인지 확인
    private void validateString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.validateOrderMenu);
        }
    }

    // 중복된 메뉴가 존재하는 지 확인
    private void validateDuplicatedMenu(String input, List<Order> orderList) {
        boolean isDuplicated = orderList.stream()
                .anyMatch(order -> order.getMenu().equals(input));

        if (isDuplicated) {
            throw new IllegalArgumentException(ErrorMessages.validateOrderMenu);
        }
    }

    // 메뉴의 개수가 0보다 큰 숫자인지 확인
    private int validateIsNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            if (number < 1) {
                throw new IllegalArgumentException(ErrorMessages.validateOrderMenu);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.validateOrderMenu);
        }
    }
}

