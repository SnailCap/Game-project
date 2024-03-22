package ee.taltech.survivors.network.packetHandlers;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ee.taltech.survivors.network.packets.Packet;

import java.util.HashMap;
import java.util.Map;

import static ee.taltech.survivors.helper.mappings.PacketToPacketHandlerMap.GET_PACKET_TO_HANDLER_MAP;

/**
 * The ClientPacketListener class listens for incoming packets from the server.
 * It routes the received packets to their respective handlers for processing.
 */
public class ClientPacketListener extends Listener {
    // Map to store packet handlers
    private final Map<Class<? extends Packet>, PacketHandler> packetHandlers = new HashMap<>();


    public ClientPacketListener() {
        // Every packet handler is mapped to a type of packet it should handle.
        // Packets to handlers map is located at helper/Constants class
        packetHandlers.putAll(GET_PACKET_TO_HANDLER_MAP());
    }

    /**
     * Handles the received packet.
     *
     * @param connection The connection over which the packet was received.
     * @param packet     The received packet.
     */
    @Override
    public void received(Connection connection, Object packet) {
        // Get the appropriate handler for the received packet
        PacketHandler handler = packetHandlers.get(packet.getClass());

        if (handler != null) {
            // If a handler is found, handle the packet
            handler.handle(packet);
        } else {
            // wrong packet handler
        }
    }
}
