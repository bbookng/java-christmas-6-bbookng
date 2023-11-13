package christmas.view;

import christmas.common.Messages;

public class OutputView {
    public void printGreetingMessage() {
        System.out.println(Messages.greetingMessage);
    }
    public void printEventPreview(int visitDay) {
        System.out.println(String.format(Messages.eventPreview, visitDay));
    }
}
