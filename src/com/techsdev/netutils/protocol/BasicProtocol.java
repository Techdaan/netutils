package com.techsdev.netutils.protocol;

import com.techsdev.netutils.util.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.Buffer;
import java.util.Optional;

/**
 * Created by Development on 9/7/2016.
 *
 * A basic protocol that anyone can use and can be inspected to see how it's made.
 * Headers are simply the packet opcode. Nothing else.
 */
public class BasicProtocol extends Protocol {
    /**
     * The @{link CodecRegistry} responsible to hold all incoming codecs
     */
    private final CodecRegistry incoming = new CodecRegistry();

    /**
     * The @{link CodecRegistry} responsible to hold all outgoing codecs
     */
    private final CodecRegistry outgoing = new CodecRegistry();

    /**
     * The @{link HandlerRegistry} responsible to hold all the handlers
     */
    private final HandlerRegistry handlers = new HandlerRegistry();

    /**
     * Registers a new inbound message
     * @param opcode The opcode of the message
     * @param message The message itself (Class)
     * @param codec The codec of the message (Class)
     * @param handler The handler of the message (Class)
     */
    protected <M extends Message, C extends Codec<? super M>, H extends Handler<? super M>> void inbound(int opcode, Class<M> message, Class<C> codec, Class<H> handler) {
        incoming.register(opcode, message, codec);
        handlers.register(message, handler);
    }

    /**
     * Registers a new outbound message
     * @param opcode The opcode of the message
     * @param message The message itself (Class)
     * @param codec The codec of the message (Class)
     */
    protected <M extends Message, C extends Codec<? super M>> void outbound(int opcode, Class<M> message, Class<C> codec) {
        outgoing.register(opcode, message, codec);
    }

    @Override
    public ByteBuf writeHeader(ByteBuf buf, Message msg, int opcode, ByteBuf body) {
        Optional<CodecRegistry.CodecInformation> infoOptional = outgoing.getCodec(msg.getClass());

        ByteBuf opcodebuf = Unpooled.buffer();
        BufferUtils.writeVarInt(opcodebuf, opcode);
        BufferUtils.writeVarInt(buf, body.readableBytes() + opcodebuf.readableBytes());
        BufferUtils.writeVarInt(buf, opcode);
        opcodebuf.release();

        return buf;
    }

    @Override
    public Codec readHeader(ByteBuf buf) {
        int opcode = BufferUtils.readVarInt(buf);
        return getCodecIncoming(opcode);
    }

    @Override
    public Codec getCodecIncoming(int opcode) throws NullPointerException {
        Optional<Codec<?>> codec = incoming.getCodec(opcode);
        if(codec.isPresent()) {
            return codec.get();
        } else {
            throw new NullPointerException("There's no codec registered in this protocol with opcode '"+opcode+"'");
        }
    }

    @Override
    public Codec getCodecOutgoing(int opcode) throws NullPointerException {
        Optional<Codec<?>> codec = outgoing.getCodec(opcode);
        if(codec.isPresent()) {
            return codec.get();
        } else {
            throw new NullPointerException("There's no codec registered in this protocol with opcode '"+opcode+"'");
        }
    }

    @Override
    public Codec getCodecOutgoing(Message message) throws NullPointerException {
        Optional<CodecRegistry.CodecInformation> codec = outgoing.getCodec(message.getClass());
        if(codec.isPresent()) {
            return codec.get().getCodec();
        } else {
            throw new NullPointerException("There's no codec registered in this protocol for the message '"+message.getClass().getSimpleName()+"'");
        }
    }

    @Override
    public Handler getHandler(Message message) throws NullPointerException {
        Optional<Handler<?>> handler = handlers.getHandler(message.getClass());
        if(handler.isPresent()) {
            return handler.get();
        } else {
            throw new NullPointerException("There's no handler registered in this protocol for the message '"+message.getClass().getSimpleName()+"'");
        }
    }

    @Override
    public int getOpcode(Message message) {
        Optional<CodecRegistry.CodecInformation> codec = outgoing.getCodec(message.getClass());
        if(codec.isPresent()) {
            return codec.get().getOpcode();
        } else {
            throw new NullPointerException("There's no codec registered in this protocol for the message '"+message.getClass().getSimpleName()+"'");
        }
    }
}
