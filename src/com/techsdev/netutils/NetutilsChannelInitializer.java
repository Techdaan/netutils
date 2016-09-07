package com.techsdev.netutils;

import com.techsdev.netutils.pipeline.MessageDecoder;
import com.techsdev.netutils.pipeline.MessageEncoder;
import com.techsdev.netutils.pipeline.MessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by Development on 9/7/2016.
 *
 * This class is used to register the correct pipeline for new connections
 */
public class NetutilsChannelInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * The server this NetutilsChannelInitializer is part of
     */
    private final Server server;

    public NetutilsChannelInitializer(Server server) {
        this.server = server;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // TODO Write this
        MessageHandler handler = new MessageHandler(server);
        MessageDecoder decoder = new MessageDecoder(handler);
        MessageEncoder encoder = new MessageEncoder(handler);

        channel.pipeline()
                .addLast("decoder", decoder)
                .addLast("encoder", encoder)
                .addLast("handler", handler);

        /*
         * ByteBuf --> Message [MessageDecoder] [decoder]
         * Message --> ByteBuf [MessageEncoder] [encoder]
         * Message --> Handler [MessageHandler] [handler]
         */
    }
}
