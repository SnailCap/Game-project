package ee.taltech.survivors.server.packetHandlers.player;

import ee.taltech.survivors.helper.server.PlayerInfoHelper;
import ee.taltech.survivors.server.packetHandlers.PacketHandler;

/**
 * The PacketRequestPlayerInfoHandler class handles the processing of PacketRequestPlayerInfo packets received by the server.
 * It responds to the client's request for player information by sending a packet containing player information.
 */
public class PacketRequestPlayerInfoHandler extends PacketHandler {

    @Override
    public void handle(Object receivedPacket) {
        // Send all players information
        PlayerInfoHelper.sendAllPlayerInfo();
    }
}
