package com.broovie.equipe.broovie.activities.Perfil;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.adapters.AmigosAdapter;
import com.broovie.equipe.broovie.adapters.FilmeAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Avaliacao;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.AvaliacaoResource;
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
    private Usuario usuario;
    private Button btnAdicionar;
    private Button btnAlterar;
    private TextView txtNome;
    private TextView txtNomeUsuario;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.activity_perfil, container, false);
            btnAdicionar = view.findViewById(R.id.btn_add_amigo);
            btnAlterar = view.findViewById(R.id.btn_alt_Usuario);
            txtNome = view.findViewById(R.id.perfil_txt_nome);
            txtNomeUsuario = view.findViewById(R.id.perfil_txt_nome_usuario);
            txtNome.setText(usuario.getNome());
            txtNomeUsuario.setText(usuario.getNomeUsuario());
            if(usuario.getCode() == UtilAutenticacao.USUARIO.getCode()){
                btnAdicionar.setVisibility(View.GONE);
                btnAlterar.setVisibility(View.VISIBLE);
            }else {
                btnAlterar.setVisibility(View.GONE);
                btnAdicionar.setVisibility(View.VISIBLE);
            }
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
