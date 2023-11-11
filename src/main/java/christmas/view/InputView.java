package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Separator;
import christmas.dto.OrderRequest;
import christmas.error.IllegalArgumentExceptionType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return parseInt(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_VISIT_DATE.getException();
        }
    }

    public List<OrderRequest> readMenus() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        return parseMenus(input);
    }

    private List<OrderRequest> parseMenus(String input) {
        try {
            return Arrays.asList(input.split(Separator.COMMA.getDivision())).stream()
                    .map(m -> {
                        String[] menus = m.split(Separator.DASH.getDivision());
                        if (menus.length != 2) {
                            throw IllegalArgumentExceptionType.INVALID_VISIT_DATE.getException();
                        }
                        return new OrderRequest(menus[0], Integer.parseInt(menus[1]));
                    }).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_ORDER.getException();
        }
    }
}
