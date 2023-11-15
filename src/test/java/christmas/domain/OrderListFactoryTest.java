package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.common.ErrorMessages;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderListFactoryTest {
    public static OrderListFactory orderListFactory = new OrderListFactory();

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

    @DisplayName("OrderList 생성 성공 테스트")
    @Test
    void createOrderList() {
        assertThat(orderListFactory.createOrderList("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1").toString())
                .isEqualTo(orderList.toString());
    }

    @DisplayName("주문 형식이 맞지 않을 경우 예외 반환 테스트")
    @Test
    void splitInputStringTest() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("티본스테이크1,바비큐립1,초코케이크-2,제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenu);
    }

    @DisplayName("음료만 주문시 주문이 불가능하다.")
    @Test
    void validateMenusTest() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("제로콜라-1,레드와인-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderWithDrink);
    }

    @DisplayName("음식 주문은 한 번에 20개까지만 가능하다. 초과할 시 주문이 불가능하다.")
    @Test
    void validateMenuCounts() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("티본스테이크-10,바비큐립-15"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenuCounts);
    }

    @DisplayName("중복 메뉴를 입력한 경우")
    @Test
    void validateDuplicateMenu() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("시저샐러드-1,시저샐러드-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenu);
    }

    @DisplayName("메뉴의 개수를 수가 아닌 문자를 입력한 경우")
    @Test
    void validateIsNumberWhenInputString() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("시저샐러드-하나,시저샐러드-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenu);
    }

    @DisplayName("메뉴의 개수가 1 이하일 경우")
    @Test
    void validateIsNumberWhenUnder1() {
        assertThatThrownBy(() -> orderListFactory.createOrderList("시저샐러드-0,시저샐러드-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenu);
    }
}