package com.example.myreference;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.StringTokenizer;

public class Autor extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    String anoPublicacao ="";
    String nomeFormatadoAutor1="";
    String nomeFormatadoAutor2="";
    String nomeFormatadoAutor3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);


        Intent i = getIntent();

        final EditText nomeAutor1 = findViewById(R.id.editTextAutor1);
        final EditText nomeAutor2 = findViewById(R.id.editTextAutor2);
        final EditText nomeAutor3 = findViewById(R.id.editTextAutor3);
        final EditText local = findViewById(R.id.editTextLocal);
        final TextView textViewData = findViewById(R.id.textViewData);
        final ImageButton imageButtonCalendar = findViewById(R.id.imageButtonCalendar);


        final String titulo = i.getStringExtra("titulo");
        final String subtitulo = i.getStringExtra("subtitulo");
        final String tituloRevista = i.getStringExtra("tituloRevista");
        final String subTituloRevista = i.getStringExtra("subTituloRevista");
        final String volume = i.getStringExtra("volume");
        final String nEdicao = i.getStringExtra("nedicao");
        final String pagInicial = i.getStringExtra("pagInicial");
        final String pagFinal = i.getStringExtra("pagFinal");

        Button buttonResultado = findViewById(R.id.buttonConcluir);

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Autor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mDay, int mMonth, int mYear) {
                        textViewData.setText(mYear + "/" + (mMonth+1)  + "/" + mDay);
                        anoPublicacao = mYear + "/" + (mMonth+1)  + "/" + mDay;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String autor1 = nomeAutor1.getText().toString();
                final String autor2 = nomeAutor2.getText().toString();
                final String autor3 = nomeAutor3.getText().toString();

                final String localPublicacao = local.getText().toString();

                if (!autor1.equals("")){
                    nomeFormatadoAutor1 = formatarAutor(autor1);
                }

                if (!autor2.equals("")){
                    nomeFormatadoAutor2 = formatarAutor(autor2);
                }

                if (!autor3.equals("")){
                    nomeFormatadoAutor3 = formatarAutor(autor3);
                }


                //Método responsável por criar a referência.
                if (!autor1.equals("")|| !autor2.equals("") || !autor3.equals("")) {


                    String resultado = makeReference(nomeFormatadoAutor1, nomeFormatadoAutor2, nomeFormatadoAutor3, titulo, subtitulo, tituloRevista, subTituloRevista, localPublicacao, volume, nEdicao, pagInicial, pagFinal);

                    Intent i = new Intent(getApplicationContext(), Resultado.class);
                    i.putExtra("resultado", resultado);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Preencher os campos para envio das informações", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public String makeReference(String nomeFormatadoAutor1, String nomeFormatadoAutor2, String nomeFormatadoAutor3, String titulo, String subTitulo, String tituloRevista, String subTituloRevista, String localPublicacao, String volume, String nEdicao, String pagInicial, String pagFinal) {


        String resultado = (!nomeFormatadoAutor1.equals("")?nomeFormatadoAutor1+"; ":"") + (!nomeFormatadoAutor2.equals("")?nomeFormatadoAutor2+"; ":"") + (!nomeFormatadoAutor3.equals("")?nomeFormatadoAutor3:"") + ". "+ titulo + subTitulo + ". " + tituloRevista + ": " + subTituloRevista + ", " + localPublicacao + ", " + volume + ", " + nEdicao + ", " + "p. " + pagInicial + "-" + pagFinal
                + ", " + anoPublicacao + ".";

        return resultado;


    }

    public String formatarAutor(String autor){

        String nomeFomatadoParcial = "";
        String nomeFormatado = "";

        //Aqui coloca o último nome do Autor como o primeiro
        StringTokenizer st = new StringTokenizer(autor);

        int num = st.countTokens();

        for (int i = 0; i < num - 1; i++) {
            nomeFomatadoParcial = nomeFomatadoParcial + " " + st.nextToken();
        }


        nomeFormatado = st.nextToken().toUpperCase() + "," + nomeFomatadoParcial;

        return nomeFormatado;

    }
}
