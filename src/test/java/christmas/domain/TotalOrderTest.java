package christmas.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("TotalOrder의 toString() 메서드 테스트")
    @Test
    void testToString() {
        String expectedOutput = "<할인 전 총주문 금액>\n" +
                "142,000원\n" +
                "\n" +
                "<증정 메뉴>\n" +
                "샴페인 1개\n" +
                "\n" +
                "<혜택 내역>\n" +
                "크리스마스 디데이 할인 : -1,200원\n" +
                "평일 할인 : -4,046원\n" +
                "특별 할인 : -1,000원\n" +
                "증정 이벤트 : -25,000원\n" +
                "\n" +
                "<총혜택 금액>\n" +
                "-31,246원\n" +
                "\n" +
                "<할인 후 예상 결제 금액>\n" +
                "135,754원\n" +
                "\n" +
                "<12월 이벤트 배지>\n" +
                "산타";

        assertThat(totalOrder.toString()).isEqualTo(expectedOutput);
    }
}