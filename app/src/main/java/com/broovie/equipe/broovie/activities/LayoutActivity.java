package com.broovie.equipe.broovie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.Perfil.PerfilActivity;
import com.broovie.equipe.broovie.activities.TelaPrincipal.TelaPrincipalActivity;
import com.broovie.equipe.broovie.activities.Usuario.AlterarUsuarioActivity;
import com.broovie.equipe.broovie.activities.Usuario.CadastroUsuarioActivity;
import com.broovie.equipe.broovie.models.Recomendacao;

public class LayoutActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private PerfilActivity perfilActivity;
    private TelaPrincipalActivity telaPrincipalActivity;
    private FilmeActivity telaFilme;
    private PesquisarActivity pesquisarActivity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_notifications:
                    showFragment(telaFilme, "Filme");
                    return true;
                case R.id.navigation_home:
                    showFragment(telaPrincipalActivity, "Tela Principal");
                    return true;
                case R.id.navigation_perfil:
                    showFragment(perfilActivity, "Perfil");
                    return true;
                case R.id.navigation_pesquisa:
                    showFragment(pesquisarActivity, "Pesquisar");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        perfilActivity = new PerfilActivity();
        telaPrincipalActivity = new TelaPrincipalActivity();
        telaFilme = new FilmeActivity();
        pesquisarActivity = new PesquisarActivity();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        showFragment(telaPrincipalActivity, "Tela Principal");
    }

    private final void showFragment(Fragment fragmento, String nomePagina) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_base, fragmento, nomePagina).commit();
    }

    public void callToast(View view){
        Intent it = new Intent(LayoutActivity.this, CompartilharActivity.class);
        Bundle params = new Bundle();
        params.putString("nome","Tela de Produto");
        startActivity(it);
    }

    public void abrirTelaAlteracao(View view) {
        switch (view.getId()) {
            case R.id.btn_alt_Usuario:
                Intent it = new Intent(LayoutActivity.this, AlterarUsuarioActivity.class);
                startActivity(it);
                break;

        }
    }
}
