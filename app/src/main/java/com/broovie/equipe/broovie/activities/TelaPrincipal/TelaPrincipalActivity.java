package com.broovie.equipe.broovie.activities.TelaPrincipal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Arquivo;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.FilmeResource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalActivity extends Fragment implements FilmesRecycleView.ItemClickListener{
    View view;
    private FilmesRecycleView adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getRecomendados();
//            }
//        });
//        t.run();
        getRecomendados();

        view = inflater.inflate(R.layout.item_categoria, container, false);



        List<Bitmap> bmpFotos = new ArrayList<>();
        ArrayList<String> filmesNomes = new ArrayList<>();

        for (Filme filme : filmes) {
            filmesNomes.add(filme.getNome());
            Bitmap bmp = BitmapFactory.decodeByteArray(filme.getFotoCapa().getBytes(), 0, filme.getFotoCapa().getBytes().length);
            bmpFotos.add(bmp);
        }





        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvFilme);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new FilmesRecycleView(getContext(), bmpFotos, filmesNomes);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    FilmeResource apiFilmeResource;
    List<Filme> filmes = new ArrayList<>();

    private void getRecomendados() {
        apiFilmeResource = APIClient.getClient().create(FilmeResource.class);
        Usuario usuario = new Usuario();
        usuario.setCode(1L);

        Call<List<Filme>> recomendados = apiFilmeResource.recomendados(usuario.getCode());
        recomendados.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                filmes.clear();
                filmes.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Toast.makeText(getContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
