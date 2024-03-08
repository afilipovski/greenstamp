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

public class ConfigurationSerializer {
    public WireguardConfiguration readConfig(File file) {
        Ini ini = new Ini();
        ini.getConfig().setMultiSection(true);
        try {
            ini.load(file);
        } catch (InvalidFileFormatException e) {
            System.err.println(file.getAbsolutePath() + " is not a valid WireGuard config file.");
        } catch (IOException e) {
            System.err.println("Couldn't open " + file.getAbsolutePath());
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

        Section interfaceSection = ini.add("Interface");

        interfaceSection.add("Address",wc.getAddress());
        interfaceSection.putComment("Address",wc.getName());
        interfaceSection.add("ListenPort",wc.getListenPort());
        interfaceSection.add("PrivateKey",wc.getPrivateKey());
        interfaceSection.add("DNS",StringUtils.tryJoin(",", wc.getDns()));
        interfaceSection.add("Table",wc.getTable());
        interfaceSection.add("MTU",wc.getMtu());
        interfaceSection.add("PreUp",wc.getPreUp());
        interfaceSection.add("PostUp",wc.getPostUp());
        interfaceSection.add("PreDown",wc.getPreDown());
        interfaceSection.add("PostDown",wc.getPostDown());

        wc.getPeers().stream().forEach(peer -> {
            Section peerSection = ini.add("Peer");

            peerSection.add("AllowedIPs", StringUtils.tryJoin(",", peer.getAllowedIPs()));
            peerSection.putComment("AllowedIPs", peer.getName());
            peerSection.add("Endpoint", peer.getEndpoint());
            peerSection.add("PublicKey", peer.getPublicKey());
            peerSection.add("PresharedKey", peer.getPresharedKey());
            peerSection.add("PersistentKeepalive", peer.getPersistentKeepalive());
        });


        try {
            ini.store(file);
        } catch (IOException e) {
            System.err.println("Couldn't open " + file.getAbsolutePath());
        }
        
        return file;
    }
    
}
