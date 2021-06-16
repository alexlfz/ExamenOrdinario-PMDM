package com.example.examenordinario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicio {
    @GET("/jorgeduenaslerin/desarrollo-web/loto")
    Call<List<Boleto>> listBoleto();
}
