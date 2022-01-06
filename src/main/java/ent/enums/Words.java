package ent.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Words {
    WELCOME_TEXT1("<b><i>Hello dear </i></b>"),
    WELCOME_TEXT2("<b><i> 😊 This bot allows you to check current exchange rates.\n" +
                  "You can check the required amount by selecting the desired currency.</i></b>"),
    ENTER_AMOUNT("<b><i>Enter the required amount</i></b>"),
    ENTER_UZS("<b><i>Enter the Uzbek sum you want to calculate</i></b>"),
    ENTER_USD("<b><i>Enter the US dollar you want to calculate</i></b>"),
    ENTER_EUR("<b><i>Enter the amount of Euro you want to calculate</i></b>"),
    ENTER_RUB("<b><i>Enter the Russian ruble you want to calculate</i></b>"),
    ERROR("<b><i>⚠️ Please enter the correct value</i></b>"),
    LIMIT_ERROR("<b><i>Exceeded the limit value ‼️</i></b>"),
    WRONG_COMMAND("<b><i>There is no such command 😢</i></b>");

    private final String text;

    public String get() {
        return this.text;
    }
}
