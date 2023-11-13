package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.Badge;
import christmas.constant.DiscountAmount;
import christmas.constant.DiscountType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BillTest {
    @Test
    @DisplayName("주말 할인 품목 1개와 12월 1일 기준으로 할인 총액을 테스트합니다.")
    void 할인_총액_주말2023_DDay1000_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu1 = new OrderMenu("양송이수프", 2);
        OrderMenu orderMenu2 = new OrderMenu("해산물파스타", 1);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.calculateDiscountAmount())
                .isEqualTo(DiscountAmount.D_DAY_BASIC.getAmount() + DiscountAmount.WEEKEND.getAmount());
    }

    @Test
    @DisplayName("증정품을 주는 경우를 테스트 합니다.")
    void 증정품_지급_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu1 = new OrderMenu("양송이수프", 2);
        OrderMenu orderMenu2 = new OrderMenu("해산물파스타", 4);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.judgeGiveaway()).isTrue();
    }

    @Test
    @DisplayName("증정품을 안주는 경우를 테스트합니다.")
    void 증정품_미지급_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu1 = new OrderMenu("양송이수프", 2);
        OrderMenu orderMenu2 = new OrderMenu("해산물파스타", 1);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.judgeGiveaway()).isFalse();
    }

    @Test
    @DisplayName("산타 배지를 주는 경우를 테스트합니다.")
    void 산타_배지_테스트() {
        VisitDate visitDate = new VisitDate(3);
        OrderMenu orderMenu1 = new OrderMenu("바비큐립", 1);
        OrderMenu orderMenu2 = new OrderMenu("티본스테이크", 1);
        OrderMenu orderMenu3 = new OrderMenu("초코케이크", 2);
        OrderMenu orderMenu4 = new OrderMenu("제로콜라", 1);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        orderMenus.add(orderMenu3);
        orderMenus.add(orderMenu4);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);
        Badge badge = bill.getBadge();

        Assertions.assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("트리 배지를 주는 경우을 테스트합니다.")
    void 트리_배지_테스트() {
        VisitDate visitDate = new VisitDate(24);
        OrderMenu orderMenu1 = new OrderMenu("바비큐립", 1);
        OrderMenu orderMenu2 = new OrderMenu("초코케이크", 4);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.getBadge()).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("별 배지를 주는 경우을 테스트합니다.")
    void 별_배지_테스트() {
        VisitDate visitDate = new VisitDate(24);
        OrderMenu orderMenu1 = new OrderMenu("바비큐립", 1);
        OrderMenu orderMenu2 = new OrderMenu("초코케이크", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.getBadge()).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("할인이 적용된 최종 가격을 테스트합니다.")
    void 할인_적용_최종_가격_테스트() {
        VisitDate visitDate = new VisitDate(24);
        OrderMenu orderMenu1 = new OrderMenu("바비큐립", 1);
        OrderMenu orderMenu2 = new OrderMenu("초코케이크", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu1);
        orderMenus.add(orderMenu2);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        Assertions.assertThat(bill.getAfterDiscountOrderAmount()).isEqualTo(88631);
    }
}

