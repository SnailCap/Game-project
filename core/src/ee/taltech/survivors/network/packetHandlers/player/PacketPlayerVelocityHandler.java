package ee.taltech.survivors.network.packetHandlers.player;

import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packets.player.PacketPlayerVelocity;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;

public class PacketPlayerVelocityHandler extends PacketHandler {
    PacketPlayerVelocity packet;

    @Override
    public void handle(Object receivedPacket) {
        packet = (PacketPlayerVelocity) receivedPacket;
        ClientConnection connection = ClientConnection.getINSTANCE();

        if (packet.getPlayerID() == connection.getClientID()) {
            return;
        }

        PlayerCharacter playerCharacter = World.getINSTANCE()
                .getPlayersManager()
                .getPlayerByID(packet.getPlayerID());

        playerCharacter.setVelocity(packet.getVelocityX(), packet.getVelocityY());

//        log();
    }

    private void log() {
        System.out.println(
                "PlayerVelocity(" + packet.getPlayerID() + "): " +
                        packet.getVelocityX() + " : " + packet.getVelocityY()
        );
    }
}
