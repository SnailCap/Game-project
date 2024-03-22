package ee.taltech.survivors.world.character;

import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.input.InputManager;
import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packets.player.PacketPlayerPosition;
import ee.taltech.survivors.network.packets.player.PacketPlayerVelocity;

import static ee.taltech.survivors.helper.constants.Constants.MAIN_CHARACTER_DEFAULT_MOVEMENT_SPEED;

/**
 * The MainCharacter class represents the main character of the game.
 * It extends the GameCharacter class and defines the main character's behavior.
 */
public class MainCharacter extends GameCharacter {

    private static MainCharacter INSTANCE;
    private final ClientConnection connection;
    private float movementSpeed = MAIN_CHARACTER_DEFAULT_MOVEMENT_SPEED;
    private final InputManager inputManager;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private MainCharacter(CharacterClassName characterClassName) {
        super(characterClassName); // Initialize with character class
        connection = ClientConnection.getINSTANCE(); // Obtain the client connection instance
        inputManager = new InputManager();
    }

    public static synchronized MainCharacter getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Gets the singleton instance of the MainCharacter class.
     *
     * @return The MainCharacter instance.
     */
    public static synchronized MainCharacter getINSTANCE(CharacterClassName characterClassName) {
        if (INSTANCE == null) {
            INSTANCE = new MainCharacter(characterClassName);
        }
        return INSTANCE;
    }

    /**
     * Sends the main character's position to the server.
     */
    public void sendPositionToServer() {
        connection.sendUDPPacket(new PacketPlayerPosition(
                b2bodyManager.getB2body().getPosition().x,
                b2bodyManager.getB2body().getPosition().y)
        );
    }

    public void sendVelocityToServer() {
        connection.sendUDPPacket(new PacketPlayerVelocity(
                getVelocityX(),
                getVelocityY())
        );
    }

    /**
     * Updates the main character's position based on user input.
     */
    @Override
    public void updatePosition() {
        // Check if any position keys are pressed. If not, set velocity to 0
        if (!inputManager.anyPositionKeyPressed()) {
            velocity.x = 0f;
            velocity.y = 0f;
        }

        // Set the linear velocity of the Box2D body
        b2bodyManager.setB2bodyLinearVelocity(velocity.x, velocity.y);

        // Update the position of the main character
        setPosition(b2bodyManager.getB2body().getPosition());
    }

    /**
     * Gets the movement speed of the main character.
     *
     * @return The movement speed.
     */
    public float getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets the movement speed of the main character.
     *
     * @param movementSpeed The movement speed to set.
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
