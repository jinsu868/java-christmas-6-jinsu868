package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 4999})
    void 배지_못받는_케이스_테스트(int amount) {
        Badge badge = Badge.from(amount);
        Assertions.assertThat(badge).isEqualTo(Badge.NONE);
    }

    @ParameterizedTest
    @ValueSource(ints = {5000, 7000, 9999})
    void 별_배지_받는_케이스_테스트(int amount) {
        Badge badge = Badge.from(amount);
        Assertions.assertThat(badge).isEqualTo(Badge.STAR);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 19999})
    void 트리_배지_받는_케이스_테스트(int amount) {
        Badge badge = Badge.from(amount);
        Assertions.assertThat(badge).isEqualTo(Badge.TREE);
    }

    @ParameterizedTest
    @ValueSource(ints = {20000, 50000, 1000000})
    void 산타_배지_받는_케이스_테스트(int amount) {
        Badge badge = Badge.from(amount);
        Assertions.assertThat(badge).isEqualTo(Badge.SANTA);
    }
}