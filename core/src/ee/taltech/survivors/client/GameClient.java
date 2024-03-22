package ee.taltech.survivors.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import ee.taltech.survivors.account.Account;
import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.screens.gameScreen.GameScreen;
import ee.taltech.survivors.screens.menuScreen.MenuScreen;
import ee.taltech.survivors.world.character.MainCharacter;

/**
 * The GameClient class represents the main entry point for the game client.
 * It extends the LibGDX Game class and manages game initialization and disposal.
 */
public class GameClient extends Game {
    private static GameClient INSTANCE;

    private Account account;
    private ClientConnection connection;
    private MainCharacter mainCharacter;
    private GameScreen currentScreen;

    /**
     * Retrieves the singleton instance of the GameClient.
     *
     * @return The singleton instance of GameClient.
     */
    public static synchronized GameClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameClient();
        }
        return INSTANCE;
    }

    @Override
    public void create() {
        // Initialize account
        account = Account.getINSTANCE();

        // Set screen
        setScreen(new MenuScreen());

        // Initialize connection
        connection = ClientConnection.getINSTANCE();
    }

    @Override
    public void dispose() {
        connection.disposeConnection();
        Gdx.app.exit();
    }
}
