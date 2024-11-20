package com.cdp.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class busqueda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda);

        // Encontrar el botón por su ID
        Button button = findViewById(R.id.button4);

        // Configurar el evento onClick para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(busqueda.this, activity_restaurante.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });
    }


}
