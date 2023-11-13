package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Separator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -50})
    void 주문_수량_0_이하_실패_테스트(int quantity) {
        assertThatThrownBy(() -> new OrderMenu("양송이수프", quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource({"양송이수프-2,12000", "타파스-4,22000", "시저셀러드-3,24000"})
    void 주문_메뉴_금액_계산_로직_테스트(String input, int result) {
        String[] menuAndQuantity = input.split(Separator.DASH.getDivision());
        OrderMenu orderMenu = new OrderMenu(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1]));
        Assertions.assertThat(orderMenu.calculateOrderAmount()).isEqualTo(result);
    }
}