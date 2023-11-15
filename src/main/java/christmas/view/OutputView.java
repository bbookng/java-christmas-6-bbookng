package christmas.view;

import christmas.common.Messages;
import christmas.domain.OrderList;
import christmas.domain.TotalOrder;
import christmas.utils.TotalOrderStringMaker;

public class OutputView {
    private final TotalOrderStringMaker totalOrderStringMaker = new TotalOrderStringMaker();

    public void printGreetingMessage() {
        System.out.println(Messages.greetingMessage);
    }

    public void printEventPreview(int visitDay) {
        System.out.println(String.format(Messages.eventPreview, visitDay));
    }

    public void printOrderList(OrderList orderList) {
        System.out.println(orderList.toString());
    }

    public void printTotalEventResult(TotalOrder totalOrder) {
        printTotalOrderAmount(totalOrder.getOrderList());
        printGiftMenu(totalOrder);
        printBenefits(totalOrder);
        printTotalBenefitAmount(totalOrder);
        printTotalPayAmount(totalOrder);
        printBadge(totalOrder);
    }

    private void printTotalOrderAmount(OrderList orderList) {
        System.out.println(totalOrderStringMaker.totalOrderAmountString(orderList));
    }

    private void printGiftMenu(TotalOrder totalOrder) {
        System.out.println(totalOrderStringMaker.giftMenuString(totalOrder));
    }

    private void printBenefits(TotalOrder totalOrder) {
        System.out.println(totalOrderStringMaker.benefitsString(totalOrder));
    }

    private void printTotalBenefitAmount(TotalOrder totalOrder) {
        System.out.println(totalOrderStringMaker.totalBenefitAmountString(totalOrder));
    }

    private void printTotalPayAmount(TotalOrder totalOrder) {
        System.out.println(totalOrderStringMaker.totalPayAmountString(totalOrder));
    }

    private void printBadge(TotalOrder totalOrder) {
        System.out.println(totalOrderStringMaker.badgeString(totalOrder));
    }
}
