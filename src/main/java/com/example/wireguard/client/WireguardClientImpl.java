package com.example.wireguard.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class WireguardClientImpl implements WireguardClient {
    private String getExecOutput(String command, String input) {
        try {
            Process proc = Runtime.getRuntime().exec(command);
            if (!input.equals("")) {
                OutputStreamWriter osw = new OutputStreamWriter(proc.getOutputStream());
                osw.write(input);
                osw.close();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            return br.readLine();
        }
        catch(IOException io) {
            System.err.println("WireGuard is not installed. Please run sudo apt install wg");
            return "";
        }
    }


    @Override
    public String privateKey() {
        return getExecOutput("wg genkey", "");
    }

    @Override
    public String publicKey(String privateKey) {
        return getExecOutput("wg pubkey", privateKey);
    }

    @Override
    public String presharedKey() {
        return getExecOutput("wg genpsk", "");
    }

}
