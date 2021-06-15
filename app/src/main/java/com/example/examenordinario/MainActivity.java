package com.example.examenordinario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final int PERMISO_SMS = 1000;
    private final int PERMISO_LLAMADA = 255;

    private TextView txtNumero;
    private Button btnComprar, btnComprarSMS, btnReclamacion, btnInfo, btnListado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumero = findViewById(R.id.txtNumero);
        if(txtNumero.length() > 5 || txtNumero.getText().toString().contains("[a-zA-Z]")){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }else{
            if(txtNumero.length() < 5){
                do{
                    String aux = txtNumero.getText().toString();
                    txtNumero.setText("0" + aux);
                }while(txtNumero.length() < 5);
            }
        }


        btnComprar = findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Mandar a pasarela pago compra " + txtNumero.getText(), Toast.LENGTH_LONG).show();
            }
        });

        btnComprarSMS = findViewById(R.id.btnComprarSMS);
        btnComprarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},PERMISO_SMS);
                }else{
                    enviarMensaje(txtNumero.getText().toString());
                }
            }
        });

        btnReclamacion = findViewById(R.id.btnReclamacion);
        btnReclamacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},PERMISO_LLAMADA);
                }else{
                    llamar();
                }
            }
        });

        Intent iInfo = new Intent(this, Actividad2.class);
        btnInfo = findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iInfo);
            }
        });

        Intent iListado = new Intent(this, Actividad1.class);
        btnListado = findViewById(R.id.btnListado);
        btnListado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(iListado);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISO_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enviarMensaje(txtNumero.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No tiene permisos de SMS", Toast.LENGTH_LONG).show();
                }
                return;
            }

            case PERMISO_LLAMADA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    llamar();
                } else {
                    Toast.makeText(getApplicationContext(), "No tiene permisos de llamada", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void llamar(){
        Intent i2 = new Intent(Intent.ACTION_CALL);
        i2.setData(Uri.parse("tel:666666666"));
        startActivity(i2);
    }

    public void enviarMensaje(String numeroBoleto){
        Intent iMensaje = new Intent(Intent.ACTION_SENDTO);
        iMensaje.setData(Uri.parse("smsto:555555555"));
        iMensaje.putExtra("sms_body", "Compra " + numeroBoleto);
        startActivity(iMensaje);
    }
}