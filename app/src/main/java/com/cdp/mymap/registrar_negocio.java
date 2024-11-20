package com.cdp.mymap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class registrar_negocio extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE_FOTOS = 1;
    private static final int REQUEST_IMAGE_CAPTURE_MENU = 2;
    private static final int CAMERA_PERMISSION_CODE = 100;

    private Bitmap photoBitmapFotos;
    private Bitmap photoBitmapMenu;

    private ImageView imageViewFoto; // Para mostrar la foto del botón "Fotos"
    private ImageView imageViewMenu; // Para mostrar la foto del botón "Menú"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_negocio);

        // Inicializar los componentes
        EditText etNombreRestaurante = findViewById(R.id.etNombreRestaurante);
        EditText etDireccion = findViewById(R.id.etUbicacion);
        EditText etCodigoComercio = findViewById(R.id.etCodigoComercio);
        ImageButton btnFotos = findViewById(R.id.btnFotos);
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        Button btnAgregar = findViewById(R.id.btnAgregar);

        // Inicializar los ImageView
        imageViewFoto = findViewById(R.id.imageViewFoto);
        imageViewMenu = findViewById(R.id.imageViewMenu);

        // Configurar el botón de "Fotos"
        btnFotos.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                openCamera(REQUEST_IMAGE_CAPTURE_FOTOS);
            } else {
                requestCameraPermission();
            }
        });

        // Configurar el botón de "Menú"
        btnMenu.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                openCamera(REQUEST_IMAGE_CAPTURE_MENU);
            } else {
                requestCameraPermission();
            }
        });

        // Configurar el botón "Agregar"
        btnAgregar.setOnClickListener(v -> {
            String nombreRestaurante = etNombreRestaurante.getText().toString();
            String direccion = etDireccion.getText().toString();
            String codigoComercio = etCodigoComercio.getText().toString();

            if (!nombreRestaurante.isEmpty() && !direccion.isEmpty() && !codigoComercio.isEmpty()) {
                Toast.makeText(this, "Negocio registrado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    private void openCamera(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                if (requestCode == REQUEST_IMAGE_CAPTURE_FOTOS) {
                    photoBitmapFotos = (Bitmap) extras.get("data");
                    imageViewFoto.setImageBitmap(photoBitmapFotos); // Mostrar la foto en imageViewFoto
                    Toast.makeText(this, "Foto capturada para 'Fotos'", Toast.LENGTH_SHORT).show();
                } else if (requestCode == REQUEST_IMAGE_CAPTURE_MENU) {
                    photoBitmapMenu = (Bitmap) extras.get("data");
                    imageViewMenu.setImageBitmap(photoBitmapMenu); // Mostrar la foto en imageViewMenu
                    Toast.makeText(this, "Foto capturada para 'Menú'", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
