package com.techsdev.netutils.util;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Development on 9/7/2016.
 *
 * A collection of utilities to perform actions regarding @{link ByteBuf}
 */
public class BufferUtils {

    /**
     * Reads an UTF-8 string from the buffer
     * @param buffer The buffer to read from
     * @return The string that's read from the buffer
     * @throws IOException When an exception shows up converting the bytes to the string
     */
    public static String readUTF8(ByteBuf buffer) throws IOException {
        int length = readVarInt(buffer);
        byte[] text = new byte[length];
        buffer.readBytes(text);
        return new String(text, StandardCharsets.UTF_8);
    }

    /**
     * Writes an UTF-8 string to the buffer
     * @param buffer The buffer to write the string to
     * @param text The text to write to the buffer
     */
    public static void writeUTF8(ByteBuf buffer, String text) {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        writeVarInt(buffer, bytes.length);
        buffer.writeBytes(bytes);
    }

    /**
     * Reads an integer from the buffer
     * @param buf The buffer to read the integer from
     * @return The integer that's read from the buffer
     */
    public static int readVarInt(ByteBuf buf) {
        int value = 0;
        int i = 0;
        long b;
        while (((b = buf.readByte()) & 0x80L) != 0) {
            value |= (b & 0x7F) << i;
            i += 7;
        }
        return (int) (value | (b << i));
    }

    /**
     * Writes an integer to the buffer using the least amount of data as possible
     * @param buf The buffer to write the data to
     * @param value The value to write to the buffer
     */
    public static void writeVarInt(ByteBuf buf, int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            buf.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        buf.writeByte(value & 0x7F);
    }
}
