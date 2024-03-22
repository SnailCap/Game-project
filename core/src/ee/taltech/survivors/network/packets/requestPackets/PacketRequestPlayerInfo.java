package ee.taltech.survivors.server.packets.requestPackets;

import ee.taltech.survivors.server.packets.Packet;

public class PacketRequestPlayerInfo extends Packet {
    private int playerID;

    public PacketRequestPlayerInfo() {
    }

    public PacketRequestPlayerInfo(int clientID) {
        playerID = clientID;
    }

    public int getClientID() {
        return playerID;
    }
}
