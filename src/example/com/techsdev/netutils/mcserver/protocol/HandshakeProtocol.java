package example.com.techsdev.netutils.mcserver.protocol;

import com.techsdev.netutils.protocol.BasicProtocol;
import example.com.techsdev.netutils.mcserver.protocol.codec.handshake.HandshakeCodec;
import example.com.techsdev.netutils.mcserver.protocol.handler.handshake.HandshakeHandler;
import example.com.techsdev.netutils.mcserver.protocol.message.handshake.HandshakeMessage;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class HandshakeProtocol extends BasicProtocol {
    public HandshakeProtocol() {
        inbound(0x00, HandshakeMessage.class, HandshakeCodec.class, HandshakeHandler.class);
    }
}
