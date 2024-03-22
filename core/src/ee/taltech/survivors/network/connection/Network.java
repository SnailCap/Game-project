package ee.taltech.survivors.network.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import ee.taltech.survivors.helper.enums.CharacterClassName;

import java.util.HashMap;

import static ee.taltech.survivors.helper.server.PacketClassesList.GET_PACKET_CLASSES_LIST;

/**
 * The Network class handles the network configuration and packet registration for the client.
 */
public class Network {
    private final Kryo kryo;

    /**
     * Constructs a new Network instance.
     *
     * @param client The client to configure network settings for.
     */
    public Network(Client client) {
        this.kryo = client.getKryo();
    }

    /**
     * Registers all packet classes with Kryo.
     */
    public void registerRequirements() {
        kryo.register(HashMap.class);

        // Register packets
        for (Class<?> packetClass : GET_PACKET_CLASSES_LIST()) {
            kryo.register(packetClass);
        }

        // Register CharacterClassName enum
        kryo.register(CharacterClassName.class);
    }
}
