package example.com.techsdev.netutils.mcserver.protocol;

import com.techsdev.netutils.protocol.Protocol;

/**
 * Created by Development on 9/7/2016.
 *
 * An enumeration of all protocols registered in the Minecraft protocol that are available in this program
 */
public enum MinecraftProtocol {
    HANDSHAKE(new HandshakeProtocol()),
    STATUS(new StatusProtocol());

    private final Protocol protocol;

    MinecraftProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
