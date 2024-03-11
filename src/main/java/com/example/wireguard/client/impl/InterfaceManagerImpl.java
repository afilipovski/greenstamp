package com.example.wireguard.client.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.wireguard.client.InterfaceManager;
import com.example.wireguard.config.ConfigurationSerializer;
import com.example.wireguard.config.WireguardConfiguration;

public class InterfaceManagerImpl implements InterfaceManager {
    private static final Path baseConfigPath = Paths.get("/etc/wireguard");

    private void executeWgQuickCommand(String action, WireguardConfiguration configuration) {
        File file = new File(baseConfigPath.toString() + "/" + configuration.getFileName());
        ConfigurationSerializer.writeConfig(configuration, file);
        try {
            String command = "wg-quick " + action + " " + configuration.getCleanName();
            System.out.println(command);
            Runtime.getRuntime().exec(command);
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
