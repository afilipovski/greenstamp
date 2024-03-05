package com.example.wireguard.config;

import java.net.Inet4Address;
import java.util.List;

import lombok.Data;

@Data
public class WireguardPeer {
    private String name;
    private List<Inet4Address> allowedIPs;
    private String endpoint;
    private String publicKey;
    private int persistentKeepalive;
}
