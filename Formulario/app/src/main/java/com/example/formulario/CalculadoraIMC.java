package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CalculadoraIMC extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInformacion;
    private EditText txtPeso;
    private EditText txtAltura;
    private Button btCalcular;
    private ImageView imgEstado;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculadora_i_m_c);

        Intent intent = getIntent();
        String Nombre = intent.getStringExtra("NombreCalculadora");
        String Apellido = intent.getStringExtra("ApellidoCalculadora");
        String Email = intent.getStringExtra("EmailCalculadora");
        String Mensaje = "Hola " + Nombre + " " + Apellido + " es un gusto tenerte aca, su correo para el informe es: " + Email;

        tvInformacion = findViewById(R.id.tvInformacion);
        tvResultado = findViewById(R.id.tvResultado);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        btCalcular = findViewById(R.id.btnCalcular);
        imgEstado = findViewById(R.id.imEstado);

        tvInformacion.setText(Mensaje);
        btCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalcular){
            double resIMC = CalcularIMC();
            String MensajeFinal = "";
            Intent intent = getIntent();
            String Nombre = intent.getStringExtra("NombreCalculadora");
            String Apellido = intent.getStringExtra("ApellidoCalculadora");
            String Email = intent.getStringExtra("EmailCalculadora");

            if (resIMC < 18.5){
                MensajeFinal =  Nombre + " " + Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de BAJO PESO";
                imgEstado.setImageResource(R.drawable.bajopeso);
            }
            if (resIMC >= 18.5 && resIMC <= 24.9){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de PESO NORMAL O SALUDABLE";
                imgEstado.setImageResource(R.drawable.normal);
            }
            if (resIMC >= 25 && resIMC <= 29.9){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de SOBREPESO";
                imgEstado.setImageResource(R.drawable.sobrepeso);
            }
            if (resIMC > 30){
                MensajeFinal =  Nombre + " " +  Apellido + " su IMC es: " + resIMC + " lo que indica que su peso esta en la categoria de OBECIDAD";
                imgEstado.setImageResource(R.drawable.obeso);
            }

            tvResultado.setText(MensajeFinal);
        }
    }

    public double CalcularIMC(){
        double res;
        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());

        res = peso/(Math.pow((altura/100),2));

        return res;
    }
}