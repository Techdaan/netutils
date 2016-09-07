package com.techsdev.netutils.pipeline;

import com.techsdev.netutils.Server;
import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Handler;
import com.techsdev.netutils.util.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Development on 9/7/2016.
 *
 * A class that's responsible to handle data exchanging as well as holding the session
 */
public class MessageHandler extends SimpleChannelInboundHandler<Message> {
    /**
     * The session that's linked to this message handler
     */
    private AtomicReference<Session> session = new AtomicReference<>(null);

    /**
     * The session's server
     */
    private final Server server;

    public MessageHandler(Server server) {
        this.server = server;
    }


    @Override
    @SuppressWarnings("unchecked")
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        Handler handler = session.get().getProtocol().getHandler(message);
        handler.handle(session.get(), message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Session sess = server.createSession(ctx.channel());
        sess.onChannelActive();
        session.set(sess);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        session.get().onDisconnect();
    }

    /**
     * Retrieves this MessageHandler's session
     * @return The session of this message handler
     */
    public Session getSession() {
        return session.get();
    }

}
