package com.broovie.equipe.broovie.activities.Usuario;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.LayoutActivity;
import com.broovie.equipe.broovie.activities.Perfil.PerfilActivity;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.UsuarioResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.Date;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarUsuarioActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtEmail;
    EditText txtDataNascimento;
    EditText txtPais;
    EditText txtNomeUsuario;
    Button btnAlterar;
    Usuario usuario;
    UsuarioResource apiUserResource;
    LayoutActivity layoutActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_usuario);
        layoutActivity = new LayoutActivity();
        apiUserResource = APIClient.getClient().create(UsuarioResource.class);

        txtNome = (EditText) findViewById(R.id.alt_txtNome);
        txtEmail = (EditText) findViewById(R.id.alt_txtEmail);
        txtDataNascimento = (EditText) findViewById(R.id.alt_txtDataNascimento);
        txtPais = (EditText) findViewById(R.id.alt_txtPais);
        txtNomeUsuario = (EditText) findViewById(R.id.alt_txtNomeUsuario);

        txtNome.setText(UtilAutenticacao.USUARIO.getNome());
        txtEmail.setText(UtilAutenticacao.USUARIO.getEmail());
        if (UtilAutenticacao.USUARIO.getDataNascimento() != null)
            txtDataNascimento.setText(formatarData(UtilAutenticacao.USUARIO.getDataNascimento()));
        txtPais.setText(UtilAutenticacao.USUARIO.getPais());
        txtNomeUsuario.setText(UtilAutenticacao.USUARIO.getNomeUsuario());


        btnAlterar = (Button) findViewById(R.id.btn_alterar);

    }

    private String formatarData(Date date) {
        return String.format("%2d/%2d/%d", date.getDay(), date.getMonth(), date.getYear() + 1900);
    }

    public void voltarTelaPrincipal(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelar:
                Intent it = new Intent(AlterarUsuarioActivity.this, LayoutActivity.class);
                startActivity(it);
                break;

        }
    }

    public void alterarUsuario(View view) {

        Usuario usuario = UtilAutenticacao.USUARIO;
        usuario.setNome(txtNome.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setPais(txtPais.getText().toString());
        //usuario.setDataNascimento(new Date());

        Call<Usuario> put = apiUserResource.put(usuario);

        apiUserResource.put(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.body() != null)
                    UtilAutenticacao.USUARIO = response.body();
                Intent it = new Intent(AlterarUsuarioActivity.this, LayoutActivity.class);
                startActivity(it);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });


    }
}
