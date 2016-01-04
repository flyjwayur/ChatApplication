package soo.chatapp;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class chatReceiver implements Runnable {

    private int PORT = 12356;
    private String HOST = "192.168.1.48";
    private Socket chatSocket;
    private Handler uiHandler;
    private BufferedReader in;
    private String chatMessage; // Class Message ??

    public chatReceiver(Handler uiHandler, Socket chatSocket) {
        this.uiHandler = uiHandler;
        this.chatSocket = chatSocket;
    }

    public void run() {
        try {
            chatSocket.connect(new InetSocketAddress(HOST, PORT));
            System.out.println("You connected to " + HOST);
            // InputStreamReader streamReader = new InputStreamReader(chatSocket.getInputStream());
            in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));

            while(true) {
                chatMessage = in.readLine();
                //if("".equals(chatMessage)){
                    //System.out.println("test check whether null or not!!");
                //}
                Message msg = this.uiHandler.obtainMessage();
                msg.what = 0;
                msg.obj = chatMessage;
                this.uiHandler.sendMessage(msg);
                //break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



