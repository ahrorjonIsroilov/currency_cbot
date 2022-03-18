package ent.handlers;

import ent.buttons.MarkupBoards;
import ent.enums.ActionStates;
import ent.processors.Calculate;
import ent.states.State;
import ent.main.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Locale;

import static ent.enums.Words.*;
import static ent.utils.Emojis.*;

public class MessageHandler extends BaseAbstractHandler implements IBaseHandler {

    private static final MessageHandler instance = new MessageHandler();

    public static MessageHandler getInstance() {
        return instance;
    }

    public static Calculate calculate = Calculate.getInstance();
    public static State state = State.getInstance();

    @Override
    public void process(Update update) {
        calculate.getUpdates();
        Bot bot = Bot.getInstance();
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String mText = message.getText();
        User user = message.getFrom();
        String firstname = "<b>" + user.getFirstName() + "</b>";
        if (mText.equals("/start")) {
            SendMessage sendMessage = msgObject(chatId, WELCOME_TEXT1.get() + firstname + WELCOME_TEXT2.get());
            sendMessage.setReplyMarkup(MarkupBoards.mainMenu());
            bot.executeMessage(sendMessage);
        } else if (mText.equals(eUZ + " UZS")) {
            SendMessage sendMessage = msgObject(chatId, ENTER_UZS.get());
            bot.executeMessage(sendMessage);
            state.setState(chatId, ActionStates.FROM_USZ);
        } else if (mText.equals((eEN + " USD"))) {
            SendMessage sendMessage = msgObject(chatId, ENTER_USD.get());
            bot.executeMessage(sendMessage);
            state.setState(chatId, ActionStates.FROM_USD);
        } else if (mText.equals((eEUR + " EUR"))) {
            SendMessage sendMessage = msgObject(chatId, ENTER_EUR.get());
            bot.executeMessage(sendMessage);
            state.setState(chatId, ActionStates.FROM_EUR);
        } else if (mText.equals((eRU + " RUB"))) {
            SendMessage sendMessage = msgObject(chatId, ENTER_RUB.get());
            bot.executeMessage(sendMessage);
            state.setState(chatId, ActionStates.FROM_RUB);
        } else if (mText.equals(eCASH + " Exchange rates")) {
            state.setState(chatId, ActionStates.CURRENCY);
            ArrayList<Float> currs = calculate.currency();
            String preparedResult = getResult(currs, chatId, 0F);
            SendMessage sendMessage = msgObject(chatId, preparedResult);
            bot.executeMessage(sendMessage);
        } else if (mText.equals("/about")) {
            SendMessage sendMessage = msgObject(chatId, getAbout());
            bot.executeMessage(sendMessage);
        } else if (state.getState(chatId) != null && state.getState(chatId).equals(ActionStates.FROM_USZ)) {
            float value;
            try {
                value = Float.parseFloat(mText);
                if (value > Math.pow(10, 12)) {
                    SendMessage error = msgObject(chatId, LIMIT_ERROR.get());
                    bot.executeMessage(error);
                    return;
                }
            } catch (NumberFormatException e) {
                SendMessage error = msgObject(chatId, ERROR.get());
                bot.executeMessage(error);
                return;
            }
            ArrayList<Float> currs = calculate.uzsToOther(value);
            String preparedResult = getResult(currs, chatId, value);
            SendMessage sendMessage = msgObject(chatId, preparedResult);
            bot.executeMessage(sendMessage);
        } else if (state.getState(chatId) != null && state.getState(chatId).equals(ActionStates.FROM_EUR)) {
            float value;
            try {
                value = Float.parseFloat(mText);
                if (value > Math.pow(10, 12)) {
                    SendMessage error = msgObject(chatId, LIMIT_ERROR.get());
                    bot.executeMessage(error);
                    return;
                }
            } catch (NumberFormatException e) {
                SendMessage error = msgObject(chatId, ERROR.get());
                bot.executeMessage(error);
                return;
            }
            ArrayList<Float> currs = calculate.eurToOther(value);
            String preparedResult = getResult(currs, chatId, value);
            SendMessage sendMessage = msgObject(chatId, preparedResult);
            bot.executeMessage(sendMessage);
        } else if (state.getState(chatId) != null && state.getState(chatId).equals(ActionStates.FROM_USD)) {
            float value;
            try {
                value = Float.parseFloat(mText);
                if (value > Math.pow(10, 12)) {
                    SendMessage error = msgObject(chatId, LIMIT_ERROR.get());
                    bot.executeMessage(error);
                    return;
                }
            } catch (NumberFormatException e) {
                SendMessage error = msgObject(chatId, ERROR.get());
                bot.executeMessage(error);
                return;
            }
            ArrayList<Float> currs = calculate.usdToOther(value);
            String preparedResult = getResult(currs, chatId, value);
            SendMessage sendMessage = msgObject(chatId, preparedResult);
            bot.executeMessage(sendMessage);
        } else if (state.getState(chatId) != null && state.getState(chatId).equals(ActionStates.FROM_RUB)) {
            float value;
            try {
                value = Float.parseFloat(mText);
                if (value > Math.pow(10, 12)) {
                    SendMessage error = msgObject(chatId, LIMIT_ERROR.get());
                    bot.executeMessage(error);
                    return;
                }
            } catch (NumberFormatException e) {
                SendMessage error = msgObject(chatId, ERROR.get());
                bot.executeMessage(error);
                return;
            }
            ArrayList<Float> currs = calculate.rubToOther(value);
            String preparedResult = getResult(currs, chatId, value);
            SendMessage sendMessage = msgObject(chatId, preparedResult);
            bot.executeMessage(sendMessage);
        } else {
            SendMessage sendMessage = msgObject(chatId, WRONG_COMMAND.get());
            bot.executeMessage(sendMessage);
        }

    }

    private String getAbout() {
        return "<b><i>This bot was developed by <a href = \"https://t.me/AhrorjonIsroilov\">Isroilov Ahrorjon</a>,\n" +
               "allows you to view current exchange rates✅</i></b>";

    }

    private String getResult(ArrayList<Float> currs, Long chat_id, Float value) {
        String stringedValue = String.format(Locale.ENGLISH, "%f", value);
        StringBuilder builder = new StringBuilder();
        if (state.getState(chat_id).equals(ActionStates.FROM_USZ)) {
            builder.append("<i>🖊 Entered ").append("<b>").append(stringedValue).append("</b>").append(" Uzbek sum\n\n")
                    .append(eEN).append("<b>").append(currs.get(0)).append("</b>").append(" US Dollar\n")
                    .append(eEUR).append("<b>").append(currs.get(1)).append("</b>").append(" Euro\n")
                    .append(eRU).append("<b>").append(currs.get(2)).append("</b>").append(" Russian ruble</i>");
        } else if (state.getState(chat_id).equals(ActionStates.FROM_RUB)) {
            builder.append("<i>🖊 Entered ").append("<b>").append(stringedValue).append("</b>").append(" Russian ruble\n\n")
                    .append(eUZ).append("<b>").append(currs.get(0)).append("</b>").append(" Uzbek sum\n")
                    .append(eEN).append("<b>").append(currs.get(1)).append("</b>").append("  US Dollar\n")
                    .append(eEUR).append("<b>").append(currs.get(2)).append("</b>").append(" Euro</i>");
        } else if (state.getState(chat_id).equals(ActionStates.FROM_EUR)) {
            builder.append("<i>🖊 Entered ").append("<b>").append(stringedValue).append("</b>").append(" Euro\n\n")
                    .append(eUZ).append("<b>").append(currs.get(1)).append("</b>").append(" Uzbek sum\n")
                    .append(eEN).append("<b>").append(currs.get(0)).append("</b>").append(" US Dollar\n")
                    .append(eRU).append("<b>").append(currs.get(2)).append("</b>").append(" Russian ruble</i>");
        } else if (state.getState(chat_id).equals(ActionStates.FROM_USD)) {
            builder.append("<i>🖊 Entered ").append("<b>").append(stringedValue).append("</b>").append(" US Dollar\n\n")
                    .append(eUZ).append("<b>").append(currs.get(0)).append("</b>").append(" Uzbek sum\n")
                    .append(eEUR).append("<b>").append(currs.get(1)).append("</b>").append(" Euro\n")
                    .append(eRU).append("<b>").append(currs.get(2)).append("</b>").append(" Russian ruble</i>");
        } else if (state.getState(chat_id).equals(ActionStates.CURRENCY)) {
            builder.append(eUZ).append("<i><b> Current exchange rates in Uzbekistan ⤵").append("\n\n</b>")
                    .append(eEN).append("<b>").append(" 1 US Dollar - ").append(currs.get(0)).append("</b>\n")
                    .append(eEUR).append("<b>").append(" 1 Euro - ").append(currs.get(1)).append("</b>\n")
                    .append(eRU).append("<b>").append(" 1 Russian ruble - ").append(currs.get(2)).append("</b></i>");
        }
        return builder.toString();
    }
}
