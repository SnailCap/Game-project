package ee.taltech.survivors.client.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ee.taltech.survivors.client.input.inputHandlers.InputHandler;
import ee.taltech.survivors.client.input.inputHandlers.MouseInputHandler;
import ee.taltech.survivors.helper.mappings.InputToInputHandlerMap;

public class InputManager implements InputProcessor {
    private static InputManager INSTANCE;
    private OrthographicCamera camera;

    public static synchronized InputManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new InputManager();
        }
        return INSTANCE;
    }

    @Override
    public boolean keyDown(int keycode) {
        try {
            InputHandler inputHandler = InputToInputHandlerMap.getInputHandlerByKeycode(keycode);
            inputHandler.handleKeyDown();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        try {
            InputHandler inputHandler = InputToInputHandlerMap.getInputHandlerByKeycode(keycode);
            inputHandler.handleKeyUp();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        try {
            MouseInputHandler inputHandler = MouseInputHandler.getINSTANCE();
            inputHandler.setCamera(camera);
            inputHandler.handleTouchDown(screenX, screenY, button);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public boolean anyPositionKeyPressed() {
        return (Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.A) ||
                Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.D));
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
