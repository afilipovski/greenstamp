package com.example;

import com.example.wireguard.config.ConfigurationReader;
import com.example.wireguard.config.WireguardConfiguration;

public class App 
{
    public static void main( String[] args )
    {
        ConfigurationReader cr = new ConfigurationReader();
        WireguardConfiguration wc = cr.readConfig();
        // System.out.println(wc.getPrivateKey());
    }
}
