package com.entawaspada.cuaca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class GpsActivity extends AppCompatActivity {

    private TextView _koordinatTextView;
    private WebView _webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gps);

        Bundle param = getIntent().getBundleExtra("param");

        _koordinatTextView = findViewById(R.id.textView_koordinat);
        _koordinatTextView.setText(param.getDouble("lat") + " x " + param.getDouble("lon"));

        _webView1 = findViewById(R.id.wvMain);

        String url = "https://www.google.com/maps" +
                "?q=" + param.getDouble("lat") + "," + param.getDouble("lon") +
                "&ll=" + param.getDouble("lat") + "," + param.getDouble("lon") +
                "&z=19";

        WebSettings webSettings = _webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        _webView1.setWebViewClient(new WebViewClient());
        _webView1.loadUrl(url);
    }
}