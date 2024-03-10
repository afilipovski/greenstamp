package com.example.wireguard.client.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.wireguard.client.InterfaceManager;
import com.example.wireguard.config.ConfigurationSerializer;
import com.example.wireguard.config.WireguardConfiguration;

public class InterfaceManagerImpl implements InterfaceManager {
    private static final Path baseConfigPath = Paths.get("./config");

    private void executeWgQuickCommand(String action, WireguardConfiguration configuration) {
        File file = new File(baseConfigPath.toString() + "/" + configuration.getName());
        ConfigurationSerializer.writeConfig(configuration, file);
        try {
            Runtime.getRuntime().exec("wg-quick " + action + " " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Wg-quick " + action + " failed");
        }
    }

    @Override
    public void create(WireguardConfiguration configuration) {
        executeWgQuickCommand("up", configuration);
    }

    @Override
    public void delete(WireguardConfiguration configuration) {
        executeWgQuickCommand("down", configuration);
    }
}
