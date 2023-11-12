package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.ErrorMessages;
import christmas.common.Messages;

public class InputView {

    public int inputVisitDay() {
        System.out.println(Messages.inputVisitDay);
        String input = Console.readLine();
        validateIsNumber(input);
        return Integer.parseInt(input);
    }

    private void validateIsNumber(String input) {
        if (!input.matches("[0-9]"))
            throw new IllegalArgumentException(ErrorMessages.validateIsNumber);
    }
}
