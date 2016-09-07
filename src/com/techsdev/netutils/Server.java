package com.techsdev.netutils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by Development on 9/7/2016.
 *
 * A class that represents a server. People using the API are recommended to extend this class.
 */
public abstract class Server {
    /**
     * The {@link ServerBootstrap} used to create the Netty server.
     */
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * The {@link EventLoopGroup} used as the boss group
     */
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * The {@link EventLoopGroup} used as the worker group
     */
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public Server() {
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NetutilsChannelInitializer())
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    /**
     * Binds the server to a port. After binding to a port the server will be listening.
     * @param port The port to bind to
     * @return The @{link ChannelFuture} of this action
     */
    public ChannelFuture bind(int port) {
        return serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()) {
                    onBindSuccess();
                } else {
                    onBindFailure(future.cause());
                }
            }
        });
    }

    /**
     * Called when we successfully bound the server to the specified port
     */
    public abstract void onBindSuccess();

    /**
     * Called when the server failed to bind to the port.
     * @param t The exception that caused the failure. This may be a null.
     */
    public abstract void onBindFailure(Throwable t);

    /**
     * Call this to shut the server down.
     */
    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
