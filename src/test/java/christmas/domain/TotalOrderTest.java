package christmas.domain;

import christmas.domain.enums.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TotalOrderTest {
    public static OrderList orderList;
    public static VisitDay visitDay;
    public static TotalOrder totalOrder;

    @BeforeAll
    static void setUp() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Menu.TBONE_STEAK, 1));
        orders.add(new Order(Menu.BARBEQUE_RIB, 1));
        orders.add(new Order(Menu.CHOCOLATE_CAKE, 2));
        orders.add(new Order(Menu.ZERO_COKE, 1));

        orderList = new OrderList(orders);
        visitDay = new VisitDay(3);
        totalOrder = new TotalOrder(orderList, visitDay);
    }

    @DisplayName("크리스마스 디데이 할인 계산 테스트, 총 주문이 10000원 이하일 경우에는 이벤트 적용이 되지 않는다.")
    @Test
    void christmasDiscountWhenUnderMinimumAmount() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(Menu.MUSHROOM_SOUP, 1));
        TotalOrder totalOrder = new TotalOrder(new OrderList(orderList), new VisitDay(3));
        assertThat(totalOrder.christmasDiscount()).isEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인 계산 테스트, 26일 이후에는 이벤트 적용이 되지 않는다.")
    @Test
    void christmasDiscountWhenOverChristmas() {
        TotalOrder totalOrder = new TotalOrder(orderList, new VisitDay(26));
        assertThat(totalOrder.christmasDiscount()).isEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인 계산 테스트, 26일 이후에는 이벤트 적용이 되지 않는다.")
    @Test
    void christmasDiscountDuringChristmas() {
        assertThat(totalOrder.christmasDiscount()).isEqualTo(1200);
    }

    @DisplayName("평일일 경우에는 평일을 반환한다.")
    @Test
    void checkedWorkingDayWhenWorkingDay() {
        assertThat(totalOrder.checkedWorkingDay()).isEqualTo("평일");
    }

    @DisplayName("주말일 경우에는 주말을 반환한다.")
    @Test
    void checkedWorkingDayWhenWeekend() {
        TotalOrder totalOrder = new TotalOrder(orderList, new VisitDay(2));
        assertThat(totalOrder.checkedWorkingDay()).isEqualTo("주말");
    }

    @DisplayName("총 주문 금액이 10000원 이하이면 평일 및 주말할인 이벤트가 적용되지 않는다.")
    @Test
    void workingDayDiscountWhenUnderMinimumAmount() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(Menu.MUSHROOM_SOUP, 1));
        TotalOrder totalOrder = new TotalOrder(new OrderList(orderList), new VisitDay(3));
        assertThat(totalOrder.workingDayDiscount()).isEqualTo(0);
    }

    @DisplayName("평일에 디저트를 시키면 디저트 개수만큼 할인이 된다.")
    @Test
    void workingDayDiscountWhenWorkingDay() {
        assertThat(totalOrder.workingDayDiscount()).isEqualTo(4046);
    }

    @DisplayName("주말에 메인메뉴를 시키면 메인메뉴 개수만큼 할인이 된다.")
    @Test
    void workingDayDiscountWhenWeekend() {
        TotalOrder totalOrder = new TotalOrder(orderList, new VisitDay(2));
        assertThat(totalOrder.workingDayDiscount()).isEqualTo(4046);
    }

    @DisplayName("총 주문 금액이 10000원 이하이면 특별할인 이벤트가 적용되지 않는다.")
    @Test
    void specialDiscountWhenUnderMinimumAmount() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(Menu.MUSHROOM_SOUP, 1));
        TotalOrder totalOrder = new TotalOrder(new OrderList(orderList), new VisitDay(3));
        assertThat(totalOrder.specialDiscount()).isEqualTo(0);
    }

    @DisplayName("별 표시된 날이 아니면 0을 반환한다.")
    @ValueSource(ints = {5, 6, 7, 8, 11, 12, 13, 14})
    @ParameterizedTest
    void specialDiscountWhenNotStar(int input) {
        TotalOrder totalOrder = new TotalOrder(orderList, new VisitDay(input));
        assertThat(totalOrder.specialDiscount()).isEqualTo(0);
    }

    @DisplayName("별 표시된 날이면 1000을 반환한다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void specialDiscountWhenStar(int input) {
        TotalOrder totalOrder = new TotalOrder(orderList, new VisitDay(input));
        assertThat(totalOrder.specialDiscount()).isEqualTo(1000);
    }

    @DisplayName("총 주문 금액이 120,000 이상이면 샴페인 1개를 증정한다.")
    @Test
    void getGiftMenuStringWhenOverStandard() {
        assertThat(totalOrder.getGiftMenuString()).isEqualTo("샴페인 1개");
    }

    @DisplayName("총 주문 금액이 120,000 이하이면 없음을 반환한다.")
    @Test
    void getGiftMenuStringWhenUnderStandard() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(Menu.MUSHROOM_SOUP, 1));
        orderList.add(new Order(Menu.CHRISTMAS_PASTA, 1));
        TotalOrder totalOrder = new TotalOrder(new OrderList(orderList), new VisitDay(3));
        assertThat(totalOrder.getGiftMenuString()).isEqualTo("없음");
    }

    @DisplayName("총 주문 금액이 120,000 이상이면 25,000을 반환한다.")
    @Test
    void getGiftMenuPriceOverStandard() {
        assertThat(totalOrder.getGiftMenuPrice()).isEqualTo(25000);
    }

    @DisplayName("총 주문 금액이 120,000 이하이면 0을 반환한다.")
    @Test
    void getGiftMenuPriceUnderStandard() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(Menu.MUSHROOM_SOUP, 1));
        orderList.add(new Order(Menu.CHRISTMAS_PASTA, 1));
        TotalOrder totalOrder = new TotalOrder(new OrderList(orderList), new VisitDay(3));
        assertThat(totalOrder.getGiftMenuPrice()).isEqualTo(0);
    }
}