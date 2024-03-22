package ee.taltech.survivors.network.packets.player;

import ee.taltech.survivors.network.packets.Packet;

public class PacketPlayerDisconnect extends Packet {

    public PacketPlayerDisconnect() {
    }

    public PacketPlayerDisconnect(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
