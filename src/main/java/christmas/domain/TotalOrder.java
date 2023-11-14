package christmas.domain;

import java.text.DecimalFormat;

public class TotalOrder {
    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final OrderList orderList;
    private final VisitDay visitDay;
    private final Badge badge;

    public TotalOrder(OrderList orderList, VisitDay visitDay) {
        this.orderList = orderList;
        this.visitDay = visitDay;
        this.badge = Badge.getBadge(getTotalBenefitAmount());
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(totalOrderAmountToString() + LINE_SEPARATOR);
        message.append(giftMenuToString() + LINE_SEPARATOR);
        message.append(benefitsToString() + LINE_SEPARATOR);
        message.append(totalBenefitAmountString() + LINE_SEPARATOR);
        message.append(totalPayAmountToString() + LINE_SEPARATOR);
        message.append(badgeToString());
        return message.toString();
    }

    private String getGiftMenu() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }

    private int getGiftMenuPrice() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return 25000;
        }
        return 0;
    }

    private String giftMenuToString() {
        return "<증정 메뉴>" + LINE_SEPARATOR + String.format("%s", getGiftMenu()) + LINE_SEPARATOR;
    }


    private String totalOrderAmountToString() {
        return "<할인 전 총주문 금액>" + LINE_SEPARATOR
                + String.format("%s원", formatter.format(orderList.getTotalOrderAmount()))
                + LINE_SEPARATOR;
    }

    private String benefitsToString() {
        StringBuilder message = new StringBuilder();
        christmasDiscountToString(message);
        workingDayDiscountToString(message);
        specialDiscountToString(message);
        giftMenuToString(message);
        if (message.length() == 0) message.append("없음" + LINE_SEPARATOR);
        return "<혜택 내역>" + LINE_SEPARATOR + message.toString();
    }

    private void christmasDiscountToString(StringBuilder message) {
        if (christmasDiscount() != 0) {
            String discountMessage = String.format("크리스마드 디데이 할인 : -%s원",
                    formatter.format(christmasDiscount())) + LINE_SEPARATOR;
            message.append(discountMessage);
        }
    }

    private int christmasDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) return 0;
        return visitDay.getChristmasDiscountPrice();
    }

    private void workingDayDiscountToString(StringBuilder message) {
        if (workingDayDiscount() != 0) {
            String discountMessage = String.format("%s 할인 : -%s원",
                    checkedWorkingDay(),
                    formatter.format(workingDayDiscount())) + LINE_SEPARATOR;
            message.append(discountMessage);
        }
    }

    private String checkedWorkingDay() {
        if (visitDay.isWorkingDay()) return "평일";
        return "주말";
    }

    private int workingDayDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) return 0;
        return orderList.getWorkingDayDiscountPrice(visitDay);
    }

    private void specialDiscountToString(StringBuilder message) {
        if (specialDiscount() != 0) {
            String discountMessage = String.format("특별 할인 : -%s원",
                    formatter.format(specialDiscount())) + LINE_SEPARATOR;
            message.append(discountMessage);
        }
    }

    private int specialDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) return 0;
        if (visitDay.isStarredDay()) return 1000;
        return 0;
    }

    private void giftMenuToString(StringBuilder message) {
        if (getGiftMenuPrice() != 0) {
            String discountMessage = String.format("증정 이벤트 : -25,000원") + LINE_SEPARATOR;
            message.append(discountMessage);
        }
    }

    private int getTotalDiscountAmount() {
        int discountAmount = 0;
        discountAmount += christmasDiscount();
        discountAmount += workingDayDiscount();
        discountAmount += specialDiscount();
        return discountAmount;
    }

    private int getTotalBenefitAmount() {
        return getTotalDiscountAmount() + getGiftMenuPrice();
    }

    private String totalBenefitAmountString() {
        return "<총혜택 금액>"
                + LINE_SEPARATOR
                + String.format("%s원", formatter.format(-getTotalBenefitAmount()))
                + LINE_SEPARATOR;
    }

    private int totalPayAmount() {
        return orderList.getTotalOrderAmount() - getTotalDiscountAmount();
    }

    private String totalPayAmountToString() {
        return "<할인 후 예상 결제 금액>"
                + LINE_SEPARATOR
                + String.format("%s원", formatter.format(totalPayAmount()))
                + LINE_SEPARATOR;
    }

    private String badgeToString() {
        return "<12월 이벤트 배지>" + LINE_SEPARATOR + badge.getBadgeName();
    }
}
