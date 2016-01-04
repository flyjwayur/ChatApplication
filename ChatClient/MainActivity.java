package soo.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class MainActivity extends Activity {


    private String chatMessage;

    // private Handler chandler;
    //private Message cMessage;
    //private Socket chatSocket = new Socket();

    private Handler uiHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==0){
                TextView allMessage = (TextView) findViewById(R.id.tv);
                allMessage.setText((String)msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = (Button) findViewById(R.id.sendBtn);
        final EditText clientInput = (EditText) findViewById(R.id.input); // Why it need 'final'??
        final TextView allMessage = (TextView) findViewById(R.id.tv);
        final Socket clientSocket = new Socket();
        //create client socket

        Thread threadR = new Thread(new chatReceiver(uiHandler, clientSocket));
        threadR.start();


        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chatMessage = clientInput.getText().toString();
                Thread threadS = new Thread(new messageSender(clientSocket, chatMessage));
                threadS.start();
                Log.d("Wow", "It is clicked!");
                // chatMessage =clientInput.getText().toString();
                Log.v("EditText", chatMessage); /* all the content is id in layout, so we need to use 'toString' ??*/
                allMessage.append(chatMessage);
               // allMessage.append("\n");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
