package ent.main;

import ent.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    static String statusText;

    public static void main(String[] args) {

        connectBot();
    }

    private static void connectBot() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Bot());
            statusText = "Connected!";
            System.out.println("Connected");
        } catch (TelegramApiException e) {
            statusText = "Not connected!";
            System.out.println("Not connected!");
        }
        if (statusText.equals("Not connected!")) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectBot();
        }
    }
}
