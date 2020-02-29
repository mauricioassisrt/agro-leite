package com.example.user.agroleite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ProgressDialog dialog;
    public static final String PREFS_NAME = "Preferences";
    List<Pessoa> listaPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nome = (EditText) findViewById(R.id.editTextNome);
        EditText senha = (EditText) findViewById(R.id.editTextSenha);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        nome.setText(settings.getString("nome", ""));
        senha.setText(settings.getString("senha", ""));


    }


  public void principal(View view){


        Usua usuario =  new Usua();
        usuario.execute("http://172.21.151.219:8080/Tcc/rest/app/get2");



  }
    public void metodoChamatela(){
        String json = null;
        EditText nome = (EditText) findViewById(R.id.editTextNome);
        EditText senha = (EditText) findViewById(R.id.editTextSenha);
        String nomes = null, senhas = null;
        Intent intent = new Intent(this, Principal.class);

        if(listaPessoa.size() == 0){
            Toast.makeText(this, "Erro: Não há Usúarios Cadastrados no Sistema ", Toast.LENGTH_SHORT).show();
        }else{
            for (Pessoa pessoa: listaPessoa){

                if(pessoa.getNome().equals(nome.getText().toString()) && pessoa.getSenha().equals(senha.getText().toString())){

                    nomes = pessoa.getNome().toString();
                    senhas = pessoa.getSenha().toString();

//                    Gson gson = new Gson();
//
//                    // Convertendo um objeto Java para JSON e retorna uma String JSON
//                    // formatada
//                     json = gson.toJson(pessoa);
//                     System.out.printf(json);
                }
            }
            if(nomes!=null && senhas != null){

                intent.putExtra("nome", nomes);
                intent.putExtra("senha", senhas);

                CheckBox chkSalvar = (CheckBox)findViewById(R.id.checkBox);
                if (chkSalvar.isChecked()){
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("nome", nomes);
                    editor.putString("senha", senhas);

                    //Confirma a gravação dos dados
                    editor.commit();
                }
                startActivity(intent);
                
                finish();
            }else{
                Toast.makeText(this, "Usúario ou senha Incorreto!", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }
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

        private List<Pessoa> getPessoa(JSONArray jsonArray) {
            List<Pessoa> pessoas = new ArrayList<>();
            try {

                JSONObject pessoaJson;
                for (int i = 0; i < jsonArray.length(); i++) {
                    pessoaJson = new JSONObject(jsonArray.getString(i));
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(pessoaJson.getString("nome"));
                    pessoa.setSenha(pessoaJson.getString("senha"));

                    pessoas.add(pessoa);
                }
            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing", e);
            }
            return pessoas;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

           listaPessoa = getPessoa(jsonArray);


            metodoChamatela();
        }

        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray objetoArray ;
            objetoArray = getJsonArray(params[0]);

            return objetoArray;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Aguarde ", "Efetuando o acesso a aplicação, isso pode levar algum tempo !",false,true);

        }
    }


}
