package example.com.techsdev.netutils.mcserver.protocol.handler.status;

import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Handler;
import example.com.techsdev.netutils.mcserver.MinecraftServer;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusRequestMessage;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusResponseMessage;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusRequestHandler implements Handler<StatusRequestMessage> {
    @Override
    public void handle(Session sess, StatusRequestMessage msg) {
        String response = "{ \"version\": { \"name\": \""+MinecraftServer.VERSION_STRING+"\", \"protocol\": "+MinecraftServer.VERSION+" }, \"players\": { \"max\": 1, \"online\": 0 }, \"description\": { \"text\": \"Boring. Unformatted. Text.\nDon't connect! Doesn't work.\" } }";

        sess.send(new StatusResponseMessage(response));
    }
}
