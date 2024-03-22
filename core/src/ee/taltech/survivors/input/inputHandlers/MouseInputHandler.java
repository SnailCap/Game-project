package ee.taltech.survivors.input.inputHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ee.taltech.survivors.world.abilities.Ability;
import ee.taltech.survivors.world.character.MainCharacter;

public class MouseInputHandler extends InputHandler {
    private static MouseInputHandler INSTANCE;

    private MainCharacter mainCharacter;
    private Vector2 mainCharacterPosition;
    private Ability mainAbility;

    private OrthographicCamera camera;

    public MouseInputHandler() {
        mainCharacter = MainCharacter.getINSTANCE();
        mainAbility = mainCharacter.getMainAbility();
        mainCharacterPosition = mainCharacter.getPosition();
    }

    public static synchronized MouseInputHandler getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MouseInputHandler();
        }
        return INSTANCE;
    }

    // Handle mouse button input
    public void handleTouchDown(int screenX, int screenY, int button) {
        switch (button) {
            case 0:
                mainAbility.execute(
                        mainCharacterPosition,
                        getMousePositionVector()
                );
            default:
                return;
        }
    }

    public Vector2 getMousePositionVector() {
        // Convert screen coordinates to world coordinates
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0); // Get mouse position
        camera.unproject(mousePos); // Convert mouse position to world coordinates

        return new Vector2(mousePos.x, mousePos.y); // Return mouse position relative to world coordinates
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
