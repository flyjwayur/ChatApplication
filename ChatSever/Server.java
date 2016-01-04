package ChatSever1403729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HyeSoo
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    //private static int PORT = 12345;
    public static void main(String[] args) throws IOException {
        try {
            final int PORT = 12356;
            int Backlog = 5;
            //String HOST = "127.0.0.1";
            ChatHistory h = ChatHistory.getInstance();
            // h.add(null);
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println(ss.getLocalPort());
            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected from "+s.getLocalAddress().getHostName());
                InputOutputHandler ioHandler = new InputOutputHandler(s, h);
                ChatHistory.getInstance().addObserver(ioHandler);
                Thread t = new Thread(ioHandler);
                t.start();
                System.out.println("Hello!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*entry.setStr(ioHandler.getMessages());
 System.out.println(entry.getStr());*/
