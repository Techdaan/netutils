package com.techsdev.netutils.pipeline;

import com.techsdev.netutils.protocol.Protocol;
import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Codec;
import com.techsdev.netutils.util.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to encode the messages that are going to be sent
 */
public class MessageEncoder extends MessageToMessageEncoder<Message> {

    /**
     * The @{link MessageHandler} associated with this encoder
     */
    private MessageHandler handler;

    public MessageEncoder(MessageHandler handler) {
        this.handler = handler;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        Session sess = handler.getSession();
        Protocol protocol = sess.getProtocol();

        Codec codec = protocol.getCodecOutgoing(msg);
        int opcode = protocol.getOpcode(msg);

        ByteBuf body = Unpooled.buffer();
        body = codec.encode(body, msg);

        ByteBuf header = protocol.writeHeader(Unpooled.buffer(), msg, opcode, body);

        out.add(Unpooled.wrappedBuffer(header, body));
    }
}
