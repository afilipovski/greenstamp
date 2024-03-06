package com.example.wireguard.config;

import java.net.InetAddress;
import java.util.List;

import lombok.Data;

@Data
public class WireguardConfiguration {
    private String name;
    private InetAddress address;
    private int listenPort;
    private String privateKey;
    private List<InetAddress> dns;
    private int table;
    private int mtu;
    private String preUp;
    private String postUp;
    private String preDown;
    private String postDown;
    private List<WireguardPeer> peers;
}
