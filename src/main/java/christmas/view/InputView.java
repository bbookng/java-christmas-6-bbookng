package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.ErrorMessages;
import christmas.common.Messages;
import christmas.domain.OrderList;
import christmas.domain.OrderListFactory;
import christmas.domain.VisitDay;

public class InputView {
    private final OrderListFactory orderListFactory;

    public InputView() {
        this.orderListFactory = new OrderListFactory();
    }

    public VisitDay inputVisitDay() {
        System.out.println(Messages.inputVisitDay);
        String input = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                input = Console.readLine().trim();
                validateIsNumber(input);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return new VisitDay(Integer.parseInt(input));
    }

    public OrderList inputOrderList() {
        System.out.println(Messages.inputOrderMenu);
        OrderList orderList = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                String input = Console.readLine().trim();
                orderList = orderListFactory.createOrderList(input);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderList;
    }

    private void validateIsNumber(String input) {
        if (!input.matches("^[0-9]+$"))
            throw new IllegalArgumentException(ErrorMessages.validateVisitDay);
    }
}
