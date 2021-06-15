package com.example.examenordinario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Actividad2 extends AppCompatActivity {

    private WebView pagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        pagina = findViewById(R.id.idWebView);
        pagina.loadUrl("https://www.selae.es/es/web-corporativa/quienes-somos");
    }
}