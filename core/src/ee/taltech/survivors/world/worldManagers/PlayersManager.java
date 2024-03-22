package ee.taltech.survivors.world.worldManagers;

import ee.taltech.survivors.world.character.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersManager implements WorldManager {
    private final Map<Integer, PlayerCharacter> players;

    public PlayersManager() {
        this.players = new HashMap<>();
    }

    public void addPlayer(PlayerCharacter player) {
        if (!players.containsValue(player)) {
            players.put(player.getID(), player);
        }
    }

    @Override
    public void update(float deltaTime) {
        if (!players.isEmpty()) {
            for (PlayerCharacter playerCharacter : getPlayersList()) {
                playerCharacter.update();
            }
        }
    }

    public PlayerCharacter getPlayerByID(int playerID) {
        return players.get(playerID);
    }

    public List<PlayerCharacter> getPlayersList() {
        return new ArrayList<>(players.values());
    }

    public void removePlayer(int playerId) {
        players.remove(playerId);
    }
}
