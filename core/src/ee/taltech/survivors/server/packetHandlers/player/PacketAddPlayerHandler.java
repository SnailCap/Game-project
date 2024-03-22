package ee.taltech.survivors.server.packetHandlers.player;

import ee.taltech.survivors.server.connection.ClientConnection;
import ee.taltech.survivors.server.packetHandlers.PacketHandler;
import ee.taltech.survivors.server.packets.player.PacketAddPlayer;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;

/**
 * The PacketAddPlayerHandler class handles the processing of PacketAddPlayer packets received by the server.
 * It adds new players to the game world.
 */
public class PacketAddPlayerHandler extends PacketHandler {
    private PacketAddPlayer packet;
    private World world;

    /**
     * Handles the received PacketAddPlayer packet.
     *
     * @param receivedPacket The received PacketAddPlayer packet.
     */
    @Override
    public void handle(Object receivedPacket) {
        packet = (PacketAddPlayer) receivedPacket;

        ClientConnection connection = ClientConnection.getINSTANCE();

        // Check if player id is the client id. If so, no action is required.
        if (packet.getPlayerID() == connection.getClientID()) {
            return;
        }

        // Create a new player character instance
        PlayerCharacter playerCharacter = new PlayerCharacter(packet.getPlayerID());

        world = World.getINSTANCE();

        // Add the player character to the game world
        world.getPlayersManager().addPlayer(playerCharacter);

        // Log the event
        log();
    }

    /**
     * Logs the event of a new player joining the session.
     */
    private void log() {
        System.out.println("\nPLAYER JOINED YOUR SESSION!");
    }
}
