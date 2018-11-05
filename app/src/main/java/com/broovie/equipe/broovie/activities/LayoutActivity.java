package com.broovie.equipe.broovie.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.Perfil.PerfilActivity;

public class LayoutActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private PerfilActivity perfilActivity;
    private TelaPrincipalActivity telaPrincipalActivity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_home:
                    showFragment(telaPrincipalActivity, "Tela Principal");
                    return true;
                case R.id.navigation_perfil:
                    showFragment(perfilActivity, "Perfil");
                    return true;
                case R.id.navigation_pesquisa:
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final void showFragment(Fragment fragmento, String nomePagina) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_base, fragmento, nomePagina).commit();
    }

}
