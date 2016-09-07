package example.com.techsdev.netutils.mcserver.protocol.handler.handshake;

import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Handler;
import example.com.techsdev.netutils.mcserver.MinecraftSession;
import example.com.techsdev.netutils.mcserver.protocol.MinecraftProtocol;
import example.com.techsdev.netutils.mcserver.protocol.message.handshake.HandshakeMessage;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class HandshakeHandler implements Handler<HandshakeMessage> {
    @Override
    public void handle(Session sess, HandshakeMessage msg) {
        if(msg.getState() == 2) {
            sess.disconnect();
            return;
        }

        MinecraftSession s = (MinecraftSession) sess;
        s.setProtocol(MinecraftProtocol.STATUS.getProtocol());
    }
}
