package ee.taltech.survivors.server.packets.projectile;

import ee.taltech.survivors.server.packets.Packet;

public class PacketProjectilePosition extends Packet {
    private int playerID;

    private float positionX;
    private float positionY;

    public PacketProjectilePosition() {
    }

    public PacketProjectilePosition(int playerID, float positionX, float positionY) {
        this.playerID = playerID;
        this.positionX = positionX;
        this.positionY = positionY;
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
