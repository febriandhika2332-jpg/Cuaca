package com.entawaspada.cuaca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity
{
    private EditText _etKota;
    private MaterialButton _btnTampilkan, _buttonViewCityInfo;
    private RecyclerView _recyclerView1;
    private RootModel _rootModel;
    private SwipeRefreshLayout _swipeRefreshLayout1;
    private TextView _totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        _etKota = findViewById(R.id.etKota);
        _recyclerView1 = findViewById(R.id.recyclerView);
        _totalTextView = findViewById(R.id.totalTextView);

        initSwipeRefreshLayout();
        initButtonViewCityInfo();
        initTampilkanButton();

        bindRecyclerView1();
    }

    private void bindRecyclerView1() {
        String namaKota = _etKota.getText().toString().trim();
        if (namaKota.isEmpty()) {
            Toast.makeText(MainActivity.this, "Nama kota tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            namaKota = URLEncoder.encode(namaKota, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        _buttonViewCityInfo.setText("Mencari...");

        String url = "https://api.openweathermap.org/data/2.5/forecast?q={nama kota}&appid={app id anda}";
        // Tampilkan url di Logcat untuk dicek di browser
        Log.d("url+tw", url);

        AsyncHttpClient ahc = new AsyncHttpClient();
        ahc.get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                // Mengubah JSON mentah ke logcat
                //Log.d("+tw", new String(responseBody));

                Gson gson = new Gson();
                _rootModel = gson.fromJson(new String(responseBody), RootModel.class);

                initCityInfo();

                RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
                _recyclerView1.setLayoutManager(lm);

                CuacaAdapter ca = new CuacaAdapter(_rootModel);
                _recyclerView1.setAdapter(ca);

                _totalTextView.setText("Total Record : " + ca.getItemCount());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initButtonViewCityInfo() {
        _buttonViewCityInfo = findViewById(R.id.buttonView_cityInfo);

        _buttonViewCityInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CityModel cm = _rootModel.getCityModel();
                CoordModel com = cm.getCoordModel();
                double latitude = com.getLat();
                double longitude = com.getLon();

                Bundle param = new Bundle();
                param.putDouble("lat", latitude);
                param.putDouble("lon", longitude);

                Intent intent = new Intent(MainActivity.this, GpsActivity.class);
                intent.putExtra("param", param);
                startActivity(intent);
            }
        });
    }

    private void initCityInfo() {
        CityModel cm = _rootModel.getCityModel();
        long sunrise = cm.getSunrise();
        long sunset = cm.getSunset();
        String cityName = cm.getName();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String sunriseTime = sdf.format(new Date(sunrise * 1000));
        String sunsetTime = sdf.format(new Date(sunset * 1000));

        String cityInfo = "Kota: " + cityName + "\n" +
                "Matahari Terbit: " + sunriseTime + " (Lokal)\n" +
                "Matahari Terbenam: " + sunsetTime + " (Lokal)";

        _buttonViewCityInfo.setText(cityInfo);
    }

    private void initSwipeRefreshLayout() {
        _swipeRefreshLayout1 = findViewById(R.id.swipeRefreshLayout);

        _swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                bindRecyclerView1();
                _swipeRefreshLayout1.setRefreshing(false);
            }
        });
    }

    private void initTampilkanButton() {
        _btnTampilkan = findViewById(R.id.btnTampilkan);

        _btnTampilkan.setOnClickListener((View v) -> {
            bindRecyclerView1();
        });
    }
}