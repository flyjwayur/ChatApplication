package ChatSever1403729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author HyeSoo
 */
class InputOutputHandler implements HistoryObserver, Runnable {

    private String messages;
    private String id;
    private String timeStamp;
    private Socket socket1;
    private ChatHistory history;
    private PrintWriter writer;
    // private ChatEntry entry;

    public InputOutputHandler(Socket s, ChatHistory h) {
        this.socket1 = s;
        this.history = h;
    }

    @Override
    public void run() {
        try {
            InputStreamReader streamReader = new InputStreamReader(socket1.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket1.getOutputStream(), true);
            //TO-Do
            if ("".equals(this.messages)) {
                System.out.println("null mes");
            }
            this.messages = reader.readLine();
            do {
                this.id = "" + socket1.getLocalPort();
                // System.out.println("test mes");
                if ("".equals(this.messages)) {
                    System.out.println("null mes");
                }
                //this.id = socket1.getInetAddress().toString();           
                this.timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                ChatEntry entry = new ChatEntry(this.id, this.timeStamp, this.messages);
                ChatHistory.getInstance().add(entry, this);
                //this.newEntry(entry);

                //writer.println("You can send a message to server :D ");
                System.out.println("You can send a message to server :D ");
                System.out.println(entry);
                writer.println(history.getHistory());
                // writer.flush();
                this.messages = reader.readLine();
            } while (!"".equals(this.messages));
//            while(!"".equals(this.messages)){
//                //this.id=socket1.getLocalPort().toString();
//                this.id = "" + socket1.getLocalPort();
//                System.out.println("test mes");
//                if ("".equals(this.messages)){
//                    System.out.println("null mes");
//                }
//                //this.id = socket1.getInetAddress().toString();           
//                this.timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//                ChatEntry entry = new ChatEntry(this.id, this.timeStamp, this.messages);
//                ChatHistory.getInstance().add(entry, this);
//                //this.newEntry(entry);
//
//                writer.println("You can send a message to server :D ");
//                System.out.println("You can send a message to server :D ");
//                System.out.println("Your Message: " + entry.getStr());
//                System.out.println(entry);
//                writer.println(entry);
//                writer.flush();
//                this.messages = reader.readLine();
//            }
        } catch (IOException ex) {
            Logger.getLogger(InputOutputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public String getMessages() {
     return messages;
     }*/
    @Override
    public void newEntry(ChatEntry entry) {
        try {
            writer = new PrintWriter(socket1.getOutputStream());
            if (!entry.getMessage().equals("")) {
                //writer.println(history.getHistory());
                writer.println(entry);
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ChatHistory ch = ChatHistory.getInstance();
        //ch.add(entry);
        //System.out.println("Entry now just entered:" + entry);
        //writer.println("Entry now just entered:" + entry);
        //System.out.println("<<<<<It's a chat history>>>>> ");
        //ch.showHistory();
    }

//    private void cleanUpWriter(PrintWriter writer) {
//        writer.flush();
//        writer.println("Moi Moi");
//        writer.close();
//    }
}
