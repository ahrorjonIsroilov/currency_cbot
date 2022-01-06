package ent.configs;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PConfig {
    private static final PConfig instance = new PConfig();

    public static PConfig getInstance() {
        return instance;
    }

    private static Properties p;

    static {
        try (FileReader reader = new FileReader("src/main/resources/app.properties")) {

            p = new Properties();
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return p.getProperty(key);
    }
}
