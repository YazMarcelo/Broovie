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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.FilmeActivity;
import com.broovie.equipe.broovie.adapters.AmigosAdapter;
import com.broovie.equipe.broovie.adapters.AmigosBaseAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.UsuarioResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmigosFragment extends Fragment implements AmigosAdapter.ItemClickListener{
    View view;
    private AmigosBaseAdapter amigoAdapter;

    protected UsuarioResource apiUserResouce;
    protected List<Usuario> amigos = new ArrayList<>();
    private ListView recyclerViewAmigos;

    public  AmigosFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_amigos, container, false);
            amigoAdapter = new AmigosBaseAdapter(this.getContext(), amigos);
            apiUserResouce = APIClient.getClient().create(UsuarioResource.class);
            this.recyclerViewAmigos = view.findViewById(R.id.recyclerViewAmigos);
            addAmigos(view);
            this.recyclerViewAmigos.setAdapter(this.amigoAdapter);
        }catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
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
        Call<List<Usuario>> get = apiUserResouce.getAmigos(UtilAutenticacao.USUARIO.getCode());

        get.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                amigos.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
