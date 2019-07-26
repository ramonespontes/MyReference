package com.example.myreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextTituloArtigo = findViewById(R.id.editTextTituloArtigo);
        final EditText editTextSubArtigo = findViewById(R.id.editTextSubArtigo);
        Button buttonProsseguir = findViewById(R.id.buttonProsseguir);

        buttonProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tituloArtigo = editTextTituloArtigo.getText().toString();
                String subTituloArtigo = editTextSubArtigo.getText().toString();

                Intent i = new Intent(getApplicationContext(), TituloRevista.class);

                i.putExtra("titulo", tituloArtigo);
                i.putExtra("subtitulo", " : " + subTituloArtigo);

                startActivity(i);
            }
        });

    }
}
