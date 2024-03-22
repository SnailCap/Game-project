package ee.taltech.survivors.server.packetHandlers.player;

import com.badlogic.gdx.Gdx;
import ee.taltech.survivors.server.connection.ClientConnection;
import ee.taltech.survivors.server.packetHandlers.PacketHandler;
import ee.taltech.survivors.server.packets.player.PacketPlayerClassName;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;
import ee.taltech.survivors.world.character.characterClass.PlayerClass;

public class PacketPlayerClassNameHandler extends PacketHandler {
    private PacketPlayerClassName packet;

    @Override
    public void handle(Object receivedPacket) {
        ClientConnection connection = ClientConnection.getINSTANCE();
        packet = (PacketPlayerClassName) receivedPacket;

        if (packet.getPlayerID() == connection.getClientID()) {
            return;
        }

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                PlayerCharacter playerCharacter = World.getINSTANCE().getPlayersManager().getPlayerByID(packet.getPlayerID());
                playerCharacter.setCharacterClass(new PlayerClass(packet.getCharacterClassName()));
            }
        });

        log();
    }

    private void log() {
        System.out.println("Player(" + packet.getPlayerID() + ") class: " + packet.getCharacterClassName());
    }
}