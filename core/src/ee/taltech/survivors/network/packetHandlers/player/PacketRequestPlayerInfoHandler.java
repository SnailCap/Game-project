package ee.taltech.survivors.network.packetHandlers.player;

import ee.taltech.survivors.helper.server.PacketSendHelper;
import ee.taltech.survivors.network.packetHandlers.PacketHandler;

/**
 * The PacketRequestPlayerInfoHandler class handles the processing of PacketRequestPlayerInfo packets received by the server.
 * It responds to the client's request for player information by sending a packet containing player information.
 */
public class PacketRequestPlayerInfoHandler extends PacketHandler {

    @Override
    public void handle(Object receivedPacket) {
        // Send all players information
        PacketSendHelper.sendAllPlayerInfo();
    }
}
