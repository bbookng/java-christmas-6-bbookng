package christmas.domain;

import christmas.domain.enums.Badge;

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

    // 크리스마스 디데이 할인 계산
    public int christmasDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) {
            return 0;
        }
        return visitDay.getChristmasDiscountPrice();
    }

    // 평일 또는 주말 여부 확인
    public String checkedWorkingDay() {
        if (visitDay.isWorkingDay()) {
            return "평일";
        }
        return "주말";
    }

    // 평일 및 주말 할인 계산
    public int workingDayDiscount() {
        if (orderList.getTotalOrderAmount() < 10000) {
            return 0;
        }
        return orderList.getWorkingDayDiscountPrice(visitDay);
    }

    // 특별 할인 계산
    public int specialDiscount() {
        if (orderList.getTotalOrderAmount() < 10000 || !visitDay.isStarredDay()) {
            return 0;
        }
        return 1000;
    }

    // 총 할인 금액 계산
    public int getTotalDiscountAmount() {
        return christmasDiscount() + workingDayDiscount() + specialDiscount();
    }

    // 총 혜택 금액 계산
    public int getTotalBenefitAmount() {
        return getTotalDiscountAmount() + getGiftMenuPrice();
    }

    // 예상 결제 금액 계산
    public int totalPayAmount() {
        return orderList.getTotalOrderAmount() - getTotalDiscountAmount();
    }

    // 증정 메뉴 정보 반환
    public String getGiftMenuString() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return "샴페인 1개";
        }
        return "없음";
    }

    // 증정 메뉴의 가격 반환
    public int getGiftMenuPrice() {
        if (orderList.getTotalOrderAmount() >= 120000) {
            return 25000;
        }
        return 0;
    }

    public Badge getBadge() {
        return this.badge;
    }

    public OrderList getOrderList() {
        return this.orderList;
    }
}