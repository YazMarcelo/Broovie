package com.broovie.equipe.broovie.activities.Perfil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.FilmeActivity;
import com.broovie.equipe.broovie.adapters.AvaliacaoAdapter;
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

public class AvaliacoesFragment extends Fragment implements AvaliacaoAdapter.ItemClickListener {

    View view;
    private AvaliacaoAdapter avaliacaoAdapter;

    private final List<Avaliacao> avaliacoes = new ArrayList<>();
    private AvaliacaoResource apiAvaliacao;
    private RecyclerView recyclerViewFilmesAvaliados = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.apiAvaliacao = APIClient.getClient().create(AvaliacaoResource.class);
        this.avaliacaoAdapter = new AvaliacaoAdapter(getContext(), this.avaliacoes);
        view = inflater.inflate(R.layout.fragment_avaliacoes, container, false);
        this.avaliacaoAdapter.setClickListener(this);
        this.getFilmesAvaliados();
        this.recyclerViewFilmesAvaliados = view.findViewById(R.id.recyclerViewFilmesAvaliados);
        this.recyclerViewFilmesAvaliados.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        return view;
    }

    public AvaliacoesFragment() {
    }

    private void getFilmesAvaliados() {
        PerfilActivity parentFrag = ((PerfilActivity)AvaliacoesFragment.this.getParentFragment());
        apiAvaliacao.getAvaliacoes(parentFrag.getUsuario().getCode()).enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                for (Avaliacao avaliacao : response.body()) {
                    if (!avaliacoes.contains(avaliacao.getFilme()))
                        avaliacoes.add(avaliacao);
                }
                recyclerViewFilmesAvaliados.setAdapter(avaliacaoAdapter);
            }

            @Override
            public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        FilmeActivity filmeActivity = new FilmeActivity();
        Filme filmeChamado = avaliacaoAdapter.getItem(position).getFilme();
        filmeActivity.setFilme(filmeChamado);
        showFragment(filmeActivity, filmeChamado.getNome());
    }

    private final void showFragment(Fragment fragmento, String nomePagina) {
        FragmentManager fragmentManager = this.getParentFragment().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_base, fragmento, nomePagina).commit();
    }
}
