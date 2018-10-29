package com.broovie.equipe.broovie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.Util.DebugActivity;
import com.broovie.equipe.broovie.activities.Usuario.CadastroUsuarioActivity;

public class LoginActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void abrirTela(View view) {
        Intent it = null;
        Bundle params = null;
        switch (view.getId()){
            case R.id.btn_cadastrar:
                //chamar tela
                Log.i(TAG, "\t\tCall view Product");
                it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                params = new Bundle();
                params.putString("nome","Tela de Produto");
                startActivity(it);
                break;
            case R.id.btn_login:
                //chamar tela
//                Log.i(TAG, "\t\tCall view Category");
//
//                it = new Intent(this, CategoryActivity.class);
//                params = new Bundle();
//                params.putString("nome","Tela de Categoria");
//                startActivity(it);
                break;
            default:
                Log.i(TAG, String.format("id: %s", view.getId()));
                break;
        }
    }
}
