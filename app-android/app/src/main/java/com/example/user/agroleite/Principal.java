package com.example.user.agroleite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal extends AppCompatActivity {
    private ListView listaV;
    List<Producao> lista;
    public static final String PREFS_NAME = "Preferences";
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Usua usuario =  new Usua();
        usuario.execute("http://172.21.151.219:8080/Tcc/rest/app/getProducao");


    }
    public void metodoChamatela(){



        //Identifica o Spinner no layout
        listaV = (ListView) findViewById(R.id.listav);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<Producao> arrayAdapter = new ArrayAdapter<Producao>(this, android.R.layout.simple_list_item_activated_1, lista);
        ArrayAdapter<Producao> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listaV.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        listaV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                valor = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(Principal.this, "Nome Selecionado: " + valor.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void enviaDadosWebService(View view){
        String valores = null, litrosLeite=null,nome=null, senha=null;
        Intent intent =  getIntent();

        Bundle extras =  intent.getExtras();

        if(extras !=null){
             valores = extras.getString("valor");
             litrosLeite = extras.getString("quantidade");
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            nome = settings.getString("nome", "");
            senha =settings.getString("senha", "");
     //       Toast.makeText(Principal.this, "Valor Litro do leite: " + valores.toString()+"Litros leite "+litrosLeite.toString()+"Json Usuario "+usuario.toString(), Toast.LENGTH_LONG).show();

        }
        if(valores !=null){
            System.out.println(valores+litrosLeite+nome+senha);
            Envia envia = new Envia();
            envia.execute("http://172.21.151.219:8080/Tcc/rest/app/producao/"+valores+"/"+litrosLeite+"/"+nome+"/"+senha);
            Usua usuario =  new Usua();
            usuario.execute("http://172.21.151.219:8080/Tcc/rest/app/getProducao");

        }else{
            Toast.makeText(this, "Nenhuma ordenha adicionada!!!", Toast.LENGTH_SHORT).show();
        }

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

        private List<Producao> getProducao(JSONArray jsonArray) {
            List<Producao> producao = new ArrayList<>();
            try {

                JSONObject proJson;
                for (int i = 0; i < jsonArray.length(); i++) {
                    proJson = new JSONObject(jsonArray.getString(i));
                    Producao producao1 = new Producao();

                    producao1.setDataOrdenha(proJson.getString("dataOrdenha"));
                    producao1.setQuantidadeLitros(proJson.getString("quantidadeLitros"));
                    producao1.setValorTotalProDiaria(proJson.getString("valorTotalProDiaria"));

                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = (Date)formatter.parse(producao1.getDataOrdenha());

                        DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Convertendo para String:: "+formatter.format(date));
                        producao1.setDataOrdenha(formatter2.format(date));
                        System.out.println("Convertendo para String:: "+formatter2.format(date));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    producao.add(producao1);
                }


            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing", e);
            }

            return producao;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            lista = getProducao(jsonArray);



            metodoChamatela();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray objetoArray ;
            objetoArray = getJsonArray(params[0]);

            return objetoArray;
        }
    }
    public class Envia extends AsyncTask<String, Integer, JSONArray> {

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

        private List<Producao> getProducao(JSONArray jsonArray) {
            List<Producao> producao = new ArrayList<>();
            try {

                JSONObject proJson;
                for (int i = 0; i < jsonArray.length(); i++) {
                    proJson = new JSONObject(jsonArray.getString(i));
                    Producao producao1 = new Producao();

                    producao1.setDataOrdenha(proJson.getString("dataOrdenha"));
                    producao1.setQuantidadeLitros(proJson.getString("quantidadeLitros"));
                    producao1.setValorTotalProDiaria(proJson.getString("valorTotalProDiaria"));

                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = (Date)formatter.parse(producao1.getDataOrdenha());

                        DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Convertendo para String:: "+formatter.format(date));
                        producao1.setDataOrdenha(formatter2.format(date));
                        System.out.println("Convertendo para String:: "+formatter2.format(date));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                   // producao.add(producao1);
                }


            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing", e);
            }

            return producao;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

          //  lista = getProducao(jsonArray);



            //metodoChamatela();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray objetoArray ;
            objetoArray = getJsonArray(params[0]);

            return objetoArray;
        }
    }

    public void abrirColetiva(View view){
        Intent views = new Intent(this, Coletiva.class);
        startActivity(views);
    }
    public void abrirIndividual(View view){
        Intent views = new Intent(this, Listagem.class);
        startActivity(views);
    }
    public void abrirListagem(View view){
        Intent views = new Intent(this, Listagem.class);
        startActivity(views);
    }
}
