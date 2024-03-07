package com.example.wireguard.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import com.example.util.StringUtils;

public class ConfigurationReader {
    public WireguardConfiguration readConfig(File file) {
        Ini ini = new Ini();
        ini.getConfig().setMultiSection(true);
        try {
            ini.load(file);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Section interfaceSection = ini.get("Interface");
        List<Section> peersSections = ini.getAll("Peer");
        if (peersSections == null)
            peersSections = new ArrayList<>(); 

        return new WireguardConfiguration(
            interfaceSection.getComment("Address"),
            interfaceSection.get("Address"),
            StringUtils.tryParseInt(interfaceSection.get("ListenPort")),
            interfaceSection.get("PrivateKey"),
            Arrays.asList(StringUtils.trySplit(interfaceSection.get("DNS"), ",")),
            StringUtils.tryParseInt(interfaceSection.get("Table")),
            StringUtils.tryParseInt(interfaceSection.get("MTU")),
            interfaceSection.get("PreUp"),
            interfaceSection.get("PostUp"),
            interfaceSection.get("PreDown"),
            interfaceSection.get("PostDown"),
            peersSections.stream().map(peerSection -> {
                return new WireguardPeer(
                    peerSection.getComment("AllowedIPs"),
                    Arrays.asList(StringUtils.trySplit(peerSection.get("AllowedIPs"), ",")),
                    peerSection.get("Endpoint"),
                    peerSection.get("PublicKey"),
                    peerSection.get("PresharedKey"),
                    StringUtils.tryParseInt(peerSection.get("PersistentKeepalive"))
                );
            }).toList()
        );
    }

    public File writeConfig(WireguardConfiguration wc, File file) {
        Ini ini = new Ini();

        ini.getConfig().setEscape(false);

        String interfaceHeader = "Interface";
        String peerHeader = "Peer";

        ini.add(interfaceHeader,"Address",wc.getAddress());
        ini.get("Interface").putComment("Address",wc.getName());
        ini.add(interfaceHeader,"ListenPort",wc.getListenPort());
        ini.add(interfaceHeader,"PrivateKey",wc.getPrivateKey());
        ini.add(interfaceHeader,"DNS",StringUtils.tryJoin(",", wc.getDns()));
        ini.add(interfaceHeader,"Table",wc.getTable());
        ini.add(interfaceHeader,"MTU",wc.getMtu());
        ini.add(interfaceHeader,"PreUp",wc.getPreUp());
        ini.add(interfaceHeader,"PostUp",wc.getPostUp());
        ini.add(interfaceHeader,"PreDown",wc.getPreDown());
        ini.add(interfaceHeader,"PostDown",wc.getPostDown());

        try {
            ini.store(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return file;
    }
    
}
