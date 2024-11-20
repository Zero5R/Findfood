package com.cdp.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        Button button = findViewById(R.id.button4);

        //Configurar el evento onClick para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(opciones.this,registrar_negocio.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });

        Button buttonHistorial = findViewById(R.id.button3);

        //Configurar el evento onClick para el botón
        buttonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(opciones.this, historial.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });

        Button buttonProblema = findViewById(R.id.button2);

        //Configurar el evento onClick para el botón
        buttonProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(opciones.this, problema.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });
    }
}
