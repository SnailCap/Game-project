package ee.taltech.survivors.client.input.inputHandlers;

public abstract class InputHandler {
    public int keycode;

    public InputHandler() {
    }

    public InputHandler(int keycode) {
        this.keycode = keycode;
    }

    public void handleKeyDown() {
    }

    public void handleKeyUp() {
    }
}
