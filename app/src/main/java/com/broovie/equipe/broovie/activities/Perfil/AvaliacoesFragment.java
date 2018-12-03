package com.broovie.equipe.broovie.activities.Perfil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.adapters.FilmeAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Avaliacao;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.resources.AvaliacaoResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvaliacoesFragment extends Fragment {

    View view;
    private FilmeAdapter filmeAdapter;

    private final List<Filme> filmes = new ArrayList<>();
    private AvaliacaoResource apiAvaliacao;
    private RecyclerView recyclerViewFilmesAvaliados = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.apiAvaliacao = APIClient.getClient().create(AvaliacaoResource.class);
        this.filmeAdapter = new FilmeAdapter(getContext(), this.filmes);
        view = inflater.inflate(R.layout.fragment_avaliacoes, container, false);
        this.getFilmesAvaliados();
        this.recyclerViewFilmesAvaliados = view.findViewById(R.id.recyclerViewFilmesAvaliados);
        this.recyclerViewFilmesAvaliados.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        this.recyclerViewFilmesAvaliados.setAdapter(this.filmeAdapter);

        return view;
    }

    public AvaliacoesFragment() {
    }

    private void getFilmesAvaliados() {
        apiAvaliacao.getAvaliacoes(UtilAutenticacao.USUARIO.getCode()).enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                for (Avaliacao avaliacao : response.body()) {
                    if (!filmes.contains(avaliacao.getFilme()))
                        filmes.add(avaliacao.getFilme());
                }
            }

            @Override
            public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
