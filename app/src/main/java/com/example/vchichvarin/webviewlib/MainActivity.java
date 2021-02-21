package com.example.vchichvarin.webviewlib;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vchichvarin.webviewlib.gamelogic.*;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 27) {
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