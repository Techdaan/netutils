package example.com.techsdev.netutils.mcserver.protocol.message.handshake;

import com.techsdev.netutils.util.Message;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class HandshakeMessage implements Message {
    private final int protocolVersion;
    private final String hostname;
    private final int port;
    private final int state;

    public HandshakeMessage(int pv, String h, int p, int s) {
        this.protocolVersion = pv;
        this.hostname = h;
        this.port = p;
        this.state = s;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public int getState() {
        return state;
    }
}
