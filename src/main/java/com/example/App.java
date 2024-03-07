package com.example;

import java.io.File;

import com.example.wireguard.config.ConfigurationReader;
import com.example.wireguard.config.WireguardConfiguration;

public class App 
{
    public static void main( String[] args )
    {
        ConfigurationReader cr = new ConfigurationReader();
        WireguardConfiguration wc = cr.readConfig(new File("./wg1.conf"));
        System.out.println(wc);
        cr.writeConfig(wc, new File("./wg1.conf"));
    }
}
