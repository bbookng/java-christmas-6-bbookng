package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.ErrorMessages;
import christmas.common.Messages;
import christmas.domain.OrderList;
import christmas.domain.OrderListFactory;

public class InputView {
    private final OrderListFactory orderListFactory;

    public InputView() {
        this.orderListFactory = new OrderListFactory();
    }

    public int inputVisitDay() {
        System.out.println(Messages.inputVisitDay);
        String input = Console.readLine();
        validateIsNumber(input);
        return Integer.parseInt(input);
    }

    public OrderList inputOrderList() {
        System.out.println(Messages.inputOrderMenu);
        String input = Console.readLine();
        return orderListFactory.createOrderList(input);
    }

    private void validateIsNumber(String input) {
        if (!input.matches("[0-9]"))
            throw new IllegalArgumentException(ErrorMessages.validateVisitDay);
    }
}
