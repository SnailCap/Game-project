package ee.taltech.survivors.server.packets.player;

import ee.taltech.survivors.server.packets.Packet;

public class PacketPlayerVelocity extends Packet {
    private float velocityX;
    private float velocityY;

    public PacketPlayerVelocity() {
    }

    public PacketPlayerVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public PacketPlayerVelocity(int playerID, float velocityX, float velocityY) {
        this.playerID = playerID;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getPlayerID() {
        return playerID;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }
}
