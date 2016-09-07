package example.com.techsdev.netutils.mcserver.protocol.codec.status;

import com.techsdev.netutils.util.BufferUtils;
import com.techsdev.netutils.util.Codec;
import example.com.techsdev.netutils.mcserver.protocol.message.status.StatusResponseMessage;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusResponseCodec implements Codec<StatusResponseMessage> {
    @Override
    public StatusResponseMessage decode(ByteBuf buf) throws IOException {
        return new StatusResponseMessage(BufferUtils.readUTF8(buf));
    }

    @Override
    public ByteBuf encode(ByteBuf buf, StatusResponseMessage msg) throws IOException {
        BufferUtils.writeUTF8(buf, msg.getData());
        return buf;
    }
}
