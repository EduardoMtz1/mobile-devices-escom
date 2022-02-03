package com.example.chat;

import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Read extends Thread{
    private MulticastSocket con;
    private List<String> us = null;
    private InetAddress gpo;
    private String user = "";
    private TextView ed = null;

    public void setUser(String user) {
        this.user = user;
    }

    public void setGpo(InetAddress gpo) {
        this.gpo = gpo;
    }

    public void setUs(List<String> us) {
        this.us = us;
    }

    public void setCon(MulticastSocket con) {
        this.con = con;
    }

    public void setEd(TextView ed){
        this.ed = ed;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                DatagramPacket p = new DatagramPacket(new byte[65535], 65535);
                con.receive(p);
                String msg = new String(p.getData(), 0, p.getLength());
                if (msg.contains("<user:")) {
                    if (user.equals("")) {
                        byte[] b = msg.getBytes();
                        DatagramPacket po = new DatagramPacket(b, b.length, gpo, 7778);
                        con.send(po);
                    } else {
                        String usuario = msg.substring(msg.indexOf(":") + 1, msg.indexOf(">"));
                        String off = usuario + ": " + msg.substring(msg.indexOf(">") + 1, msg.length());
                        ed.append(off+"\n");
                        System.out.println(off);
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
                        con.send(po);

                    } else {
                        if (msg.contains("<list>")) {
                            String nuevo = msg.substring(msg.indexOf("<loged>") + 7);
                            if (nuevo.contains(user)) {
                                String conec = msg.substring(6, msg.indexOf("<loged>") - 1);
                                ed.append(conec + "\n");
                                System.out.println(conec);
                            } else {
                                ed.append(nuevo + " se ha conectado\n");
                                System.out.println(nuevo + " se ha conectado");
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
