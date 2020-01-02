package com.vk.aidlservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.vk.interfacelibrary.IRemoteProductInterface;
import com.vk.interfacelibrary.Product;

public class MyService extends Service {

    public MyService() {

    }


    private IRemoteProductInterface.Stub remoteProductInterface = new IRemoteProductInterface.Stub() {

        List<Product> products = Collections.synchronizedList(new ArrayList<Product>());

        @Override
        public boolean addProduct(String name, float price, int quantity) throws RemoteException {
            Product product = new Product(name, price,quantity);
            return products.add(product);
        }

        @Override
        public Product getProduct(String name) throws RemoteException {

            for(Product product : products) {
                if(product.getName().equalsIgnoreCase(name)) {
                    return product;
                }
            }
            return null;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return remoteProductInterface;
    }
}
