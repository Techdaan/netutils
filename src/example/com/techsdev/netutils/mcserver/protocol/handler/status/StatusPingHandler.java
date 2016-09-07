package example.com.techsdev.netutils.mcserver.protocol.handler.status;

import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Handler;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusPingMessage;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusPingHandler implements Handler<StatusPingMessage> {
    @Override
    public void handle(Session sess, StatusPingMessage msg) {
        sess.send(msg);
    }
}
