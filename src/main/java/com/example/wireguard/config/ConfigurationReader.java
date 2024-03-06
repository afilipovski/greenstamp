package com.example.wireguard.config;

import java.io.File;
import java.io.IOException;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

public class ConfigurationReader {
    public WireguardConfiguration readConfig() {
        WireguardConfiguration wireguardConfiguration = new WireguardConfiguration();

        File file = new File("./wg.conf");
        Ini ini = new Ini();
        ini.getConfig().setMultiSection(true);
        try {
            ini.load(file);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Interface
        Section interfaceSection = ini.get("Interface");
        System.out.println(interfaceSection.get("Address", 0));
        // wireguardConfiguration.setName(interfaceSection.get("Name"));
        // wireguardConfiguration.setAddress(InetAddress.getByName(interfaceSection.get("Address")));
        // wireguardConfiguration.setPrivateKey(interfaceSection.get("PrivateKey"));

        return wireguardConfiguration;
    }
    
}
