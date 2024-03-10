package com.example.wireguard.client;

import com.example.wireguard.config.WireguardConfiguration;

public interface InterfaceManager {
    public void create(WireguardConfiguration configuration);
    public void delete(WireguardConfiguration configuration);
}
