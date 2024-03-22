package ee.taltech.survivors.network.packets.player;

import ee.taltech.survivors.network.packets.Packet;

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
