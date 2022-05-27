package com.example.wasp_and;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WebView wView;
    ProgressBar pBar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wView = findViewById(R.id.wView);
        pBar = findViewById(R.id.pBar);
        pBar.setVisibility(View.GONE);

        wView.getSettings().setUseWideViewPort(true);

        initWebView();
    }


    public void initWebView() {
        wView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings ws = wView.getSettings();
        ws.setJavaScriptEnabled(true);

        //화면비율
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);

        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);

        wView.loadUrl("http://147.46.229.53:800/cgi-bin/fswepp/wasp/wasp.pl");
    }

    @Override
    public void onBackPressed(){
        if(wView.canGoBack()){
            wView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}