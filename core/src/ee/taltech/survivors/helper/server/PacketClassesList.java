package ee.taltech.survivors.helper.server;

import ee.taltech.survivors.network.packets.Packet;
import ee.taltech.survivors.network.packets.player.*;
import ee.taltech.survivors.network.packets.projectile.PacketProjectilePosition;

import java.util.ArrayList;
import java.util.List;

public class PacketClassesList {
    public static final List<Class<? extends Packet>> PACKET_CLASSES_LIST = new ArrayList<>();

    static {
        // Player related packets
        PACKET_CLASSES_LIST.add(PacketRequestPlayerInfo.class);
        PACKET_CLASSES_LIST.add(PacketAddPlayer.class);
        PACKET_CLASSES_LIST.add(PacketPlayerPosition.class);
        PACKET_CLASSES_LIST.add(PacketPlayerDisconnect.class);
        PACKET_CLASSES_LIST.add(PacketPlayerVelocity.class);
        PACKET_CLASSES_LIST.add(PacketPlayerName.class);
        PACKET_CLASSES_LIST.add(PacketPlayerClassName.class);

        // Projectile related packets
        PACKET_CLASSES_LIST.add(PacketProjectilePosition.class);
    }

    public static List<Class<? extends Packet>> GET_PACKET_CLASSES_LIST() {
        return PACKET_CLASSES_LIST;
    }
}
