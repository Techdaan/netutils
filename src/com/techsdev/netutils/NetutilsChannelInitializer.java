package com.techsdev.netutils;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by Development on 9/7/2016.
 *
 * This class is used to register the correct pipeline for new connections
 */
public class NetutilsChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // TODO Write this


        /*
         * ByteBuf --> Message [MessageDecoder] [decoder]
         * Message --> ByteBuf [MessageEncoder] [encoder]
         * Message --> Handler [MessageHandler] [handler]
         */
    }
}
