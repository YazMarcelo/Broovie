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

    private UsuarioResource apiUsuario = null;

    private String nomeUsuario = null;
    private String senha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogin = findViewById(R.id.txt_login);
        txtSenha = findViewById(R.id.txt_login_senha);
        apiUsuario = APIClient.getClient().create(UsuarioResource.class);
    }

    public void abrirTela(View view) {
        Intent it;
        switch (view.getId()) {
            case R.id.btn_cadastrar:
                it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
                break;
            case R.id.btn_login:
                nomeUsuario = txtLogin.getText().toString();
                senha = txtSenha.getText().toString();
                apiUsuario.autenticar(
                        Usuario.builder()
                                .nomeUsuario(nomeUsuario)
                                .senha(senha)
                                .build()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        String token = response.headers().get("Authorization");
                        if (token != null && token.contains("Bearer")) {
                            UtilAutenticacao.TOKEN = token;
                            apiUsuario.readByNomeUsuario(LoginActivity.this.nomeUsuario).enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                    if (response.body() != null && response.body().getCode() != null) {
                                        UtilAutenticacao.USUARIO = response.body();
                                        Intent intent = new Intent(LoginActivity.this, LayoutActivity.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuário ou senha não encontrados!", Toast.LENGTH_SHORT).show();
                            txtLogin.requestFocus();
                            txtLogin.setText("");
                            txtSenha.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


                break;
            default:
                Log.i(TAG, String.format("id: %s", view.getId()));
                break;
        }
    }
}
