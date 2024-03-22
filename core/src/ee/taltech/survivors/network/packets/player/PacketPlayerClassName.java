package ee.taltech.survivors.network.packets.player;

import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.network.packets.Packet;

public class PacketPlayerClassName extends Packet {
    private CharacterClassName characterClassName;

    public PacketPlayerClassName() {
    }

    public PacketPlayerClassName(CharacterClassName characterClassName) {
        this.characterClassName = characterClassName;
    }

    public PacketPlayerClassName(int playerID, CharacterClassName characterClassName) {
        this.playerID = playerID;
        this.characterClassName = characterClassName;
    }

    public CharacterClassName getCharacterClassName() {
        return characterClassName;
    }

    public int getPlayerID() {
        return playerID;
    }
}
