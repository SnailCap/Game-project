package ee.taltech.survivors.helper.mappings;

import com.badlogic.gdx.Input;
import ee.taltech.survivors.input.inputHandlers.InputHandler;
import ee.taltech.survivors.input.inputHandlers.PositionInputHandler;

import java.util.HashMap;
import java.util.Map;

public class InputToInputHandlerMap {
    private static final Map<Integer, InputHandler> INPUT_TO_HANDLER_MAP = new HashMap<>();

    static {
        // Main character position
        INPUT_TO_HANDLER_MAP.put(Input.Keys.W, new PositionInputHandler(Input.Keys.W));
        INPUT_TO_HANDLER_MAP.put(Input.Keys.A, new PositionInputHandler(Input.Keys.A));
        INPUT_TO_HANDLER_MAP.put(Input.Keys.S, new PositionInputHandler(Input.Keys.S));
        INPUT_TO_HANDLER_MAP.put(Input.Keys.D, new PositionInputHandler(Input.Keys.D));
    }

    public static InputHandler getInputHandlerByKeycode(int keycode) {
        return INPUT_TO_HANDLER_MAP.get(keycode);
    }
}
