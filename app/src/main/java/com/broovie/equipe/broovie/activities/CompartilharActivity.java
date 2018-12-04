package com.broovie.equipe.broovie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.broovie.equipe.broovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CompartilharActivity extends AppCompatActivity {
    View view;
    EditText txtPesquisar;
    List<String[]> lstVetor = new ArrayList<>();
    List<String[]> lstVetorPesquisar = new ArrayList<>();
    SimpleAdapter adapter;

    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
    protected List<HashMap<String,String>> colecaoPesquisar = new ArrayList<HashMap<String,String>>();
    protected ListView listViewAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartilhar);

        listViewAmigos = findViewById(R.id.lst_amigos_compartilhar);
        txtPesquisar = (EditText) findViewById(R.id.txt_pesquisar_compartilhar);
        addAmigos();
        lstVetorPesquisar = lstVetor;
        colecaoPesquisar = colecao;

        txtPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    addAmigos();
                }else{
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchItem(String textToSearch){
        int cont = 0;
        colecao = colecaoPesquisar;
        while (cont < colecao.size()) {
          HashMap<String,String> map = colecao.get(cont);
            if(!map.get("nome").contains(textToSearch) && !map.get("userName").contains(textToSearch)){
                colecao.remove(map);
            }
          cont++;
        }

        adapter.notifyDataSetChanged();
    }

    public void addAmigos() {
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});
        lstVetor.add(new String[]{"Marcelo Hugo", "_marcelohu"});
        lstVetor.add(new String[]{"Mathes Teste", "_matheusBla"});
        lstVetor.add(new String[]{"Bruno Carlos", "_brunnin"});
        lstVetor.add(new String[]{"Wagner Moreira", "_wagao"});

        for(String[] amigo : lstVetor){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("nome", amigo[0]);
            item.put("userName", amigo[1]);

            colecao.add(item);

            String[] from =  {"nome", "userName"};
            int[] to = {R.id.labelNome, R.id.labelUsuario};

            adapter = new SimpleAdapter( getApplicationContext(),colecao,R.layout.item_amigo,from,to);

            listViewAmigos.setAdapter(adapter);
        }
    }
}
