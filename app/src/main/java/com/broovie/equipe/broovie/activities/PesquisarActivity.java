package com.broovie.equipe.broovie.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
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

public class PesquisarActivity extends Fragment {
    View view;
    UsuarioResource apiUserResource;
    List<Usuario> lstUsuario = new ArrayList<>();
    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
    EditText txtPesquisar;
    Button btnPesquisar;
    protected ListView listPesquisar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_pesquisar, container, false);
        txtPesquisar = view.findViewById(R.id.txt_pesquisar);
        listPesquisar = view.findViewById(R.id.lst_pesquisar);
        btnPesquisar = view.findViewById(R.id.btn_pesquisar);

        this.btnPesquisar.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
               pesquisar();
            }
        }));

        return view;
    }

    public void pesquisar(){
        apiUserResource = APIClient.getClient().create(UsuarioResource.class);
        String nome = txtPesquisar.getText().toString();


        Call<List<Usuario>> get = apiUserResource.pesquisar(nome, nome);

        get.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                lstUsuario = response.body();


                for (Usuario u: lstUsuario) {
                    //Criar dados para adapter
                    HashMap<String, String> mapUser = new HashMap<String, String>();
                    mapUser.put("nome", String.valueOf(u.getNome()));
                    mapUser.put("userName", u.getNomeUsuario());

                    colecao.add(mapUser);

                    String[] from = {"nome", "userName"};
                    int[] to = {R.id.labelNome, R.id.labelUsuario};

                    SimpleAdapter adapter = new SimpleAdapter(getContext(), colecao, R.layout.item_amigo, from, to);

                    listPesquisar.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
