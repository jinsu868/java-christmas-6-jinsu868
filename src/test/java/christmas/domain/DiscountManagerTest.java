package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.DiscountType;
import christmas.constant.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
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

}