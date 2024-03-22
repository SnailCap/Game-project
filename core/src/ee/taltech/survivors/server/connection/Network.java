package ee.taltech.survivors.server.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import ee.taltech.survivors.world.character.characterClass.CharacterClassName;

import java.util.HashMap;

import static ee.taltech.survivors.helper.server.PacketClassesList.GET_PACKET_CLASSES_LIST;

/**
 * The Network class handles the network configuration and packet registration for the client.
 */
public class Network {
    private final Client client;
    private final Kryo kryo;

    /**
     * Constructs a new Network instance.
     *
     * @param client The client to configure network settings for.
     */
    public Network(Client client) {
        this.client = client;
        this.kryo = client.getKryo();

        // Register HashMap class with Kryo
        kryo.register(HashMap.class);
    }

    /**
     * Registers all packet classes with Kryo.
     */
    public void registerRequirements() {
        // Register packets
        for (Class<?> packetClass : GET_PACKET_CLASSES_LIST()) {
            kryo.register(packetClass);
        }

        // Register CharacterClassName enum
        kryo.register(CharacterClassName.class);
    }
}
