package com.techsdev.netutils.protocol;

import com.techsdev.netutils.util.Codec;
import com.techsdev.netutils.util.Handler;
import com.techsdev.netutils.util.Message;
import io.netty.buffer.ByteBuf;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to describe a protocol
 */
public abstract class Protocol {

    /**
     * Writes the header of a message to an outgoing bytebuf
     * @param buf The buffer to write the header to. This should be an empty buffer
     * @param msg The message whose header should be written to the buffer
     * @param opcode The opcode of the message
     * @param body The body of the message
     * @return The buffer with the header written in it
     */
    public abstract ByteBuf writeHeader(ByteBuf buf, Message msg, int opcode, ByteBuf body);

    /**
     * Reads the header from an incoming bytebuf, returning the corresponding codec
     * @param buf The buffer to read the header from
     * @return The codec that can be used to decode the buffer
     */
    public abstract Codec readHeader(ByteBuf buf);

    /**
     * Retrieves an incoming codec by opcode
     * @param opcode The opcode to get the codec from
     * @return The codec for the opcode
     */
    public abstract Codec getCodecIncoming(int opcode);

    /**
     * Retrieves an outgoing codec by opcode
     * @param opcode The opcode to get the codec from
     * @return The codec for the opcode
     */
    public abstract Codec getCodecOutgoing(int opcode);

    /**
     * Retrieves a codec for an outgoing packet by message class
     * @param message The message to get the codec from
     * @return The codec for the message
     */
    public abstract Codec getCodecOutgoing(Message message);

    /**
     * Retrieves the handler associated with the provided {@link Message}
     * @param message The message to get the handler from
     * @return The handler for the message
     */
    public abstract Handler getHandler(Message message);

    /**
     * Retrieves the opcode associated with the provided {@link Message}
     * @param message The message to get the opcode from
     * @return The opcode for the messaeg
     */
    public abstract int getOpcode(Message message);
}
