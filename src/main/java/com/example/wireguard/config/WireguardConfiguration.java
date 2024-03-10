package com.example.wireguard.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WireguardConfiguration {
    private String name;
    private String address;
    private Integer listenPort;
    private String privateKey;
    private List<String> dns;
    private Integer table;
    private Integer mtu;
    private String preUp;
    private String postUp;
    private String preDown;
    private String postDown;
    private List<WireguardPeer> peers;
}
