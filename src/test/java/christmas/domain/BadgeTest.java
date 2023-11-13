package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @DisplayName("STAR BADGE 생성 테스트")
    @ValueSource(ints = {5000, 6000, 7000, 9999})
    @ParameterizedTest
    void getBadgeSTAR(int totalBenefitAmount) {
        assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.STAR);
    }

    @DisplayName("TREE BADGE 생성 테스트")
    @ValueSource(ints = {10000, 15000, 19999})
    @ParameterizedTest
    void getBadgeTREE(int totalBenefitAmount) {
        assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.TREE);
    }

    @DisplayName("SANTA BADGE 생성 테스트")
    @ValueSource(ints = {20000, 30000})
    @ParameterizedTest
    void getBadgeSANTA(int totalBenefitAmount) {
        assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.SANTA);
    }

    @DisplayName("BADGE 를 얻지 못할 경우 테스트")
    @ValueSource(ints = {0, 1000, 4999})
    @ParameterizedTest
    void getBadgeNONE(int totalBenefitAmount) {
        assertThat(Badge.getBadge(totalBenefitAmount))
                .isEqualTo(Badge.NONE);
    }
}