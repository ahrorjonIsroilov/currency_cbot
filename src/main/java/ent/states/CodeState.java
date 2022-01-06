package ent.states;

import ent.enums.CodeStates;

import java.util.HashMap;
import java.util.Map;

public class CodeState {

    private final static CodeState instance = new CodeState();

    public static CodeState getInstance() {
        return instance;
    }

    private static final Map<Long, CodeStates> codeState = new HashMap<>();

    public CodeStates getStateById(Long chatId) {
        for (Map.Entry<Long, CodeStates> entry : codeState.entrySet()) {
            if (entry.getKey().equals(chatId)) return entry.getValue();
        }
        return null;
    }

    public void setCodeState(Long chatId, CodeStates codeStates) {
        codeState.put(chatId, codeStates);
    }
}

