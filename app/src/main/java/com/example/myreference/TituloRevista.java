package com.example.myreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TituloRevista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titulo_revista);

        //Pegando dados da Tela anterior
        Intent i = getIntent();
        final String titulo = i.getStringExtra("titulo");
        final String subtitulo = i.getStringExtra("subtitulo");


        final EditText editTextTituloRevista = findViewById(R.id.editTextTituloRevista);
        final EditText editTextSubRevista = findViewById(R.id.editTextSubRevista);
        final EditText editTextVolume = findViewById(R.id.editTextVolume);
        final EditText editTextNEdicao = findViewById(R.id.editTextNEdicao);
        final EditText editTextPagInicial = findViewById(R.id.editTextPagInicial);
        final EditText editTextPagFinal = findViewById(R.id.editTextPagFinal);

        Button buttonProsseguir = findViewById(R.id.buttonProsseguir);

        buttonProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tituloRevista = editTextTituloRevista.getText().toString();
                String subTituloRevista = editTextSubRevista.getText().toString();
                String volume = editTextVolume.getText().toString();
                String nEdicao = editTextNEdicao.getText().toString();
                String pagInicial = editTextPagInicial.getText().toString();
                String pagFinal = editTextPagFinal.getText().toString();

                Intent i = new Intent(getApplicationContext(), Autor.class);

                i.putExtra("titulo", titulo);
                i.putExtra("subtitulo", subtitulo);
                i.putExtra("tituloRevista", tituloRevista);
                i.putExtra("subTituloRevista", subTituloRevista);
                i.putExtra("volume", volume);
                i.putExtra("nedicao", nEdicao);
                i.putExtra("pagInicial", pagInicial);
                i.putExtra("pagFinal", pagFinal);

                startActivity(i);


            }
        });


    }
}
