package com.example.examenordinario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Actividad1 extends AppCompatActivity {

    public Adaptador adapter;
    ArrayList<ArrayList<String>> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        RecyclerView recyclerView = findViewById(R.id.idListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adaptador(this);

        recyclerView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://my-json-server.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Servicio servicio = retrofit.create(Servicio.class);

                Call<List<Boleto>> llamada = servicio.listBoleto();

                llamada.enqueue(new Callback<List<Boleto>>() {
                    @Override
                    public void onResponse(Call<List<Boleto>> call, Response<List<Boleto>> response) {
                        for(Boleto b: response.body()){
                            ArrayList<String> fila = new ArrayList<>();
                            fila.add(b.numero);
                            fila.add(b.serie);
                            fila.add(b.premio);
                            info.add(fila);
                            adapter.addData(info);
                            info.clear();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Boleto>> call, Throwable t) {
                        Log.d("Resultado", "Fallo en la peticion...");
                    }
                });
            }
        }).start();
    }
}