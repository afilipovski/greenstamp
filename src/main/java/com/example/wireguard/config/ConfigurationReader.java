package com.example.wireguard.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

import com.example.util.StringUtils;

public class ConfigurationReader {
    public WireguardConfiguration readConfig() {
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



        return new WireguardConfiguration(
            interfaceSection.getComment("Name"),
            interfaceSection.get("Address",0),
            StringUtils.tryParseInt(interfaceSection.get("ListenPort",0)),
            interfaceSection.get("PrivateKey",0),
            Arrays.asList(StringUtils.trySplit(interfaceSection.get("DNS",0), ",")),
            StringUtils.tryParseInt(interfaceSection.get("Table",0)),
            StringUtils.tryParseInt(interfaceSection.get("MTU",0)),
            interfaceSection.get("PreUp",0),
            interfaceSection.get("PostUp",0),
            interfaceSection.get("PreDown",0),
            interfaceSection.get("PostDown",0),
            new ArrayList<>()
        );
    }
    
}
