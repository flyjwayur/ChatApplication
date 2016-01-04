package ChatSever1403729;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HyeSoo
 */
public class ChatEntry {

    private final String id;
    private final String timeStamp;
    private final String message;

    public ChatEntry(String id, String timeStamp, String messageStr) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = messageStr;
    }

    public String getId() {
        return this.id;
    }

    public String getTimestamp() {

        //timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return this.timeStamp;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "Entry Information> ID:" + this.id +" timeStamp: "+ this.timeStamp +" Messages: "+this.message;
    }

    
}
