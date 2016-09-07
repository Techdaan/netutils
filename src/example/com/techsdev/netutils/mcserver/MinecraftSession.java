package example.com.techsdev.netutils.mcserver;

import com.techsdev.netutils.protocol.Protocol;
import com.techsdev.netutils.session.Session;
import com.techsdev.netutils.util.Message;
import example.com.techsdev.netutils.mcserver.protocol.MinecraftProtocol;
import io.netty.channel.Channel;

/**
 * Created by Development on 9/7/2016.
 *
 * A session class ported so it fits well with the Minecraft stuff
 */
public class MinecraftSession implements Session {
    private Protocol protocol;
    private Channel channel;

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public MinecraftSession(Channel ch) {
        this.channel = ch;
        protocol = MinecraftProtocol.HANDSHAKE.getProtocol();
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public void disconnect() {
        channel.close();
    }

    @Override
    public void send(Message message) {
        channel.writeAndFlush(message);
    }

    @Override
    public void onChannelActive() {

    }

    @Override
    public void onDisconnect() {

    }
}
