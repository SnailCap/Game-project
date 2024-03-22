package ee.taltech.survivors.client.input.inputHandlers;

import com.badlogic.gdx.Input;
import ee.taltech.survivors.world.character.MainCharacter;

public class PositionInputHandler extends InputHandler {
    MainCharacter mainCharacter;

    public PositionInputHandler(int keycode) {
        super(keycode);
        mainCharacter = MainCharacter.getINSTANCE();
    }

    public void handleKeyDown() {
        float velocityX = mainCharacter.getVelocityX();
        float velocityY = mainCharacter.getVelocityY();
        float movementSpeed = mainCharacter.getMovementSpeed();
        switch (keycode) {
            case Input.Keys.W:
                mainCharacter.setVelocityY(velocityY + movementSpeed);
                break;
            case Input.Keys.A:
                mainCharacter.setVelocityX(velocityX - movementSpeed);
                break;
            case Input.Keys.S:
                mainCharacter.setVelocityY(velocityY - movementSpeed);
                break;
            case Input.Keys.D:
                mainCharacter.setVelocityX(velocityX + movementSpeed);
                break;
        }
    }

    @Override
    public void handleKeyUp() {
        float velocityX = mainCharacter.getVelocityX();
        float velocityY = mainCharacter.getVelocityY();
        float movementSpeed = mainCharacter.getMovementSpeed();
        switch (keycode) {
            case Input.Keys.W:
                mainCharacter.setVelocityY(velocityY - movementSpeed);
                break;
            case Input.Keys.A:
                mainCharacter.setVelocityX(velocityX + movementSpeed);
                break;
            case Input.Keys.S:
                mainCharacter.setVelocityY(velocityY + movementSpeed);
                break;
            case Input.Keys.D:
                mainCharacter.setVelocityX(velocityX - movementSpeed);
                break;
        }
    }
}
