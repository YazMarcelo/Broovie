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
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.FilmeResource;
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
        getUsuario(1);

        view = inflater.inflate(R.layout.item_categoria, container, false);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvFilmes);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new FilmeAdapter(getContext(), usuario.getRecomendados());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    UsuarioResource usuarioResource;

    private void getUsuario(long code) {
        usuarioResource = APIClient.getClient().create(UsuarioResource.class);
        usuarioResource.get(code).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
