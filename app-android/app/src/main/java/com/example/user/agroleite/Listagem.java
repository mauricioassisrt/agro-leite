package com.example.user.agroleite;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listagem extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView vacas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);


        vacas = (ListView) findViewById(R.id.lista);
        final String[] times = new String[]{"Branquinha"+"Litro 10", "Mimosa "+"Litro 20", "Laranja"+"Litro 40"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, times);

        vacas.setAdapter(adapter);
        vacas.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String time = (String) parent.getAdapter().getItem(position);


        Intent exibir = new Intent(this, Individual.class);
        exibir.putExtra("vaca", time);
        startActivity(exibir);
    }
}
