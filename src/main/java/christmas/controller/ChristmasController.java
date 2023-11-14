package christmas.controller;

import christmas.domain.OrderList;
import christmas.domain.TotalOrder;
import christmas.domain.VisitDay;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    public void play() {
        outputView.printGreetingMessage();
        VisitDay visitDay = inputView.inputVisitDay();
        OrderList orderList = inputView.inputOrderList();
        outputView.printEventPreview(visitDay.getDay());

        outputView.printOrderList(orderList);
        TotalOrder totalOrder = new TotalOrder(orderList, visitDay);
        outputView.printResultMessage(totalOrder);
    }
}
