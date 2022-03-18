package ent.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

import static ent.utils.Emojis.*;

public class MarkupBoards {

    private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();

    public static ReplyKeyboardMarkup mainMenu() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row1.add(new KeyboardButton(eUZ + " UZS"));
        row1.add(new KeyboardButton(eRU + " RUB"));
        row2.add(new KeyboardButton(eEN + " USD"));
        row2.add(new KeyboardButton(eEUR + " EUR"));
        row3.add(new KeyboardButton(eCASH + " Exchange rates"));
        board.setKeyboard(Arrays.asList(row1, row2, row3));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

}
