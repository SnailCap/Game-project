package ee.taltech.survivors.helper.mappings;

import ee.taltech.survivors.network.packetHandlers.PacketHandler;
import ee.taltech.survivors.network.packetHandlers.player.*;
import ee.taltech.survivors.network.packets.Packet;
import ee.taltech.survivors.network.packets.player.*;

import java.util.HashMap;
import java.util.Map;

public class PacketToPacketHandlerMap {
    private static final Map<Class<? extends Packet>, PacketHandler> PACKET_TO_HANDLER_MAP = new HashMap<>();

    static {
        PACKET_TO_HANDLER_MAP.put(PacketAddPlayer.class, new PacketAddPlayerHandler());
        PACKET_TO_HANDLER_MAP.put(PacketRequestPlayerInfo.class, new PacketRequestPlayerInfoHandler());
        PACKET_TO_HANDLER_MAP.put(PacketPlayerPosition.class, new PacketPlayerPositionHandler());
        PACKET_TO_HANDLER_MAP.put(PacketPlayerDisconnect.class, new PacketPlayerDisconnectHandler());
        PACKET_TO_HANDLER_MAP.put(PacketPlayerName.class, new PacketPlayerNameHandler());
        PACKET_TO_HANDLER_MAP.put(PacketPlayerVelocity.class, new PacketPlayerVelocityHandler());
        PACKET_TO_HANDLER_MAP.put(PacketPlayerClassName.class, new PacketPlayerClassNameHandler());
    }

    public static Map<Class<? extends Packet>, PacketHandler> GET_PACKET_TO_HANDLER_MAP() {
        return PACKET_TO_HANDLER_MAP;
    }
}
