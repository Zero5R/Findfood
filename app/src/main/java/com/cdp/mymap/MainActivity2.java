package com.cdp.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.mymap.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity2 extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Encontrar el botón por su ID
        Button button = findViewById(R.id.button);

        // Configurar el evento onClick para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });
        Button button2 = findViewById(R.id.button_registro_negocio);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la SecondActivity
                Intent intent = new Intent(MainActivity2.this, registrar_usuario.class);
                startActivity(intent); // Iniciar la nueva Activity
            }
        });
        // Configura Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("391070944982-lrom6r88d1mhe9g77p2j9m30so070lq3.apps.googleusercontent.com") // Usar el ID para Android
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Botón para iniciar sesión
        ImageButton googleSignInButton = findViewById(R.id.ButtonGoogle);
        googleSignInButton.setOnClickListener(v -> signIn());
    }

    // Iniciar sesión con Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Maneja el resultado del inicio de sesión
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // El inicio de sesión fue exitoso, obtén la cuenta de Google
                GoogleSignInAccount account = task.getResult();
                if (account != null) {
                    String idToken = account.getIdToken();
                    // Ahora puedes usar el idToken para autenticarte en tu backend si es necesario
                    Log.d("GoogleSignIn", "ID Token: " + idToken);
                    // Redirige a otra actividad o muestra la información del usuario
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    intent.putExtra("userName", account.getDisplayName());
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                Log.e("GoogleSignIn", "Error al obtener la cuenta de Google: " + e.getMessage());
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
