package com.broovie.equipe.broovie.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.broovie.equipe.broovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotificacaoActivity extends Fragment {
    View view;
    ListView lstNotificacoes;
    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_notificacao, container, false);
        addAmigos(view);
        return view;
    }
    public void addAmigos(final View view) {
        lstNotificacoes = view.findViewById(R.id.lst_notificacoes);

        List<String> lstVetor = new ArrayList<>();

        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");
        lstVetor.add("Marcelo Hugo deseja te adicionar como amigo");


        for(String notificacao : lstVetor){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("descricao", notificacao);

            colecao.add(item);

            String[] from =  {"descricao"};
            int[] to = {R.id.labelNome, R.id.labelUsuario};

            SimpleAdapter adapter = new SimpleAdapter( getContext(),colecao,R.layout.item_notificacao,from,to);

            lstNotificacoes.setAdapter(adapter);
        }
    }
}
