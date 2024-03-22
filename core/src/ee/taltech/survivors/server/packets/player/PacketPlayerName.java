package ee.taltech.survivors.server.packets.player;

import ee.taltech.survivors.server.packets.Packet;

public class PacketPlayerName extends Packet {
    private String playerName;

    public PacketPlayerName() {
    }

    public PacketPlayerName(String playerName) {
        this.playerName = playerName;

    }

    public PacketPlayerName(int playerID, String playerName) {
        this.playerID = playerID;
        this.playerName = playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
