package example.com.techsdev.netutils.mcserver.protocol.codec.handshake;

import com.techsdev.netutils.util.BufferUtils;
import com.techsdev.netutils.util.Codec;
import example.com.techsdev.netutils.mcserver.protocol.message.handshake.HandshakeMessage;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class HandshakeCodec implements Codec<HandshakeMessage> {
    @Override
    public HandshakeMessage decode(ByteBuf buf) throws IOException {
        int protocolVersion = (int) BufferUtils.readVarInt(buf);
        String hostname = BufferUtils.readUTF8(buf);
        int port = buf.readUnsignedShort();
        int state = (int) BufferUtils.readVarInt(buf);

        return new HandshakeMessage(protocolVersion, hostname, port, state);
    }

    @Override
    public ByteBuf encode(ByteBuf buf, HandshakeMessage msg) throws IOException {
        BufferUtils.writeVarInt(buf, msg.getProtocolVersion());
        BufferUtils.writeUTF8(buf, msg.getHostname());
        buf.writeShort(msg.getPort());
        BufferUtils.writeVarInt(buf, msg.getState());

        return buf;
    }
}
