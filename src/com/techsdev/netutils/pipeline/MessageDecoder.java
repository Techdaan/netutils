package com.techsdev.netutils.pipeline;

import com.techsdev.netutils.protocol.Protocol;
import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.BufferUtils;
import com.techsdev.netutils.util.Codec;
import com.techsdev.netutils.util.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to decode received messages
 */
public class MessageDecoder extends ByteToMessageDecoder {

    /**
     * The @{link MessageHandler} associated with this decoder
     */
    private MessageHandler handler;

    public MessageDecoder(MessageHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf inbuf, List<Object> out) throws Exception {
        if(inbuf.readableBytes()==0)
            return;

        inbuf.markReaderIndex();
        int length = BufferUtils.readVarInt(inbuf);

        if(inbuf.readableBytes() < length) {
            inbuf.resetReaderIndex();
            return;
        }

        ByteBuf buf = inbuf.readBytes(length);
        //Codec codec = handler.getSession().getProtocol().readHeader(buf);


        Session sess = handler.getSession();
        Protocol protocol = sess.getProtocol();
        Codec codec = protocol.readHeader(buf);

        Message msg = codec.decode(buf);
        out.add(msg);
    }
}
