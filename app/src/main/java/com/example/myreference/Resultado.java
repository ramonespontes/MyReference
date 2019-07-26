package com.example.myreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Resultado extends AppCompatActivity {

    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent i = getIntent();
        resultado = i.getStringExtra("resultado");

        EditText editTextResultado = findViewById(R.id.editTextResultado);
        Button buttonConcluir = findViewById(R.id.buttonConcluir);

        //Imprime o resultado no campo
        editTextResultado.setText(resultado);

        //Permite compartilhar o resultado
        buttonConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Antes de enviar verifica se tem conteúdo
                boolean check = verificaInformacao();

                //Aqui fazer o código para compartilhar....
                if(check) {
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                    share.putExtra(Intent.EXTRA_SUBJECT,
                            "Referência");
                    share.putExtra(Intent.EXTRA_TEXT,
                            resultado.toString());

                    startActivity(Intent.createChooser(share, "Referência Fácil"));
                }
            }
        });

    }

    public boolean verificaInformacao(){

        if (resultado.equals("")){

            Toast.makeText(getApplicationContext(), "Preencher os campos para envio das informações", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
