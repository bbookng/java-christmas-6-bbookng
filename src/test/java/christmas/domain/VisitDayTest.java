package christmas.domain;

import christmas.common.ErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class VisitDayTest {

    @DisplayName("평일일 경우 워킹데이 판단")
    @ValueSource(ints = {3, 4, 5, 6, 7})
    @ParameterizedTest
    void isWorkingDay(int input) {
        assertThat(new VisitDay(input).isWorkingDay()).isTrue();
    }

    @DisplayName("주말일 경우 워킹데이 판단")
    @ValueSource(ints = {1, 2})
    @ParameterizedTest
    void isNotWorkingDay(int input) {
        assertThat(new VisitDay(input).isWorkingDay()).isFalse();
    }

    @DisplayName("별표시된 날짜인지 판단")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void isStarredDay(int input) {
        assertThat(new VisitDay(input).isStarredDay()).isTrue();
    }

    @DisplayName("별표시되지 않은 날짜인지 판단")
    @ValueSource(ints = {4, 5, 6, 7, 8, 30})
    @ParameterizedTest
    void isNotStarredDay(int input) {
        assertThat(new VisitDay(input).isStarredDay()).isFalse();
    }

    @DisplayName("VisitDay 생성자 테스트")
    @ValueSource(ints = {4, 5, 6, 7, 8, 30})
    @ParameterizedTest
    void createVisitDay(int input) {
        assertThatCode(() -> new VisitDay(input)).doesNotThrowAnyException();
    }

    @DisplayName("1에서 31사이의 날짜가 입력되지 않은 경우 생성자 테스트")
    @ValueSource(ints = {-4, 0, 32, 100})
    @ParameterizedTest
    void createInvalidVisitDay(int input) {
        assertThatThrownBy(() -> new VisitDay(input)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ErrorMessages.validateInRange);
    }
}