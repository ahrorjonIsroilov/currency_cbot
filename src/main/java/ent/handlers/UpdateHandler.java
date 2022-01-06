package ent.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateHandler extends BaseAbstractHandler {
    private static final UpdateHandler instance = new UpdateHandler();
    private final MessageHandler messageHandler = MessageHandler.getInstance();
    private final CallbackHandler callbackHandler = CallbackHandler.getInstance();

    public static UpdateHandler getInstance() {
        return instance;
    }

    public void handle(Update update) {
        if (update.hasMessage()) {
            messageHandler.process(update);
        } else if (update.hasCallbackQuery())
            callbackHandler.process(update);
    }
}
