package com.example.wireguard.config;

import java.util.List;

import lombok.Data;

@Data
public class WireguardPeer {
    private String name;
    private List<String> allowedIPs;
    private String endpoint;
    private String publicKey;
    private int persistentKeepalive;
}
