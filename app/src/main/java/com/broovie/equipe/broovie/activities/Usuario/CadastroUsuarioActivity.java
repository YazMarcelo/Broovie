package com.broovie.equipe.broovie.activities.Usuario;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.models.Usuario;
import com.broovie.equipe.broovie.resources.UsuarioResource;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuarioActivity extends AppCompatActivity implements DadosGeraisFragment.onClickFragmentListener {

    UsuarioResource apiUserResouce;

    public Fragment prevFragment;
    public Fragment proxFragment;
    public DadosGeraisFragment dadosGeraisFragment;
    public DadosLoginFragment dadosLoginFragment;
    public GenerosFragment generosFragment;
    private TextView tvFirst;
    private TextView tvSecond;
    private boolean tvFirstIsCheck = true;
    private int count = 0;

    EditText txtNome, txtEmail, txtDataNascimento, txtPais, txtLogin, txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        this.initFragment();
        this.showDadosGeraisFragment();
        this.tvFirst = (TextView)this.findViewById(R.id.btn_prev);
        this.tvSecond = (TextView)this.findViewById(R.id.btn_prox);
        if (this.tvFirst != null) {
            this.tvFirst.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    count = count == 0 ? 0 : count - 1;
                    CadastroUsuarioActivity.this.firstSelected();
                    CadastroUsuarioActivity.this.showDadosGeraisFragment();

                    settarBotoes();
                }
            }));
        }

        if (this.tvSecond != null) {
            this.tvSecond.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    count = count == 2 ? 2 : count + 1;
                    CadastroUsuarioActivity.this.secondSelected();
                    CadastroUsuarioActivity.this.showDadosLoginFragment();

                    settarBotoes();
                }
            }));
        }
    }

    private void settarBotoes(){
        switch(count){
            case 0:
                setPrevFragment(null);
                setProxFragment(dadosLoginFragment);
                break;
            case 1:
                setPrevFragment(dadosGeraisFragment);
                setProxFragment(generosFragment);
                break;
            case 2:
                setPrevFragment(dadosLoginFragment);
                setProxFragment(null);
                break;
        }
    }

    public String getNameClass(){
        switch(count){
            case 0:
                return DadosGeraisFragment.class.getName();
            case 1:
                return DadosLoginFragment.class.getName();
            case 2:
                return GenerosFragment.class.getName();
            default:
                return null;
        }
    }

    private final void initFragment() {
        dadosGeraisFragment = new DadosGeraisFragment();
        dadosLoginFragment = new DadosLoginFragment();
        generosFragment = new GenerosFragment();
        dadosGeraisFragment.setListener((DadosGeraisFragment.onClickFragmentListener)this);
        this.prevFragment = dadosGeraisFragment;
        this.proxFragment = dadosLoginFragment;
    }

    private final void firstSelected() {
        this.tvFirstIsCheck = true;
    }

    private final void secondSelected() {
        this.tvFirstIsCheck = false;
    }

    private final void showDadosGeraisFragment() {
        FragmentTransaction var10000 = this.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_in,R.anim.left_out);
        var10000.replace(R.id.main_layout, prevFragment, getNameClass()).commit();
        this.firstSelected();
    }

    private final void showDadosLoginFragment() {
        FragmentTransaction var10000 = this.getSupportFragmentManager().beginTransaction().addToBackStack((String)null).setCustomAnimations(R.anim.right_in,R.anim.right_out,R.anim.left_in,R.anim.left_out);
        var10000.replace(R.id.main_layout, proxFragment, getNameClass()).commit();
        this.secondSelected();
    }

    public void clicked() {
        this.showDadosLoginFragment();
    }

    public final void setPrevFragment(Fragment var1) {
        this.prevFragment = var1;
    }

    public final void setProxFragment(Fragment var1) {
        this.proxFragment = var1;
    }

    public final TextView getTvFirst() {
        return this.tvFirst;
    }

    public final void setTvFirst(TextView var1) {
        this.tvFirst = var1;
    }

    public final TextView getTvSecond() {
        return this.tvSecond;
    }

    public final void setTvSecond(TextView var1) {
        this.tvSecond = var1;
    }

    public final boolean getTvFirstIsCheck() {
        return this.tvFirstIsCheck;
    }

    public final void setTvFirstIsCheck(boolean var1) {
        this.tvFirstIsCheck = var1;
    }

    public void onBackPressed() {
        if (!this.tvFirstIsCheck) {
            this.firstSelected();
            this.tvFirstIsCheck = true;
        }

        super.onBackPressed();
    }

    public void addUser(View view){
        txtNome = findViewById(R.id.txt_nome);
        txtEmail = findViewById(R.id.txt_email);
        txtDataNascimento = findViewById(R.id.txt_data_nascimento);
        txtPais = findViewById(R.id.txt_pais);
        txtLogin = findViewById(R.id.txt_nome_usuario);
        txtSenha = findViewById(R.id.txt_senha);


        final Usuario user = Usuario.builder()
                .nome(txtNome.getText().toString())
                .email(txtEmail.getText().toString())
                .dataNascimento(new Date(2018,10,29))
                .pais(txtPais.getText().toString())
                .nomeUsuario(txtLogin.getText().toString())
                .senha(txtSenha.getText().toString()).build();


        Call<Usuario> post = apiUserResouce.post(user);
        post.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario u = response.body();
                Toast.makeText(getApplicationContext(),
                        u.toString(),
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
