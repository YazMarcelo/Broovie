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
import com.broovie.equipe.broovie.resources.RecomendacaoResource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalActivity extends Fragment implements FilmeAdapter.ItemClickListener {
    private View view;
    private FilmeAdapter adapter;
    private Usuario usuario = new Usuario();
    private RecomendacaoResource apiRecomendacao;
    private final List<Filme> filmesUS = new ArrayList<>();
    private final List<Filme> filmesMF = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            this.apiRecomendacao = APIClient.getClient().create(RecomendacaoResource.class);
            this.adapter = new FilmeAdapter(getContext(), this.filmesUS);
            this.adapter.setClickListener(this);
            this.view = inflater.inflate(R.layout.item_categoria, container, false);
            buscarRecomendacoes(146, Recomendacao.TipoRecomendacao.USER_SIMILARITY);
            RecyclerView recyclerView = this.view.findViewById(R.id.rvFilmes);
            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(horizontalLayoutManager);
            recyclerView.setAdapter(this.adapter);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return this.view;
    }

    private void buscarRecomendacoes(long codigoUsuario, final Recomendacao.TipoRecomendacao tipo) {
        apiRecomendacao.recomendacoes(codigoUsuario, tipo).enqueue(new Callback<List<Recomendacao>>() {
            @Override
            public void onResponse(Call<List<Recomendacao>> call, Response<List<Recomendacao>> response) {
                for (Recomendacao r : response.body()) {
                    if (tipo == Recomendacao.TipoRecomendacao.USER_SIMILARITY)
                        TelaPrincipalActivity.this.filmesUS.add(r.getFilme());
                    else if (tipo == Recomendacao.TipoRecomendacao.MATRIX_FACTORIZATION)
                        TelaPrincipalActivity.this.filmesMF.add(r.getFilme());
                }
            }

            @Override
            public void onFailure(Call<List<Recomendacao>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

}
