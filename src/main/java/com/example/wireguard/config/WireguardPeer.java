package com.example.wireguard.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WireguardPeer {
    private String name;
    private List<String> allowedIPs;
    private String endpoint;
    private String publicKey;
    private String presharedKey;
    private Integer persistentKeepalive;
}
