package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"없는메뉴1", "없는메뉴2", "없는메뉴3", "없는메뉴4"})
    void 없는_메뉴_생성_실패_테스트(String name) {
        assertThatThrownBy(() -> Menu.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "크리스마스파스타", "제로콜라"})
    void 있는_메뉴_생성_성공_테스트(String name) {
        Menu menu = Menu.from(name);
        Assertions.assertThat(menu.getName()).isEqualTo(name);
    }
}