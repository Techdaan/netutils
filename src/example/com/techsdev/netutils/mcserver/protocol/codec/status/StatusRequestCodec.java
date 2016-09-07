package example.com.techsdev.netutils.mcserver.protocol.codec.status;

import com.techsdev.netutils.util.Codec;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusRequestMessage;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusRequestCodec implements Codec<StatusRequestMessage> {
    @Override
    public StatusRequestMessage decode(ByteBuf buf) throws IOException {
        return new StatusRequestMessage();
    }

    @Override
    public ByteBuf encode(ByteBuf buf, StatusRequestMessage msg) throws IOException {
        return buf;
    }
}
