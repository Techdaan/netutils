package com.techsdev.netutils.util;

import com.techsdev.netutils.session.Session;

/**
 * Created by Development on 9/7/2016.
 *
 * An interface responsible for marking message handlers. Handlers handle the messages that are received.
 */
public interface Handler<T extends Message> {

    /**
     * Handles an incoming message
     * @param sess The @{link Session} this message was received from
     * @param msg The received @{link Message}
     */
    void handle(Session sess, T msg);

}
