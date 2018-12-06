package com.broovie.equipe.broovie.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Avaliacao;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.resources.AvaliacaoResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeActivity extends Fragment {
    View view;
    WebView mWebView;
    TextView txtNomeFilme;
    TextView txtSinopse;
    Filme filme;
    SimpleRatingBar srb;
    AvaliacaoResource apiAvaliacao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_filme, container, false);
        apiAvaliacao = APIClient.getClient().create(AvaliacaoResource.class);
        mWebView = (WebView) view.findViewById(R.id.filme_player);

        String frameVideo = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/av2jODMFu6M\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe></body></html>";

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

        txtNomeFilme = (TextView) view.findViewById(R.id.txt_nome_filme);
        txtSinopse = (TextView) view.findViewById(R.id.txt_sinopse);
        srb = view.findViewById(R.id.ratingBar);
        this.srb.setOnRatingBarChangeListener(new SimpleRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingBar simpleRatingBar, float rating, boolean fromUser) {
                avaliarFilme((int) rating);
            }
        });
        setDadosFilme(filme);
        return view;
    }

    public void avaliarFilme(int rating) {
        alterarAvaliacao(this.filme, Avaliacao.Nota.values()[rating]);
    }

    public void setDadosFilme(final Filme filme) {
        txtNomeFilme.setText(filme.getNome());
        txtSinopse.setText(filme.getSinopse());

        apiAvaliacao.avaliacao(UtilAutenticacao.USUARIO.getCode(), filme.getCode()).enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Avaliacao avaliacao = response.body();
                if (avaliacao != null) {
                    srb.setRating(avaliacao.getNota().ordinal());
                }
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    private void alterarAvaliacao(final Filme filme, final Avaliacao.Nota nota) {
        apiAvaliacao.avaliacao(UtilAutenticacao.USUARIO.getCode(), filme.getCode()).enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Avaliacao avaliacao = response.body();
                if (avaliacao != null && avaliacao.getNota() != nota) {
                    avaliacao.setNota(nota);
                    alterar(avaliacao);
                }
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setFilme(filme);
                avaliacao.setUsuario(UtilAutenticacao.USUARIO);
                avaliacao.setNota(Avaliacao.Nota.values()[nota.ordinal()]);
                avaliar(avaliacao);
            }
        });
    }


    private void alterar(Avaliacao avaliacao) {
        apiAvaliacao.put(avaliacao).enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Toast.makeText(view.getContext(), "Filme avaliado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void avaliar(Avaliacao avaliacao) {
        apiAvaliacao.post(avaliacao).enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Toast.makeText(view.getContext(), "Filme avaliado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
