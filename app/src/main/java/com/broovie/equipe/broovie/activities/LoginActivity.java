package com.broovie.equipe.broovie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void abrirTela(View view) {
        Intent it = null;
        Bundle params = null;
        switch (view.getId()) {
            case R.id.btn_cadastrar:
                //chamar tela
                Log.i(TAG, "\t\tCall view CadastroUsuarioActivity");
                it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                params = new Bundle();
                params.putString("nome", "Tela de Produto");
                startActivity(it);
                break;
            case R.id.btn_login:
                UsuarioResource apiUsuario = APIClient.getClient().create(UsuarioResource.class);
                apiUsuario.autenticar(Usuario.builder().nomeUsuario("gguuimaraes").senha("666").build()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), response.headers().get("Authorization"), Toast.LENGTH_LONG).show();
                        UtilAutenticacao.TOKEN = response.headers().get("Authorization");
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
