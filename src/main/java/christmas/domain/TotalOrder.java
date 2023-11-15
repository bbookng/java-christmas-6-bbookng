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
        appendTotalOrderAmount(message);
        appendGiftMenu(message);
        appendBenefits(message);
        appendTotalBenefitAmount(message);
        appendTotalPayAmount(message);
        appendBadge(message);
        return message.toString();
    }

    // 총 주문 금액을 문자열로 추가
    private void appendTotalOrderAmount(StringBuilder message) {
        message.append("<할인 전 총주문 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(orderList.getTotalOrderAmount())))
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);
    }

    // 증정 메뉴 정보를 문자열로 추가
    private void appendGiftMenu(StringBuilder message) {
        message.append("<증정 메뉴>").append(LINE_SEPARATOR)
                .append(String.format("%s", getGiftMenu()))
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);
    }

    // 혜택 내역을 문자열로 추가
    private void appendBenefits(StringBuilder message) {
        message.append("<혜택 내역>").append(LINE_SEPARATOR);
        appendDiscountDetail(message, "크리스마스 디데이 할인", christmasDiscount());
        appendDiscountDetail(message, checkedWorkingDay() + " 할인", workingDayDiscount());
        appendDiscountDetail(message, "특별 할인", specialDiscount());
        appendGiftMenuDiscount(message);
        if (getTotalBenefitAmount() == 0) {
            message.append("없음").append(LINE_SEPARATOR);
        }
        message.append(LINE_SEPARATOR);
    }

    // 총 혜택 금액을 문자열로 추가
    private void appendTotalBenefitAmount(StringBuilder message) {
        message.append("<총혜택 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(-getTotalBenefitAmount())))
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);
    }

    // 예상 결제 금액을 문자열로 추가
    private void appendTotalPayAmount(StringBuilder message) {
        message.append("<할인 후 예상 결제 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(totalPayAmount())))
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);
    }

    // 이벤트 배지 정보를 문자열로 추가
    private void appendBadge(StringBuilder message) {
        message.append("<12월 이벤트 배지>").append(LINE_SEPARATOR)
                .append(badge.getBadgeName())
                .append(LINE_SEPARATOR);
    }

    // 크리스마스 디데이 할인 계산
    private int christmasDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) {
            return 0;
        }
        return visitDay.getChristmasDiscountPrice();
    }

    // 평일 또는 주말 여부 확인
    private String checkedWorkingDay() {
        if (visitDay.isWorkingDay()) {
            return "평일";
        }
        return "주말";
    }

    // 평일 및 주말 할인 계산
    private int workingDayDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) {
            return 0;
        }
        return orderList.getWorkingDayDiscountPrice(visitDay);
    }

    // 특별 할인 계산
    private int specialDiscount() {
        if (orderList.getTotalOrderAmount() < 10000 || !visitDay.isStarredDay()) {
            return 0;
        }
        return 1000;
    }

    // 할인 내역을 문자열로 추가
    private void appendDiscountDetail(StringBuilder message, String discountName, int discountAmount) {
        if (discountAmount != 0) {
            message.append(String.format("%s : -%s원", discountName, formatter.format(discountAmount)))
                    .append(LINE_SEPARATOR);
        }
    }

    // 증정 메뉴 할인 정보를 문자열로 추가
    private void appendGiftMenuDiscount(StringBuilder message) {
        if (getGiftMenuPrice() != 0) {
            message.append("증정 이벤트 : -25,000원").append(LINE_SEPARATOR);
        }
    }

    // 총 할인 금액 계산
    private int getTotalDiscountAmount() {
        return christmasDiscount() + workingDayDiscount() + specialDiscount();
    }

    // 총 혜택 금액 계산
    private int getTotalBenefitAmount() {
        return getTotalDiscountAmount() + getGiftMenuPrice();
    }

    // 예상 결제 금액 계산
    private int totalPayAmount() {
        return orderList.getTotalOrderAmount() - getTotalDiscountAmount();
    }

    // 증정 메뉴 정보 반환
    private String getGiftMenu() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }

    // 증정 메뉴의 가격 반환
    private int getGiftMenuPrice() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return 25000;
        }
        return 0;
    }
}