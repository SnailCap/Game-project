package ee.taltech.survivors.network.connection;

import com.esotericsoftware.kryonet.Client;
import ee.taltech.survivors.network.packetHandlers.ClientPacketListener;
import ee.taltech.survivors.network.packets.Packet;

import java.io.IOException;

import static ee.taltech.survivors.helper.constants.Constants.TCP_PORT;
import static ee.taltech.survivors.helper.constants.Constants.UDP_PORT;

/**
 * The ClientConnection class manages the connection between the client and the server.
 */
public class ClientConnection {
    private static ClientConnection INSTANCE;
    private static Client connection;
    private int sessionID;

    /**
     * Constructs a new ClientConnection instance.
     * It initializes the client connection, registers required packets, and initializes the packet listener.
     */
    private ClientConnection() {
        createConnection();
        registerRequiredPackets();
        initializePacketListener();
    }

    /**
     * Retrieves the singleton instance of ClientConnection.
     *
     * @return The singleton instance of ClientConnection.
     */
    public static synchronized ClientConnection getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ClientConnection();
        }
        return INSTANCE;
    }

    /**
     * Creates the client connection to the server.
     */
    private void createConnection() {
        connection = new Client();
        connection.start();
        try {
            connection.connect(5000, "localhost", TCP_PORT, UDP_PORT); // Connect to the server
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers required packets for communication with the server.
     */
    private void registerRequiredPackets() {
        Network network = new Network(connection);
        network.registerRequirements();
    }

    /**
     * Initializes the packet listener to handle incoming packets from the server.
     */
    private void initializePacketListener() {
        connection.addListener(new ClientPacketListener());
    }

    /**
     * Disposes the client connection.
     */
    public void disposeConnection() {
        connection.close(); // Close the client connectionawd
        try {
            connection.dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a UDP packet to the server.
     *
     * @param packet The packet to be sent.
     */
    public void sendUDPPacket(Packet packet) {
        connection.sendUDP(packet);
    }

    /**
     * Sends a TCP packet to the server.
     *
     * @param packet The packet to be sent.
     */
    public void sendTCPPacket(Packet packet) {
        connection.sendTCP(packet);
    }

    /**
     * Retrieves the client ID.
     *
     * @return The client ID.
     */
    public int getClientID() {
        return connection.getID();
    }

    /**
     * Retrieves the session ID.
     *
     * @return The session ID.
     */
    public int getSessionID() {
        return sessionID;
    }

    /**
     * Sets the session ID.
     *
     * @param sessionID The session ID to be set.
     */
    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}
