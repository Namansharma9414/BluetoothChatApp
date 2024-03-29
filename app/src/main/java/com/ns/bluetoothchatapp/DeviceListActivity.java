package com.ns.bluetoothchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class DeviceListActivity extends AppCompatActivity {
private ListView listPairedDevices, listAvailableDevices;
private ArrayAdapter<String> adapterPairedDevices, adapterAvailableDevices;
private Context context;
private BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        context = this;
    }
    private void init(){
      listPairedDevices =   findViewById(R.id.list_paired_devices);
      listAvailableDevices = findViewById(R.id.list_available_devices);

      adapterPairedDevices = new ArrayAdapter<String>(context, R.layout.device_list_item);
      adapterAvailableDevices = new ArrayAdapter<String>(context, R.layout.device_list_item);
      listAvailableDevices.setAdapter(adapterAvailableDevices);
      listPairedDevices.setAdapter(adapterPairedDevices);

      bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if(pairedDevices != null && pairedDevices.size()>0){
            for(BluetoothDevice device : pairedDevices){
                adapterPairedDevices.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_scan_devices:
                Toast.makeText(context, "Scan Devices Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}