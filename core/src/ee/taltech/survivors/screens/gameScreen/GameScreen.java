package ee.taltech.survivors.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ee.taltech.survivors.client.GameClient;
import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.input.InputManager;
import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packets.player.PacketAddPlayer;
import ee.taltech.survivors.render.GameScreenRenderer;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.MainCharacter;

import static ee.taltech.survivors.helper.constants.Constants.VIEWPORT_HEIGHT;
import static ee.taltech.survivors.helper.constants.Constants.VIEWPORT_WIDTH;

/**
 * The GameScreen class represents the main game screen where gameplay occurs.
 * It handles rendering the game world, updating game logic, and handling user input.
 */
public class GameScreen extends ScreenAdapter {
    private World world;
    public float stateTime = 0f;

    private static OrthographicCamera camera;
    private final FitViewport viewport;

    private final SpriteBatch batch;
    private final MainCharacter mainCharacter;

    private final GameScreenRenderer screenRenderer;

    private final InputManager inputManager;

    /**
     * Constructs a new GameScreen instance.
     * Initializes various components required for rendering and managing the game screen.
     */
    public GameScreen(CharacterClassName characterClassName) {
        batch = new SpriteBatch();

        // Set up new world
        world = World.getINSTANCE();

        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // Get player
        mainCharacter = MainCharacter.getINSTANCE(characterClassName);

        // Set up viewport
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);

        // Get render manager
        screenRenderer = new GameScreenRenderer(this);

        // Set up inputManager
        inputManager = InputManager.getINSTANCE();
        inputManager.setCamera(camera);
        Gdx.input.setInputProcessor(inputManager);

        ClientConnection clientConnection = ClientConnection.getINSTANCE();
        clientConnection.sendTCPPacket(new PacketAddPlayer(
                clientConnection.getClientID()
        ));
    }

    @Override
    public void render(float deltaTime) {
        // update information
        update(deltaTime);
        screenRenderer.renderAll(stateTime);
    }

    private void update(float deltaTime) {
        stateTime += deltaTime;

        // If escape is pressed, end application process
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
        }

        // Update information about main character
        mainCharacter.update();

        // Update camera to follow player position
        camera.position.set(mainCharacter.getPositionX(), mainCharacter.getPositionY(), 0);
        camera.update();

        // Update information about the world
        world.update(deltaTime);

        // Send required info to server
        sendInfoToServer();
    }

    private void sendInfoToServer() {
        mainCharacter.sendPositionToServer();
        mainCharacter.sendVelocityToServer();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        // Dispose of resources and close the application
        GameClient.getINSTANCE().dispose();
    }
}
