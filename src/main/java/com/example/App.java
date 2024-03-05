package com.example;

import com.example.wireguard.client.WireguardClientImpl;

public class App 
{
    public static void main( String[] args )
    {
        WireguardClientImpl wci = new WireguardClientImpl();
        System.out.println(wci.publicKey(wci.privateKey()));
    }
}
