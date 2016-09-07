package com.techsdev.netutils.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to easily register codecs in a registry to find them back by message or by opcode
 */
public class CodecRegistry {

    /**
     * The HashMap that contains all opcode -> codec references
     */
    private HashMap<Integer, Codec> codecs = new HashMap<>();

    /**
     * The HashMap that contains all message (Class) -> CodecInformation references
     */
    private HashMap<Class<? extends Message>, CodecInformation> messages = new HashMap<>();

    /**
     * Retrieves a codec by opcode
     * @param opcode The opcode of the codec
     * @return The corresponding codec
     */
    public Optional<Codec<?>> getCodec(int opcode) {
        return Optional.ofNullable(codecs.get(opcode));
    }

    /**
     * Retrieves a codec's information by the message class
     * @param messageClazz The class of the message
     * @return The corresponding codec
     */
    public <M extends Message> Optional<CodecInformation> getCodec(Class<M> messageClazz) {
        return Optional.ofNullable(messages.get(messageClazz));
    }

    /**
     * Registers a new @{link Codec} in the registry
     * @param opcode The opcode of the codec
     * @param messageClazz The message class that's linked to the codec
     * @param codecClazz The codec's class
     */
    public <M extends Message, C extends Codec<? super M>> void register(int opcode, Class<M> messageClazz, Class<C> codecClazz) {
        Codec c;
        try {
            Constructor<C> constructor = codecClazz.getConstructor();
            constructor.setAccessible(true);
            c = constructor.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

        codecs.put(opcode, c);
        messages.put(messageClazz, new CodecInformation(opcode, c));
    }


    /**
     * A class that is responsible to hold both the opcode and the codec
     */
    public class CodecInformation {
        /**
         * The opcode linked to the codec
         */
        private final int opcode;

        /**
         * The codec that's being represented
         */
        private final Codec codec;

        public CodecInformation(int opcode, Codec codec) {
            this.opcode = opcode;
            this.codec = codec;
        }

        /**
         * Gets the opcode
         * @return The opcode
         */
        public int getOpcode() {
            return opcode;
        }

        /**
         * Gets the {@link Codec}
         * @return The codec
         */
        public Codec getCodec() {
            return codec;
        }
    }
}
