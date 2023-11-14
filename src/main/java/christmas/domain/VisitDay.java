package christmas.domain;

import christmas.common.Constant;
import christmas.common.ErrorMessages;

public class VisitDay {
    private final int day;

    public VisitDay(int day) {
        validateInRange(day);
        this.day = day;
    }

    public int getDay() {
        return this.day;
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

    public int getChristmasDiscountPrice() {
        if (day < 26) {
            return 1000 + (100 * (day - 1));
        }
        return 0;
    }

    private void validateInRange(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessages.validateVisitDay);
        }
    }
}
