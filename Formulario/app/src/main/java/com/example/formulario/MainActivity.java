package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btAceptar;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAceptar = findViewById(R.id.btnAceptar);
        etNombre = findViewById(R.id.txtNombre);
        etApellido = findViewById(R.id.txtApellido);
        etEmail = findViewById(R.id.txtEmail);

        btAceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAceptar){
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            String email = etEmail.getText().toString();

            if(nombre.isEmpty()){
                CustomToastView.makeErrorToast(this, "Error al validar el Nombre", R.layout.custom_toast).show();
                return;
            }
            if(apellido.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al validar el Apellido", R.layout.custom_toast).show();
                return;
            }
            if(!isValidEmail(email)){
                CustomToastView.makeWarningToast(this, "Error al validar el Email", R.layout.custom_toast).show();
                return;
            }

            Intent myIntent = new Intent(this, CalculadoraIMC.class);
            myIntent.putExtra("NombreCalculadora", nombre);
            myIntent.putExtra("ApellidoCalculadora", apellido);
            myIntent.putExtra("EmailCalculadora", email);
            startActivity(myIntent);
        }

    }

    private boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }



}