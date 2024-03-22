package ee.taltech.survivors.network.packets.player;

import ee.taltech.survivors.network.packets.Packet;

public class PacketPlayerPosition extends Packet {
    private float positionX;
    private float positionY;

    // No-argument constructor for kryoNet serialization
    public PacketPlayerPosition() {
    }

    public PacketPlayerPosition(float X, float Y) {
        positionX = X;
        positionY = Y;
    }

    public PacketPlayerPosition(int playerID, float X, float Y) {
        this.playerID = playerID;
        positionX = X;
        positionY = Y;
    }

    public int getPlayerID() {
        return playerID;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }
}
