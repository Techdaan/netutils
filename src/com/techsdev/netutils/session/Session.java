package com.techsdev.netutils.session;

import com.techsdev.netutils.protocol.Protocol;
import com.techsdev.netutils.util.Message;
import io.netty.channel.Channel;

/**
 * Created by Development on 9/7/2016.
 *
 * A class to representate a connection
 */
public interface Session {

    /**
     * Retrieves the channel this session is active on
     * @return The @{link Channel}
     */
    Channel getChannel();

    /**
     * Retrieves the protocol that the session's currently using
     * @return The protocol that the session's using
     */
    Protocol getProtocol();

    /**
     * Disconnects the client
     */
    void disconnect();

    /**
     * Sends a message to the session's user
     * @param message The message to send
     */
    void send(Message message);

    /**
     * Called when the channel's ready to receive and send data
     */
    void onChannelActive();

    /**
     * Called when the client disconnected
     */
    void onDisconnect();

}
