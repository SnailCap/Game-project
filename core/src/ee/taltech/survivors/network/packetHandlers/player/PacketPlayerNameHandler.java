package ee.taltech.survivors.network.packetHandlers.player;

import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packets.player.PacketPlayerName;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.PlayerCharacter;

public class PacketPlayerNameHandler extends PacketHandler {
    PacketPlayerName packet;

    @Override
    public void handle(Object receivedPacket) {
        ClientConnection connection = ClientConnection.getINSTANCE();
        packet = (PacketPlayerName) receivedPacket;

        if (packet.getPlayerID() == connection.getClientID()) {
            return;
        }

        PlayerCharacter playerCharacter = World.getINSTANCE().getPlayersManager().getPlayerByID(packet.getPlayerID());
        playerCharacter.setPlayerName(packet.getPlayerName());

        log();
    }

    public void log() {
        System.out.println("Player(" + packet.getPlayerID() + ") name: " + packet.getPlayerName());
    }
}