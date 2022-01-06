package ent.handlers;

import ent.y.main.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class CallbackHandler extends BaseAbstractHandler implements IBaseHandler {

    private static final CallbackHandler instance = new CallbackHandler();

    public static CallbackHandler getInstance() {
        return instance;
    }

    @Override
    public void process(Update update) {
        Message message;
        CallbackQuery callbackQuery = update.getCallbackQuery();
        Bot bot = Bot.getInstance();
        if (update.hasCallbackQuery())
            message = update.getCallbackQuery().getMessage();
        else
            message = update.getMessage();
        Long chatId = message.getChatId();
        String msgText = message.getText();
        User tgUser = message.getFrom();
        String data = callbackQuery.getData();
    }
}
