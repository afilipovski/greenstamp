package com.example.wireguard.client;

public interface Keygen {
    public String privateKey();
    public String publicKey(String privateKey);
    public String presharedKey();
}
