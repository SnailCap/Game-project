package ee.taltech.survivors.network.packetHandlers.player;

import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packets.player.PacketPlayerDisconnect;
import ee.taltech.survivors.world.World;

/**
 * The PacketPlayerDisconnectHandler class handles the processing of PacketPlayerDisconnect packets received by the server.
 * It removes disconnected players from the game world.
 */
public class PacketPlayerDisconnectHandler extends PacketHandler {
    private PacketPlayerDisconnect packet;
    private String playerName;

    /**
     * Handles the received PacketPlayerDisconnect packet.
     *
     * @param receivedPacket The received PacketPlayerDisconnect packet.
     */
    @Override
    public void handle(Object receivedPacket) {
        packet = (PacketPlayerDisconnect) receivedPacket;
        World world = World.getINSTANCE();
        // Get the name of the disconnected player
        playerName = world.getPlayersManager().getPlayerByID(packet.getPlayerID()).getPlayerName();

        // Remove the disconnected player from the world
        world.getPlayersManager().removePlayer(packet.getPlayerID());

        // Log the disconnection event
        log();
    }

    /**
     * Logs the event of a player disconnecting from the session.
     */
    private void log() {
        System.out.println("\n" +
                "PLAYER HAS DISCONNECTED!" + "\n" +
                "Name: " + playerName + "\n" +
                "ID: " + packet.getPlayerID() + "\n" +
                World.getINSTANCE().getPlayersManager().getPlayersList()
        );
    }
}
