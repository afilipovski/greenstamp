package com.example.wireguard.config;

import java.net.Inet4Address;
import java.util.List;

import lombok.Data;

@Data
public class WireguardConfiguration {
    private String name;
    private Inet4Address address;
    private int listenPort;
    private String privateKey;
    private List<Inet4Address> dns;
    private int table;
    private int mtu;
    private String preUp;
    private String postUp;
    private String preDown;
    private String postDown;
    private List<WireguardPeer> peers;
}
