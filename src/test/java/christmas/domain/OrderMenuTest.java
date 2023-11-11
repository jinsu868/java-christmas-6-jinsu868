package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.error.IllegalArgumentExceptionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"없는메뉴1", "없는메뉴2", "없는메뉴3", "없는메뉴4"})
    void 없는_메뉴_생성_실패_테스트(String menu) {
        assertThatThrownBy(() -> new OrderMenu(menu, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "크리스마스파스타", "제로콜라"})
    void 있는_메뉴_생성_성공_테스트(String menu) {
        OrderMenu orderMenu = new OrderMenu(menu, 1);
    }
}