package example.com.techsdev.netutils.mcserver.protocol.message.status;

import com.techsdev.netutils.util.Message;

/**
 * Created by Development on 9/7/2016.
 *
 * Refer to wiki.vg for more information
 */
public class StatusResponseMessage implements Message {
    private final String data;

    public StatusResponseMessage(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
