package ee.taltech.survivors.server.packetHandlers;

/**
 * The PacketHandler class is an abstract class for handling packets received by the server.
 * Subclasses should implement the handle method to process specific types of packets.
 */
public abstract class PacketHandler {

    /**
     * Constructs a new PacketHandler instance.
     */
    public PacketHandler() {
    }

    /**
     * Handles the received packet.
     *
     * @param receivedPacket The packet received by the server.
     */
    public void handle(Object receivedPacket) {
        // Subclasses should override this method to handle specific types of packets
    }
}
