package com.example.minimarketapp.interfaces;

import com.example.minimarketapp.modelos.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductoApi {
    @POST("api/productos")
    Call<Producto> registrarDatos(@Body Producto p);
}
