package com.techsdev.netutils.util;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to easily register handlers in a registry to find them back by message
 */
public class HandlerRegistry {
    /**
     * The HashMap responsible for holding all message class -> handler conversions
     */
    private HashMap<Class<? extends Message>, Handler<?>> handlers = new HashMap<>();

    /**
     * Registers a new @{link Handler} in the registry
     * @param messageClazz The message that the handler's linked to
     * @param handlerClazz The handler's class itself
     */
    public <M extends Message, H extends Handler<? super M>> void register(Class<M> messageClazz, Class<H> handlerClazz) {
        try {
            Handler<? super M> handler = handlerClazz.newInstance();
            handlers.put(messageClazz, handler);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a handler by message class
     * @param messageClazz The class of the message
     * @return The handler
     */
    public <M extends Message> Optional<Handler<?>> getHandler(Class<M> messageClazz) {
        return Optional.ofNullable(handlers.get(messageClazz));
    }
}
