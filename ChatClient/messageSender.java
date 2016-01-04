package soo.chatapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class messageSender implements Runnable {

    private String cMessage;
    private Socket chatSocket;
    private PrintWriter writer;


    public messageSender(Socket chatSocket, String cMessage) {
        this.chatSocket = chatSocket;
        this.cMessage = cMessage;
    }


    @Override
    public void run() {
        try {
            writer = new PrintWriter(chatSocket.getOutputStream(), true);
            while(true){
               //  System.out.println(cMessage + "clgt");
               // System.out.println("test cMessage");
                writer.println(cMessage);
                writer.flush();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
