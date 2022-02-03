package com.example.chat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat extends Activity {
    String user;
    MulticastSocket cl;
    EditText tx;
    Button bn;
    TextView tv;
    Bundle b;
    InetAddress gpo;
    String name;
    List<String> us = null;
    String msg, print;
    Handler handler = null;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat);
        b = new Bundle();
        handler = new Handler();
        b = getIntent().getExtras();
        name =  b.getString("user");
        String log = "<login>" + name;
        user = "<user:" +name + ">";
        tx = (EditText) findViewById(R.id.xetI);
        bn = (Button) findViewById(R.id.xbnA);
        tv = (TextView) findViewById(R.id.xtvC);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = user + tx.getText();
                byte[] b = msg.getBytes();
                DatagramPacket p = new DatagramPacket(b,b.length,gpo,7777);
                try {
                    cl.send(p);
                    tx.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                tv.append("\n"+print);
            }
        };
        try {
            cl = new MulticastSocket(7778);
            cl.setReuseAddress(true);
            gpo = InetAddress.getByName("230.0.0.1");
            cl.joinGroup(gpo);
            cl.setTimeToLive(255);
            byte[] b = log.getBytes();
            DatagramPacket p = new DatagramPacket(b,b.length,gpo,7777);
            cl.send(p);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;) {
                        try {
                            DatagramPacket p = new DatagramPacket(new byte[65535], 65535);
                            cl.receive(p);
                            msg = new String(p.getData(), 0, p.getLength());
                            if (msg.contains("<user:")) {
                                if (user.equals("")) {
                                    byte[] b = msg.getBytes();
                                    DatagramPacket po = new DatagramPacket(b, b.length, gpo, 7778);
                                    cl.send(po);
                                } else {
                                    String usuario = msg.substring(msg.indexOf(":") + 1, msg.indexOf(">"));
                                    print= usuario + ": " + msg.substring(msg.indexOf(">") + 1, msg.length());
                                    if(print != null){
                                        handler.post(runnableUi);
                                    }
                                    //tv.append(off+"\n");
                                    //System.out.println(off);
                                }

                            } else {
                                if (msg.contains("<login>")) {
                                    String lista = "<list>Conectado:\n";
                                    for (int i = 0; i < us.size(); i++) {
                                        lista = lista + us.get(i) + "\n";
                                    }
                                    String usr = msg.substring(msg.indexOf(">") + 1);
                                    us.add(usr);
                                    lista = lista + "<loged>" + usr;
                                    System.out.println(lista);
                                    byte[] b = lista.getBytes();
                                    DatagramPacket po = new DatagramPacket(b, b.length, gpo, 7778);
                                    cl.send(po);

                                } else {
                                    if (msg.contains("<list>")) {
                                        String nuevo = msg.substring(msg.indexOf("<loged>") + 7);
                                        if (nuevo.contains(name)) {
                                            print = msg.substring(6, msg.indexOf("<loged>") - 1);
                                            if(print != null){
                                                handler.post(runnableUi);
                                            }
                                            //tv.append(conec + "\n");
                                            //System.out.println(conec);
                                        } else {
                                            print = nuevo + " se ha conectado";
                                            if(print != null){
                                                handler.post(runnableUi);
                                            }
                                            //tv.append(nuevo + " se ha conectado\n");
                                            //System.out.println(nuevo + " se ha conectado");
                                        }
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
