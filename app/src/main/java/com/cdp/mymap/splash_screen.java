package com.cdp.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // Se declara el tiempo en segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Usar Handler para manejar el tiempo de retraso
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar la actividad principal después del retraso
                Intent intent = new Intent(splash_screen.this, MainActivity2.class);
                startActivity(intent);
                finish(); // Finalizar la SplashScreen para que no vuelva a aparecer al presionar el botón de atrás
            }
        }, SPLASH_DISPLAY_LENGTH); // Retraso de 2 segundos
    }
}