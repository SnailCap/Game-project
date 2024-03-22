package ee.taltech.survivors.network.packetHandlers.player;

import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packets.player.PacketPlayerPosition;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;

/**
 * The PacketPlayerPositionHandler class handles the processing of PacketPlayerPosition packets received by the server.
 * It updates the position of player characters in the game world.
 */
public class PacketPlayerPositionHandler extends PacketHandler {
    private PacketPlayerPosition packet;

    /**
     * Handles the received PacketPlayerPosition packet.
     *
     * @param receivedPacket The received PacketPlayerPosition packet.
     */
    @Override
    public void handle(Object receivedPacket) {
        ClientConnection connection = ClientConnection.getINSTANCE();
        packet = (PacketPlayerPosition) receivedPacket;

        // Check if player id is the client id. If so, no action is required.
        if (packet.getPlayerID() == connection.getClientID()) {
            return;
        }

        World world = World.getINSTANCE();

        // Retrieve the player character from the world manager using the player ID from the packet
        PlayerCharacter playerCharacter = world.getPlayersManager().getPlayerByID(packet.getPlayerID());

        // Update the position of the player character
        playerCharacter.setPosition(packet.getPositionX(), packet.getPositionY());

        //        log();}
    }

    private void log() {
        System.out.println("Position: " + packet.getPositionX() + " : " + packet.getPositionY());
    }
}
