package example.com.techsdev.netutils.mcserver.protocol.codec.status;

import com.techsdev.netutils.util.Codec;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusPingMessage;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusPingCodec implements Codec<StatusPingMessage> {
    @Override
    public StatusPingMessage decode(ByteBuf buf) throws IOException {
        return new StatusPingMessage(buf.readLong());
    }

    @Override
    public ByteBuf encode(ByteBuf buf, StatusPingMessage msg) throws IOException {
        buf.writeLong(msg.getPayload());
        return buf;
    }
}
