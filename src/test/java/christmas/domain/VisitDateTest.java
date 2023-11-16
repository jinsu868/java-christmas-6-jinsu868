package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 50})
    void 유효X_방문날짜_생성_실패_테스트(int date) {
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 15, 30, 31})
    void 유효_방문날짜_생성_성공_테스트(int date) {
        VisitDate visitDate = new VisitDate(date);
        Assertions.assertThat(visitDate.getDate()).isEqualTo(date);
    }

}