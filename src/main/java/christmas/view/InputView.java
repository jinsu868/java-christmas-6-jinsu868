package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Message;
import christmas.constant.Separator;
import christmas.dto.OrderRequest;
import christmas.error.ErrorCode;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;
    private static final int SPLIT_STRING_LENGTH = 2;

    public int readDate() {
        System.out.println(Message.VISIT_DAY_INPUT_MESSAGE.getMessage());
        String input = Console.readLine();
        return parseInt(input);
    }

    public List<OrderRequest> readMenus() {
        System.out.println(Message.ORDER_MENU_INPUT_MESSAGE.getMessage());
        String input = Console.readLine();

        return parseMenus(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw ErrorCode.INVALID_VISIT_DATE.getException();
        }
    }

    private List<OrderRequest> parseMenus(String input) {
        try {
            return Arrays.stream(input.split(Separator.COMMA.getDivision()))
                    .map(m -> m.split(Separator.DASH.getDivision()))
                    .peek(this::validateMenu)
                    .map(menu -> new OrderRequest(menu[MENU_NAME_INDEX].trim(),
                            Integer.parseInt(menu[MENU_QUANTITY_INDEX].trim())))
                    .toList();
        } catch (NumberFormatException e) {
            throw ErrorCode.INVALID_ORDER.getException();
        }
    }

    private void validateMenu(String[] menu) {
        if (menu.length != SPLIT_STRING_LENGTH) {
            throw ErrorCode.INVALID_VISIT_DATE.getException();
        }
    }
}
