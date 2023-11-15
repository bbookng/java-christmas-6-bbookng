package christmas.utils;

import christmas.domain.OrderList;
import christmas.domain.TotalOrder;

import java.text.DecimalFormat;

public class TotalOrderStringMaker {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    // 총 주문 금액을 문자열로 반환
    public String totalOrderAmountString(OrderList orderList) {
        StringBuilder message = new StringBuilder();
        message.append("<할인 전 총주문 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(orderList.getTotalOrderAmount())))
                .append(LINE_SEPARATOR);

        return message.toString();
    }

    // 증정 메뉴 정보를 문자열로 반환
    public String giftMenuString(TotalOrder totalOrder) {
        StringBuilder message = new StringBuilder();
        message.append("<증정 메뉴>").append(LINE_SEPARATOR)
                .append(String.format("%s", totalOrder.getGiftMenuString()))
                .append(LINE_SEPARATOR);

        return message.toString();
    }

    // 혜택 내역 정보를 문자열로 반환
    public String benefitsString(TotalOrder totalOrder) {
        StringBuilder message = new StringBuilder();
        message.append("<혜택 내역>").append(LINE_SEPARATOR);

        appendChristmasDiscount(message, totalOrder);
        appendWorkingDayDiscount(message, totalOrder);
        appendSpecialDiscount(message, totalOrder);
        appendGiftMenuDiscount(message, totalOrder);

        appendNoDiscountMessageIfApplicable(message, totalOrder);

        return message.toString();
    }

    // 크리스마스 디데이 할인 내역 문자열 추가
    private void appendChristmasDiscount(StringBuilder message, TotalOrder totalOrder) {
        String discountName = "크리스마스 디데이 할인";
        int discountAmount = totalOrder.christmasDiscount();
        appendDiscountDetail(message, discountName, discountAmount);
    }

    // 평일 및 주말 할인 내역 문자열 추가
    private void appendWorkingDayDiscount(StringBuilder message, TotalOrder totalOrder) {
        String discountName = totalOrder.checkedWorkingDay() + " 할인";
        int discountAmount = totalOrder.workingDayDiscount();
        appendDiscountDetail(message, discountName, discountAmount);
    }

    // 특별 할인 내역 문자열 추가
    private void appendSpecialDiscount(StringBuilder message, TotalOrder totalOrder) {
        String discountName = "특별 할인";
        int discountAmount = totalOrder.specialDiscount();
        appendDiscountDetail(message, discountName, discountAmount);
    }

    // 혜택이 없을 경우 "없음" 문자열 추가
    private void appendNoDiscountMessageIfApplicable(StringBuilder message, TotalOrder totalOrder) {
        if (totalOrder.getTotalBenefitAmount() == 0) {
            message.append("없음").append(LINE_SEPARATOR);
        }
    }

    // 증정 메뉴 할인 정보를 문자열로 추가
    public void appendGiftMenuDiscount(StringBuilder message, TotalOrder totalOrder) {
        if (totalOrder.getGiftMenuPrice() != 0) {
            message.append("증정 이벤트 : -25,000원").append(LINE_SEPARATOR);
        }
    }

    // 할인 내역을 문자열로 추가
    private void appendDiscountDetail(StringBuilder message, String discountName, int discountAmount) {
        if (discountAmount != 0) {
            message.append(String.format("%s : -%s원", discountName, formatter.format(discountAmount)))
                    .append(LINE_SEPARATOR);
        }
    }

    // 총 혜택 금액을 문자열로 추가
    public String totalBenefitAmountString(TotalOrder totalOrder) {
        StringBuilder message = new StringBuilder();
        message.append("<총혜택 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(-totalOrder.getTotalBenefitAmount())))
                .append(LINE_SEPARATOR);

        return message.toString();
    }

    // 예상 결제 금액을 문자열로 추가
    public String totalPayAmountString(TotalOrder totalOrder) {
        StringBuilder message = new StringBuilder();
        message.append("<할인 후 예상 결제 금액>").append(LINE_SEPARATOR)
                .append(String.format("%s원", formatter.format(totalOrder.totalPayAmount())))
                .append(LINE_SEPARATOR);

        return message.toString();
    }

    // 이벤트 배지 정보를 문자열로 추가
    public String badgeString(TotalOrder totalOrder) {
        StringBuilder message = new StringBuilder();
        message.append("<12월 이벤트 배지>").append(LINE_SEPARATOR)
                .append(totalOrder.getBadge().getBadgeName());

        return message.toString();
    }
}
