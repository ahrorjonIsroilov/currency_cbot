package ent.processors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ent.models.Currency;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Calculate implements BaseProcessor {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static ArrayList<Currency> currencyJson;
    private static Currency USD = new Currency();
    private static Currency EUR = new Currency();
    private static Currency RUB = new Currency();
    private static Float usd_Value = 0F;
    private static Float rub_Value = 0F;
    private static Float eur_Value = 0F;
    private static Float usz_Value = 0F;
    private static ArrayList<Float> currencies = new ArrayList<>();

    public void getUpdates() {
        try {
            currencyJson = getCurrencies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currencyJson.forEach(currency -> {
            switch (currency.getCcy()) {
                case "USD" -> USD = currency;
                case "EUR" -> EUR = currency;
                case "RUB" -> RUB = currency;
            }
        });
    }


    @Override
    public ArrayList<Float> uzsToOther(Float value) {
        Float usd = value / (USD.getRate());
        Float eur = value / (EUR.getRate());
        Float rub = value / (RUB.getRate());
        currencies.clear();
        currencies.addAll(List.of(usd, eur, rub));
        return currencies;
    }

    @Override
    public ArrayList<Float> usdToOther(Float value) {
        Float uzs = value * (USD.getRate());
        Float eur = (value * (USD.getRate())) / (EUR.getRate());
        Float rub = (value * (USD.getRate())) / (RUB.getRate());
        currencies.clear();
        currencies.addAll(List.of(uzs, eur, rub));
        return currencies;
    }

    @Override
    public ArrayList<Float> eurToOther(Float value) {
        Float uzs = value * (EUR.getRate());
        Float usd = (value * (EUR.getRate())) / (USD.getRate());
        Float rub = (value * (EUR.getRate())) / (RUB.getRate());
        currencies.clear();
        currencies.addAll(List.of(uzs, usd, rub));
        return currencies;
    }

    @Override
    public ArrayList<Float> rubToOther(Float value) {
        Float uzs = value * (RUB.getRate());
        Float usd = (value * (RUB.getRate())) / (USD.getRate());
        Float eur = (value * (RUB.getRate())) / (EUR.getRate());
        currencies.clear();
        currencies.addAll(List.of(uzs, usd, eur));
        return currencies;
    }

    @Override
    public ArrayList<Float> currency() {
        Float usd = (USD.getRate());
        Float eur = (EUR.getRate());
        Float rub = (RUB.getRate());
        currencies.clear();
        currencies.addAll(List.of(usd, eur, rub));
        return currencies;
    }

    public ArrayList<Currency> getCurrencies() throws IOException {
        URLConnection conn = null;
        try {
            URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Currency>>() {
        }.getType();
        if (conn == null) throw new AssertionError();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return gson.fromJson(reader, type);
    }

    private static final Calculate instance = new Calculate();

    public static Calculate getInstance() {
        return instance;
    }
}
