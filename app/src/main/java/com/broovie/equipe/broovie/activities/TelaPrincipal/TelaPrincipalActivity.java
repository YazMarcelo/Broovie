package com.broovie.equipe.broovie.activities.TelaPrincipal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.broovie.equipe.broovie.adapters.FilmeAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Recomendacao;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.RecomendacaoResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalActivity extends Fragment implements FilmeAdapter.ItemClickListener {
    private View view;
    private FilmeAdapter filmeAdapterUS;
    private FilmeAdapter filmeAdapterMF;
    private Usuario usuario = new Usuario();
    private RecomendacaoResource apiRecomendacao;
    private final Set<Recomendacao> recomendacoes = new HashSet<>();
    private final List<Filme> filmesUS = new ArrayList<>();
    private final List<Filme> filmesMF = new ArrayList<>();
    private RecyclerView recyclerViewFilmesUS = null;
    private RecyclerView recyclerViewFilmesMF = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            this.apiRecomendacao = APIClient.getClient().create(RecomendacaoResource.class);
            this.filmeAdapterUS = new FilmeAdapter(getContext(), this.filmesUS);
            this.filmeAdapterUS.setClickListener(this);
            this.filmeAdapterMF = new FilmeAdapter(getContext(), this.filmesMF);
            this.filmeAdapterMF.setClickListener(this);
            this.view = inflater.inflate(R.layout.activity_tela_principal, container, false);
            this.buscarRecomendacoes(Recomendacao.TipoRecomendacao.USER_SIMILARITY);
            this.recyclerViewFilmesUS = this.view.findViewById(R.id.recyclerViewFilmesUS);
            this.recyclerViewFilmesUS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            this.recyclerViewFilmesUS.setAdapter(this.filmeAdapterUS);
            this.buscarRecomendacoes(Recomendacao.TipoRecomendacao.MATRIX_FACTORIZATION);
            this.recyclerViewFilmesMF = this.view.findViewById(R.id.recyclerViewFilmesMF);
            this.recyclerViewFilmesMF.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            this.recyclerViewFilmesMF.setAdapter(this.filmeAdapterMF);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return this.view;
    }

    public void buscarRecomendacoes(final Recomendacao.TipoRecomendacao tipo) {
        apiRecomendacao.recomendacoes(UtilAutenticacao.USUARIO.getCode(), tipo).enqueue(new Callback<List<Recomendacao>>() {
            @Override
            public void onResponse(Call<List<Recomendacao>> call, Response<List<Recomendacao>> response) {
                recomendacoes.addAll(response.body());
                TelaPrincipalActivity.this.filtrarFilmes();
            }

            @Override
            public void onFailure(Call<List<Recomendacao>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        FilmeActivity filmeActivity = new FilmeActivity();
        Filme filmeChamado = filmeAdapterUS.getItem(position);
        filmeActivity.setFilme(filmeChamado);
        showFragment(filmeActivity, filmeChamado.getNome());
        Toast.makeText(getContext(), "You clicked " + filmeAdapterUS.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    private final void showFragment(Fragment fragmento, String nomePagina) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_base, fragmento, nomePagina).commit();
    }

    private void filtrarFilmes() {
        for (Recomendacao r : recomendacoes) {
            if (r.getTipoRecomendacao() == Recomendacao.TipoRecomendacao.USER_SIMILARITY)
                if (!this.filmesUS.contains(r.getFilme()))
                    this.filmesUS.add(r.getFilme());
            if (r.getTipoRecomendacao() == Recomendacao.TipoRecomendacao.MATRIX_FACTORIZATION)
                if (!this.filmesMF.contains(r.getFilme()))
                    this.filmesMF.add(r.getFilme());
        }
    }

}
