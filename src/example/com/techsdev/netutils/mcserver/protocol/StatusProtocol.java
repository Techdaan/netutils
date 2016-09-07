package example.com.techsdev.netutils.mcserver.protocol;

import com.techsdev.netutils.protocol.BasicProtocol;
import example.com.techsdev.netutils.mcserver.protocol.codec.status.StatusPingCodec;
import example.com.techsdev.netutils.mcserver.protocol.codec.status.StatusRequestCodec;
import example.com.techsdev.netutils.mcserver.protocol.codec.status.StatusResponseCodec;
import example.com.techsdev.netutils.mcserver.protocol.handler.status.StatusPingHandler;
import example.com.techsdev.netutils.mcserver.protocol.handler.status.StatusRequestHandler;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusPingMessage;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusRequestMessage;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusResponseMessage;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusProtocol extends BasicProtocol {
    public StatusProtocol() {
        inbound(0x00, StatusRequestMessage.class, StatusRequestCodec.class, StatusRequestHandler.class);
        inbound(0x01, StatusPingMessage.class, StatusPingCodec.class, StatusPingHandler.class);

        outbound(0x00, StatusResponseMessage.class, StatusResponseCodec.class);
        outbound(0x01, StatusPingMessage.class, StatusPingCodec.class);
    }
}
