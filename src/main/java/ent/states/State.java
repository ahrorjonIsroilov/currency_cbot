package ent.states;

import ent.enums.ActionStates;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class State {
    private final static State state = new State();
    private final Map<Long, ActionStates> menuState = new HashMap<>();

    public static State getInstance() {
        return state;
    }

    public ActionStates getState(Long chatId) {
        for (Map.Entry<Long, ActionStates> entry : menuState.entrySet()) {
            if (entry.getKey().equals(chatId)) return entry.getValue();
        }
        return null;
    }


    public void setState(long chatID, ActionStates nemMenu) {
        menuState.put(chatID, nemMenu);
    }
}
