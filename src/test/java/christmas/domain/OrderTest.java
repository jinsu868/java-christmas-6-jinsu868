package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    @DisplayName("주문 메뉴가 중복되면 에러가 발생한다")
    void 주문_메뉴_중복_실패_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu menu1 = new OrderMenu("해산물파스타", 1);
        OrderMenu menu2 = new OrderMenu("해산물파스타", 1);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(menu1);
        orderMenus.add(menu2);
        assertThatThrownBy(() -> new Order(visitDate, orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴를 정상적으로 입력하면 주문이 생성된다.")
    void 주문_생성_성공_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu menu1 = new OrderMenu("해산물파스타", 1);
        OrderMenu menu2 = new OrderMenu("레드와인", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(menu1);
        orderMenus.add(menu2);
        Order order = new Order(visitDate, orderMenus);
    }

    @Test
    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    void 음료만_주문하는_경우_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu menu1 = new OrderMenu("레드와인", 2);
        OrderMenu menu2 = new OrderMenu("샴페인", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(menu1);
        orderMenus.add(menu2);
        assertThatThrownBy(() -> new Order(visitDate, orderMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}