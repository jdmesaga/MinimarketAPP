package com.example.minimarketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minimarketapp.interfaces.ProductoApi;
import com.example.minimarketapp.modelos.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edtxCodigo, edtxNombre, edtxCantidad, edtxPrecio;

    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxCodigo= findViewById(R.id.edtxCodigo);
        edtxNombre= findViewById(R.id.edtxNombre);
        edtxCantidad= findViewById(R.id.edtxCantidad);
        edtxPrecio= findViewById(R.id.edtxPrecio);
        btnRegistrar= findViewById(R.id.btnRegistrar);

            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    registrar();
                }
            });
    }

    public void registrar(){
        int cod= Integer.parseInt(edtxCodigo.getText().toString());
        String nombre= edtxNombre.getText().toString();
        int cantidad= Integer.parseInt(edtxCantidad.getText().toString());
        int precio= Integer.parseInt(edtxPrecio.getText().toString());

        Producto p = new Producto(cod,nombre,cantidad,precio);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.100.5:8080/").addConverterFactory(GsonConverterFactory.create()).build();

        ProductoApi productoApi= retrofit.create(ProductoApi.class);

        Call<Producto> call =productoApi.registrarDatos(p);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                Toast.makeText(MainActivity.this, "Producto Registrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }


        });
    }


}