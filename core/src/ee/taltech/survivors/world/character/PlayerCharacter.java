package ee.taltech.survivors.world.character;

/**
 * The PlayerCharacter class represents a player character in the game.
 */
public class PlayerCharacter extends GameCharacter {
    private final int playerID; // Unique identifier for the player
    private String playerName; // Name of the player

    public PlayerCharacter(int playerID) {
        super();
        this.playerID = playerID;
    }

    public int getID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
