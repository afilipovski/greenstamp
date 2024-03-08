package com.example;

import java.io.File;

import com.example.wireguard.config.ConfigurationSerializer;
import com.example.wireguard.config.WireguardConfiguration;

public class App 
{
    public static void main( String[] args )
    {
        ConfigurationSerializer cr = new ConfigurationSerializer();
        WireguardConfiguration wc = cr.readConfig(new File("./wg.conf"));
        System.out.println(wc);
        cr.writeConfig(wc, new File("./wg1.conf"));
    }
}
