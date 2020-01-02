package com.soti.my_aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vk.interfacelibrary.IRemoteProductInterface;
import com.vk.interfacelibrary.Product;

public class MainActivity extends AppCompatActivity {

    private IRemoteProductInterface myService;
    private RemoteServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectService();

        findViewById(R.id.add_product).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myService!=null){

                    try {
                        boolean added = myService.addProduct("Vivek", 23,2);
                        Toast.makeText(MainActivity.this, "product added successfully", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Service is not connected", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.get_all_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myService!=null){

                    try {
                        Product product = myService.getProduct("Vivek");
                        if(product!=null){
                            Toast.makeText(MainActivity.this, "product was found", Toast.LENGTH_SHORT).show();
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Service is not connected", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void connectService() {
        serviceConnection = new RemoteServiceConnection();
        Intent intent = new Intent("service.aidl");
        intent.setPackage("com.vk.aidlservice");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private class RemoteServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = IRemoteProductInterface.Stub.asInterface(service);
            Log.d("onServiceConnected", "service is connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
            Log.d("onServiceConnected", "service is connected");
        }
    }
}
