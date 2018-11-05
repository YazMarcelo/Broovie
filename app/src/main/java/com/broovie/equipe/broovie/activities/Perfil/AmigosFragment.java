package com.broovie.equipe.broovie.activities.Perfil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.broovie.equipe.broovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AmigosFragment extends Fragment {
    View view;

    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
    protected ListView listViewCategory;
    public  AmigosFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_amigos, container, false);
       addAmigos(view);

        return view;
    }
    public void addAmigos(final View view) {
        listViewCategory = view.findViewById(R.id.lst_amigos);

        List<String[]> lstVetor = new ArrayList<>();

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

            SimpleAdapter adapter = new SimpleAdapter( getContext(),colecao,R.layout.item_amigo,from,to);

            listViewCategory.setAdapter(adapter);
        }
    }
}
