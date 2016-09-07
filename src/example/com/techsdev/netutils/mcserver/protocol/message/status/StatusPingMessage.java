package example.com.techsdev.netutils.mcserver.protocol.message.status;

import com.techsdev.netutils.util.Message;

/**
 * Created by Development on 9/7/2016.
 */
public class StatusPingMessage implements Message {
    private final Long payload;

    public StatusPingMessage(Long payload) {
        this.payload = payload;
    }

    public long getPayload() {
        return payload;
    }
}
