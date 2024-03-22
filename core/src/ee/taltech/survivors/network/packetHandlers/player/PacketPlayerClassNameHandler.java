package ee.taltech.survivors.network.packetHandlers.player;

import com.badlogic.gdx.Gdx;
import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packets.player.PacketPlayerClassName;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;

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
                playerCharacter.setCharacterClassName(packet.getCharacterClassName());
                playerCharacter.getAnimationManager().initializeAnimations();
            }
        });

        log();
    }

    private void log() {
        System.out.println("Player(" + packet.getPlayerID() + ") class: " + packet.getCharacterClassName());
    }
}