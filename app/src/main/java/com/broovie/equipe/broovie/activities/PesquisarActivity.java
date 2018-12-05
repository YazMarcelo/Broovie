package com.broovie.equipe.broovie.activities;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.Perfil.PerfilActivity;
import com.broovie.equipe.broovie.adapters.AmigosAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.UsuarioResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarActivity extends Fragment implements AmigosAdapter.ItemClickListener {
    View view;
    UsuarioResource apiUserResource;
    List<Usuario> lstUsuario = new ArrayList<>();
    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
    EditText txtPesquisar;
    Button btnPesquisar;
    protected ListView listPesquisar;

    private AmigosAdapter amigoAdapter;
    protected List<Usuario> amigos = new ArrayList<>();
    private RecyclerView recyclerViewAmigos;
    Usuario usuarioChamado = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_pesquisar, container, false);
        txtPesquisar = view.findViewById(R.id.txt_pesquisar);
        btnPesquisar = view.findViewById(R.id.btn_pesquisar);

        this.amigoAdapter = new AmigosAdapter(getContext(), this.amigos);
        this.amigoAdapter.setClickListener(this);
        apiUserResource = APIClient.getClient().create(UsuarioResource.class);
        this.recyclerViewAmigos = view.findViewById(R.id.lst_pesquisar);
        this.recyclerViewAmigos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        this.btnPesquisar.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
               pesquisar();
            }
        }));

        return view;
    }

    public void pesquisar(){
        String nome = txtPesquisar.getText().toString();

        Call<List<Usuario>> get = apiUserResource.pesquisar(nome, nome);

        get.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                amigos.addAll(response.body());
                recyclerViewAmigos.setAdapter(amigoAdapter);
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        PerfilActivity perfilActivity = new PerfilActivity();
        usuarioChamado = amigoAdapter.getItem(position);
        perfilActivity.setUsuario(usuarioChamado);
        showFragment(perfilActivity, usuarioChamado.getNome());
    }

    private final void showFragment(Fragment fragmento, String nomePagina) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_base, fragmento, nomePagina).commit();
    }
}
