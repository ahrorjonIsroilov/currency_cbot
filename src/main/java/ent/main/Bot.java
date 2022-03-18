package ent.main;

import ent.handlers.UpdateHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private static final Bot instance = new Bot();
    UpdateHandler handler = UpdateHandler.getInstance();

    public static Bot getInstance() {
        return instance;
    }

    @Override
    public String getBotUsername() {
        return "@currency_cbot";
    }

    @Override
    public String getBotToken() {
        return "5206559611:AAGT6oNxwD8auisggQ9ksNqCiRBAiKQjKF8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        handler.handle(update);
    }

    public void executeMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(SendDocument sendDocument) {
        try {
            this.execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
