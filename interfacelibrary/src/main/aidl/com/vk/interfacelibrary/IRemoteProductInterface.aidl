// IRemoteProductInterface.aidl
package com.vk.interfacelibrary;

import com.vk.interfacelibrary.Product;
// Declare any non-default types here with import statements

interface IRemoteProductInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    boolean addProduct(String name,float price, int quantity);
    Product getProduct(String name);
    List<Product> getAllProducts();
}
