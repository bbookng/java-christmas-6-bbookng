package christmas.view;

import christmas.common.Messages;
import christmas.domain.OrderList;
import christmas.domain.TotalOrder;

public class OutputView {
    public void printGreetingMessage() {
        System.out.println(Messages.greetingMessage);
    }
    public void printEventPreview(int visitDay) {
        System.out.println(String.format(Messages.eventPreview, visitDay));
    }
    public void printOrderList(OrderList orderList) {
        System.out.println(orderList.toString());
    }
    public void printResultMessage(TotalOrder totalOrder) {
        System.out.println(totalOrder.toString());
    }
}
