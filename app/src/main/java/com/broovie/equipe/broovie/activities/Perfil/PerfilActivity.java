package com.broovie.equipe.broovie.activities.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.LayoutActivity;
import com.broovie.equipe.broovie.activities.LoginActivity;
import com.broovie.equipe.broovie.activities.TelaPrincipal.TelaPrincipalActivity;
import com.broovie.equipe.broovie.activities.Usuario.AlterarUsuarioActivity;
import com.broovie.equipe.broovie.activities.Usuario.CadastroUsuarioActivity;
import com.broovie.equipe.broovie.adapters.FilmeAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Avaliacao;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Recomendacao;
import com.broovie.equipe.broovie.resources.AvaliacaoResource;
import com.broovie.equipe.broovie.resources.RecomendacaoResource;
import com.broovie.equipe.broovie.resources.UsuarioResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends Fragment {

   private TabLayout tabLayout;
   private ViewPager viewPager;
    private FilmeAdapter filmeAdapter;
    private final List<Filme> filmes = new ArrayList<>();
    private List<Avaliacao> avaliados = new ArrayList<>();
   private AvaliacaoResource apiAvaliacao;
    private RecyclerView recyclerViewFilmesAvaliados = null;
   View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            this.apiAvaliacao = APIClient.getClient().create(AvaliacaoResource.class);
            this.filmeAdapter = new FilmeAdapter(getContext(), this.filmes);
            view = inflater.inflate(R.layout.activity_perfil, container, false);
            this.getFilmesAvaliados();
            this.recyclerViewFilmesAvaliados = this.view.findViewById(R.id.recyclerViewFilmesAvaliados);
            this.recyclerViewFilmesAvaliados.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            this.recyclerViewFilmesAvaliados.setAdapter(this.filmeAdapter);



            tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
            viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
            adapter.AddFragment(new AmigosFragment(), "Amigos");
            adapter.AddFragment(new AvaliacoesFragment(), "Avaliações");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private void getFilmesAvaliados() {
        apiAvaliacao.getAvaliacoes(146).enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                avaliados.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });

    }
}
