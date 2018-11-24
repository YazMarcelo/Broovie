package com.broovie.equipe.broovie.activities.TelaPrincipal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.adapters.FilmeAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Recomendacao;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.FilmeResource;
import com.broovie.equipe.broovie.resources.RecomendacaoResource;
import com.broovie.equipe.broovie.resources.UsuarioResource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalActivity extends Fragment implements FilmeAdapter.ItemClickListener {
    View view;
    private FilmeAdapter adapter;

    private Usuario usuario = new Usuario();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_categoria, container, false);

        try {
            RecyclerView recyclerView = view.findViewById(R.id.rvFilmes);
            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            Response<List<Recomendacao>> recomendacoes = APIClient.getClient().create(RecomendacaoResource.class).recomendacoes(146, Recomendacao.TipoRecomendacao.USER_SIMILARITY).execute();
            adapter = new FilmeAdapter(getContext(), recomendacoes.body());
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println(e);
        }
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

}
