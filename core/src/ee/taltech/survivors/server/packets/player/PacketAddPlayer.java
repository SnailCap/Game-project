package ee.taltech.survivors.server.packets.player;

import ee.taltech.survivors.server.packets.Packet;

public class PacketAddPlayer extends Packet {

    // No-argument constructor for kryoNet serialization
    public PacketAddPlayer() {
    }

    public PacketAddPlayer(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
