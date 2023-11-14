package christmas.domain;

import christmas.common.ErrorMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {
    public static VisitDay workingDay;
    public static VisitDay weekend;

    @BeforeAll
    static void setup() {
        workingDay = new VisitDay(5);
        weekend = new VisitDay(1);
    }

    @DisplayName("메뉴판에 있는 메뉴 (티본스테이크) 찾기 테스트")
    @Test
    void findMenuByMenuName() {
        assertThat(Menu.findMenuByMenuName("티본스테이크"))
                .isEqualTo(Menu.TBONE_STEAK);
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력했을 시 예외 발생 테스트")
    @Test
    void findMenuByMenuNameException() {
        assertThatThrownBy(() -> Menu.findMenuByMenuName("짜파게티"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.validateOrderMenu);
    }

    @DisplayName("평일에 디저트를 먹었을 때 할인 가격이 2023원이 나오는지 테스트")
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    @ParameterizedTest
    void getDiscountedPriceWhenWorkingDayAndDessert(String menuName) {
        assertThat(Menu.findMenuByMenuName(menuName).getDiscountedPrice(workingDay))
                .isEqualTo(2023);
    }

    @DisplayName("주말에 메인 메뉴를 먹었을 때 할인 가격이 2023원이 나오는지 테스트")
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    @ParameterizedTest
    void getDiscountedPriceWhenWeekendAndMain(String menuName) {
        assertThat(Menu.findMenuByMenuName(menuName).getDiscountedPrice(weekend))
                .isEqualTo(2023);
    }

    @DisplayName("평일에 디저트가 아닌 다른 메뉴를 먹었을 때 할인 가격이 0원이 나오는지 테스트")
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    @ParameterizedTest
    void getDiscountedPriceWhenWorkingDayNotDessert(String menuName) {
        assertThat(Menu.findMenuByMenuName(menuName).getDiscountedPrice(workingDay))
                .isEqualTo(0);
    }

    @DisplayName("평일에 디저트가 아닌 다른 메뉴를 먹었을 때 할인 가격이 0원이 나오는지 테스트")
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    @ParameterizedTest
    void getDiscountedPriceWhenWeekendNotMain(String menuName) {
        assertThat(Menu.findMenuByMenuName(menuName).getDiscountedPrice(weekend))
                .isEqualTo(0);
    }

    @DisplayName("음료 메뉴 시 false 반환 테스트")
    @Test
    void validateOrder() {
        assertThat(Menu.CHAMPAIGN.validateOrder()).isFalse();
    }

    @DisplayName("음료가 아닌 메뉴 시 true 반환 테스트")
    @ValueSource(strings = {"초코케이크", "아이스크림", "티본스테이크", "크리스마스파스타"})
    @ParameterizedTest
    void validateOrder(String input) {
        assertThat(Menu.findMenuByMenuName(input).validateOrder()).isTrue();
    }
}