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
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.FilmeActivity;
import com.broovie.equipe.broovie.adapters.AmigosAdapter;
import com.broovie.equipe.broovie.models.Filme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AmigosFragment extends Fragment implements AmigosAdapter.ItemClickListener{
    View view;
    private AmigosAdapter amigoAdapter;

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

    @Override
    public void onItemClick(View view, int position) {
//        FilmeActivity filmeActivity = new FilmeActivity();
//        Filme filmeChamado = filmeAdapterUS.getItem(position);
//        filmeActivity.setFilme(filmeChamado);
//        showFragment(filmeActivity, filmeChamado.getNome());
        Toast.makeText(getContext(), "You clicked " + amigoAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
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
