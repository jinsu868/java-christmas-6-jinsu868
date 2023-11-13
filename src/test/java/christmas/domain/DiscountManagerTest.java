package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.DiscountType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountManagerTest {

    @ParameterizedTest
    @CsvSource({"1,1000", "5,1400", "25,3400"})
    void 디데이_할인_적용_테스트(int date, int discountAmount) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.get(DiscountType.D_DAY)).isEqualTo(discountAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 28, 30, 31})
    void 디데이_할인_적용X_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        boolean isDDayDiscount = discountResults.keySet().stream()
                .anyMatch(discountType -> discountType.getType().equals(DiscountType.D_DAY));

        Assertions.assertThat(isDDayDiscount).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 특별_할인_적용_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.get(DiscountType.SPECIAL)).isEqualTo(1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 9, 11})
    void 특별_할인_적용X_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        boolean isSpecialDiscount = discountResults.keySet().stream()
                .anyMatch(discountType -> discountType.getType().equals(DiscountType.SPECIAL));

        Assertions.assertThat(isSpecialDiscount).isFalse();
    }

    @Test
    @DisplayName("구매 총액이 10000보다 작은 경우 할인이 적용되지 않는다.")
    void 구매_총액_10000원_미만_할인_적용X_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 1);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void 평일_할인_적용_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("아이스크림", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.get(DiscountType.WEEKDAY)).isEqualTo(2023 * 3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15})
    void 평일_할인_적용X_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("양송이수프", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        boolean isWeekDayDiscount = discountResults.keySet().stream()
                .anyMatch(discountType -> discountType.getType().equals(DiscountType.WEEKDAY));

        Assertions.assertThat(isWeekDayDiscount).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15})
    void 주말_할인_적용_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("해산물파스타", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.get(DiscountType.WEEKEND)).isEqualTo(2023 * 3);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void 주말_할인_적용X_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu("아이스크림", 3);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        boolean isWeekendDiscount = discountResults.keySet().stream()
                .anyMatch(discountType -> discountType.getType().equals(DiscountType.WEEKEND));

        Assertions.assertThat(isWeekendDiscount).isFalse();
    }

    @Test
    @DisplayName("120000원 이상 구매 시 증정품을 준다.")
    void 증정품_적용_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu = new OrderMenu("해산물파스타", 4);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        Assertions.assertThat(discountResults.get(DiscountType.GIVEAWAY)).isEqualTo(25000);
    }

    @Test
    @DisplayName("120000원 미만 구매하면 증정품을 제공하지 않습니다.")
    void 증정품_미적용_테스트() {
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu = new OrderMenu("해산물파스타", 2);
        List<OrderMenu> orderMenus = new ArrayList<>();
        orderMenus.add(orderMenu);
        Order order = new Order(visitDate, orderMenus);
        DiscountManager discountManager = new DiscountManager(order);
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();

        boolean isGiveawayDiscount = discountResults.keySet().stream()
                .anyMatch(discountType -> discountType.getType().equals(DiscountType.GIVEAWAY));

        Assertions.assertThat(isGiveawayDiscount).isFalse();
    }
}