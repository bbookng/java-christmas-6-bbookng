package christmas.controller;

import christmas.domain.OrderList;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    public void play() {
        outputView.printGreetingMessage();
        int visitDay = inputView.inputVisitDay();
        OrderList orderList = inputView.inputOrderList();
        outputView.printEventPreview(visitDay);

    }
}
