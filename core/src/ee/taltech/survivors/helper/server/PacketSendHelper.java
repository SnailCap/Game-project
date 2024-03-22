package ee.taltech.survivors.helper.server;

import ee.taltech.survivors.account.Account;
import ee.taltech.survivors.network.connection.ClientConnection;
import ee.taltech.survivors.network.packets.player.PacketPlayerClassName;
import ee.taltech.survivors.network.packets.player.PacketPlayerName;
import ee.taltech.survivors.network.packets.player.PacketPlayerPosition;
import ee.taltech.survivors.network.packets.player.PacketPlayerVelocity;
import ee.taltech.survivors.world.character.MainCharacter;

/**
 * The PlayerInfoHelper class provides methods to retrieve player information and create PacketPlayerInfo objects.
 */
public class PacketSendHelper {
    public static void sendAllPlayerInfo() {
        ClientConnection connection = ClientConnection.getINSTANCE();
        MainCharacter mainCharacter = MainCharacter.getINSTANCE();
        Account account = Account.getINSTANCE();

        // Send name
        connection.sendTCPPacket(
                new PacketPlayerName(account.getPlayerName())
        );

        // Send position
        connection.sendTCPPacket(
                new PacketPlayerPosition(mainCharacter.getPositionX(), mainCharacter.getPositionY())
        );

        // Send velocity
        connection.sendTCPPacket(
                new PacketPlayerVelocity(mainCharacter.getVelocityX(), mainCharacter.getVelocityY())
        );

        // Send character class name
        connection.sendTCPPacket(
                new PacketPlayerClassName(mainCharacter.getCharacterClassName())
        );
    }
}
