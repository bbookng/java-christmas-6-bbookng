package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import christmas.domain.enums.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderListTest {

    public static OrderList orderList;
    public static VisitDay visitDay;

    @BeforeAll
    static void setUp() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Menu.TBONE_STEAK, 1));
        orders.add(new Order(Menu.BARBEQUE_RIB, 1));
        orders.add(new Order(Menu.CHOCOLATE_CAKE, 2));
        orders.add(new Order(Menu.ZERO_COKE, 1));

        orderList = new OrderList(orders);
        visitDay = new VisitDay(3);
    }

    @DisplayName("OrderList 출력 테스트")
    @Test
    void testToString() {
        String expected = "<주문 메뉴>\n" +
                "티본스테이크 1개\n" +
                "바비큐립 1개\n" +
                "초코케이크 2개\n" +
                "제로콜라 1개\n";

        assertThat(orderList.toString())
                .isEqualToNormalizingWhitespace(expected);
    }

    @DisplayName("총 주문 금액 테스트")
    @Test
    void getTotalOrderAmount() {
        assertThat(orderList.getTotalOrderAmount())
                .isEqualTo(142000);
    }

    @DisplayName("평일/주말할인 금액 테스트")
    @Test
    void getWorkingDayDiscountPrice() {
        assertThat(orderList.getWorkingDayDiscountPrice(visitDay)).isEqualTo(4046);
    }
}