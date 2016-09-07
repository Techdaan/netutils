package com.techsdev.netutils.util;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Development on 9/7/2016.
 *
 * An interface responsible for marking codecs. A codec converts ByteBufs into Messages and the other way round.
 */
public interface Codec<T extends Message> {

    /**
     * Decodes the supplied {@link ByteBuf} into a {@link Message}
     * @param buf The buffer to translate
     * @return The message
     */
    T decode(ByteBuf buf) throws IOException;

    /**
     * Encodes the supplied {@link Message} into the supplied {@link ByteBuf}
     * @param buf The buffer to write the data to. This should be empty.
     * @param msg The message to write to the buffer
     * @return The buffer the data is written to
     */
    ByteBuf encode(ByteBuf buf, T msg) throws IOException;

}
