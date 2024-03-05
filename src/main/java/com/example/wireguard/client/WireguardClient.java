package com.example.wireguard.client;

public interface WireguardClient {
    public String privateKey();
    public String publicKey(String privateKey);
    public String presharedKey();
}
