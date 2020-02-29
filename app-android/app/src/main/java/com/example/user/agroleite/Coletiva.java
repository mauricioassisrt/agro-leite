package com.example.user.agroleite;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Coletiva extends AppCompatActivity {
    private Spinner spn1;
    List<Leite> listaLeite;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Usua usuario =  new Usua();
        usuario.execute("http://172.21.151.219:8080/Tcc/rest/app/getleite");

        setContentView(R.layout.activity_coletiva);


    }



    public void metodoChamatela(){



        //Identifica o Spinner no layout
        spn1 = (Spinner) findViewById(R.id.spinner);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<Leite> arrayAdapter = new ArrayAdapter<Leite>(this, android.R.layout.simple_spinner_dropdown_item, listaLeite);
        ArrayAdapter<Leite> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                valor = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(Coletiva.this, " Selecionado valor : " + valor.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void AdicionaProducao(View view){
        EditText edittx = (EditText) findViewById(R.id.editTextLitros);
        Intent exibir = new Intent(this, Principal.class);
        String litrosLeite = edittx.getText().toString();
        exibir.putExtra("valor", valor);
        exibir.putExtra("quantidade", litrosLeite);
        System.out.println("LEITE "+valor);
        System.out.println("PRODUCAO "+litrosLeite);
        startActivity(exibir);
        finish();
    }
    public class Usua extends AsyncTask<String, Integer, JSONArray> {

        public JSONArray getJsonArray(String url) {
            JSONArray jObj = null;
            try {
                String line, newjson = "";
                URL urls = new URL(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(urls.openStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    newjson += line;
                }
                String json = newjson.toString();
                jObj = new JSONArray(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return jObj;
        }

        private List<Leite> getLeite(JSONArray jsonArray) {
            List<Leite> leites = new ArrayList<>();
            try {

                JSONObject leiteJson;
                for (int i = 0; i < jsonArray.length(); i++) {
                    leiteJson = new JSONObject(jsonArray.getString(i));
                    Leite leite = new Leite();
                    leite.setValorLitro(leiteJson.getString("valorLitro"));


                    leites.add(leite);
                }
            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing", e);
            }
            return leites;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            listaLeite = getLeite(jsonArray);


            metodoChamatela();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray objetoArray ;
            objetoArray = getJsonArray(params[0]);

            return objetoArray;
        }
    }



}
