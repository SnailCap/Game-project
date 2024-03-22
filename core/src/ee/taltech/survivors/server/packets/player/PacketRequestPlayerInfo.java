package ee.taltech.survivors.server.packets.player;

import ee.taltech.survivors.server.packets.Packet;

public class PacketRequestPlayerInfo extends Packet {
    private int playerID;

    public PacketRequestPlayerInfo() {
    }

    public PacketRequestPlayerInfo(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
