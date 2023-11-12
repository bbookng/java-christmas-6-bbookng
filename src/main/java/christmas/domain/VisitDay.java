package christmas.domain;

import christmas.common.Constant;
import christmas.common.ErrorMessages;

public class VisitDay {
    private final int day;

    public VisitDay(int day) {
        validateInRange(day);
        this.day = day;
    }

    public boolean isWorkingDay() {
        if (this.day % 7 > 2 || this.day % 7 == 0) {
            return true;
        }
        return false;
    }

    public boolean isStarredDay() {
        for (int starredDay : Constant.STARRED_DAY) {
            if (starredDay == this.day) return true;
        }
        return false;
    }

    private void validateInRange(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessages.validateInRange);
        }
    }
}
