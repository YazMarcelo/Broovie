package com.broovie.equipe.broovie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.UsuarioResource;
import com.broovie.equipe.broovie.util.DebugActivity;
import com.broovie.equipe.broovie.activities.TelaPrincipal.TelaPrincipalActivity;
import com.broovie.equipe.broovie.activities.Usuario.CadastroUsuarioActivity;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends DebugActivity {

    private EditText txtLogin = null;
    private EditText txtSenha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogin = findViewById(R.id.txt_login);
        txtSenha = findViewById(R.id.txt_login_senha);
    }

    public void abrirTela(View view) {
        Intent it;
        switch (view.getId()) {
            case R.id.btn_cadastrar:
                it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
                break;
            case R.id.btn_login:
                String nomeUsuario = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();
                UsuarioResource apiUsuario = APIClient.getClient().create(UsuarioResource.class);
                apiUsuario.autenticar(
                        Usuario.builder()
                                .nomeUsuario(nomeUsuario)
                                .senha(senha)
                                .build()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        String token = response.headers().get("Authorization");
                        if (token != null) UtilAutenticacao.TOKEN = token;
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                if (UtilAutenticacao.TOKEN.contains("Bearer")) {
                    apiUsuario.readByNomeUsuario(nomeUsuario).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.body() != null && response.body().getCode() != null) {
                                UtilAutenticacao.USUARIO = response.body();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    if (UtilAutenticacao.USUARIO != null) {
                        it = new Intent(LoginActivity.this, LayoutActivity.class);
                        startActivity(it);
                    }
                }
                break;
            default:
                Log.i(TAG, String.format("id: %s", view.getId()));
                break;
        }
    }
}
