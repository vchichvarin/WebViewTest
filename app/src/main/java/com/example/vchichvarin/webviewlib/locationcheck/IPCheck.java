package com.example.vchichvarin.webviewlib.locationcheck;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.http.GET;


public class IPCheck {

    static String DOMAIN = "https://api.ipdata.co";

    static String IP;

    public static String getIP () {
        {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                URL myIP = new URL("https://checkip.amazonaws.com/");
                URLConnection c = myIP.openConnection();
                c.setConnectTimeout(1000);
                c.setReadTimeout(1000);

                BufferedReader in = null;
                in = new BufferedReader(new InputStreamReader(c.getInputStream()));
                IP = in.readLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return IP;
    }
}
