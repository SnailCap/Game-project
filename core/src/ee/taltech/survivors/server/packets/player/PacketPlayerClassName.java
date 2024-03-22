package ee.taltech.survivors.server.packets.player;

import ee.taltech.survivors.server.packets.Packet;
import ee.taltech.survivors.world.character.characterClass.CharacterClassName;

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
