package ent.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;

public class InlineBoards {
    private static final InlineKeyboardMarkup board = new InlineKeyboardMarkup();

    public static InlineKeyboardMarkup amounts() {
        InlineKeyboardButton _10 = new InlineKeyboardButton("10");
        _10.setCallbackData("10");
        InlineKeyboardButton _50 = new InlineKeyboardButton("50");
        _50.setCallbackData("50");
        InlineKeyboardButton _100 = new InlineKeyboardButton("100");
        _100.setCallbackData("100");
        InlineKeyboardButton _1000 = new InlineKeyboardButton("1000");
        _1000.setCallbackData("1000");
        ArrayList<InlineKeyboardButton> row1 = new ArrayList<>(Arrays.asList(_10, _50));
        ArrayList<InlineKeyboardButton> row2 = new ArrayList<>(Arrays.asList(_100, _1000));
        board.setKeyboard(Arrays.asList(row1, row2));
        return board;
    }
}
