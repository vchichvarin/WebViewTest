package com.example.vchichvarin.webviewlib;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vchichvarin.webviewlib.locationcheck.IPCheck;
import com.example.vchichvarin.webviewlib.locationcheck.data.Country;
import com.example.vchichvarin.webviewlib.locationcheck.CountryRetrofit;

import java.io.IOException;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    boolean isUserFromRussia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String IP = IPCheck.getIP();

        try {
            Response<Country> response = CountryRetrofit.getInstance()
                    .getJSONApi()
                    .getCountryByIP(IP, "d0058184b11b55c11973045640dca20a")
                    .execute();

            Country country = response.body();
            isUserFromRussia = country.getCountry().equals("Russia");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!isUserFromRussia) {
            setContentView(R.layout.activity_main);
            PlayGame.init(this);
            PlayGame.newGame();
            PlayGame.initFieldButtons();
            PlayGame.createControlButtons();
        }
        else {
            setContentView(R.layout.webviewplug);
            webView = findViewById(R.id.webView);
            webView.setWebViewClient(new MyWebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.github.com/vchichvarin");
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}