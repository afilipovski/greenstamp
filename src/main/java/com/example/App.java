package com.example;

import java.io.File;

import com.example.wireguard.client.InterfaceManager;
import com.example.wireguard.client.impl.InterfaceManagerImpl;
import com.example.wireguard.config.ConfigurationSerializer;
import com.example.wireguard.config.WireguardConfiguration;

public class App 
{
    public static void main( String[] args )
    {
        WireguardConfiguration wc = ConfigurationSerializer.readConfig(new File("./config/server.conf"));
        System.out.println(wc);

        InterfaceManager im = new InterfaceManagerImpl();

        im.create(wc);
    }
}
