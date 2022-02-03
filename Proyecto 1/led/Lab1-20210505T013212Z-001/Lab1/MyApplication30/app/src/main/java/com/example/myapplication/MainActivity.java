package com.example.myapplication;
import android.app.ListActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.Set;
import java.util.UUID;

public class MainActivity extends ListActivity {
    private ArrayAdapter<String> mArrayAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket btSocket;
    private ArrayList<BluetoothDevice> btDeviceArray = new ArrayList<BluetoothDevice>();
    private ConnectAsyncTask connectAsyncTask;
    private BluetoothAdapter mBTAdapter;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    ToggleButton btn1;
    ImageView im;
    byte[] buffer = new byte[256];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (ImageView)findViewById(R.id.im1);
        im.setImageResource(R.drawable.off);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        setListAdapter(mArrayAdapter);
        connectAsyncTask = new ConnectAsyncTask();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBluetoothAdapter == null){
            //Device does not support Bluetooth
            Toast.makeText(getApplicationContext(), "Not support bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        Set<BluetoothDevice> pariedDevices = mBluetoothAdapter.getBondedDevices();
        if(pariedDevices.size() > 0){
            for(BluetoothDevice device : pariedDevices){
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                btDeviceArray.add(device);
            }
        }


        btn1 = (ToggleButton)findViewById(R.id.btn1);


        //dispEmparejados = (ListView)findViewById(R.id.lista);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream mmOutStream = null;
                if(btn1.isChecked()){
                    try {

                        if(btSocket.isConnected()){
                            InputStream mmIn = null;
                            mmOutStream = btSocket.getOutputStream();
                            mmOutStream.write(new String("1").getBytes());
                            im.setImageResource(R.drawable.on);
                            mmIn = btSocket.getInputStream();
                            int length = mmIn.read(buffer);
                            String text = new String(buffer, 0, length);
                            showToastMessage("On");
                        }

                    } catch (IOException e) { }
                }else{
                    try {

                        if(btSocket.isConnected()){
                            InputStream mmIn = null;
                            mmOutStream = btSocket.getOutputStream();
                            mmOutStream.write(new String("0").getBytes());
                            im.setImageResource(R.drawable.off);
                            mmIn = btSocket.getInputStream();
                            int length = mmIn.read(buffer);
                            String text = new String(buffer, 0, length);
                            showToastMessage("Off");
                        }

                    } catch (IOException e) { }
                }
            }
        });

    }


    private void showToastMessage(String s) {
        Toast t = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        BluetoothDevice device = btDeviceArray.get(position);
        connectAsyncTask.execute(device);
        Toast.makeText(getApplicationContext(),"Conectado",Toast.LENGTH_SHORT).show();
    }

    private class ConnectAsyncTask extends AsyncTask<BluetoothDevice, Integer, BluetoothSocket>{
        private BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice;
        @Override
        protected BluetoothSocket doInBackground(BluetoothDevice... device) {
            mmDevice = device[0];
            try {
                String mmUUID = "00001101-0000-1000-8000-00805F9B34FB";
                mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(mmUUID));
                mmSocket.connect();
            } catch (Exception e) { }
            return mmSocket;
        }

        @Override
        protected void onPostExecute(BluetoothSocket result) {
            btSocket = result;
        }
    }
}