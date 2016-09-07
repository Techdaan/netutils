package example.com.techsdev.netutils.mcserver;

import com.techsdev.netutils.Server;
import com.techsdev.netutils.session.Session;
import io.netty.channel.Channel;

/**
 * Created by Development on 9/7/2016.
 *
 * Small testing server. Shows a simple message when pinged from the server list
 */
public class MinecraftServer extends Server {
    public static final int PORT = 25565;
    public static final int VERSION = 210;
    public static final String VERSION_STRING = "1.10.2";

    @Override
    public Session createSession(Channel channel) {
        return new MinecraftSession(channel);
    }

    @Override
    public void onBindSuccess() {
        System.out.println("Successfully bound to port "+PORT);
    }

    @Override
    public void onBindFailure(Throwable t) {
        System.out.println("Failed to bind to port: "+t.getMessage());
        System.exit(1);
    }



    public static void main(String[] args) {
        MinecraftServer server = new MinecraftServer();
        server.bind(PORT);
    }
}
