package com.broovie.equipe.broovie.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

        String frameVideo = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/voYDlnfcchs\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe></body></html>";

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

        txtNomeFilme = (TextView) view.findViewById(R.id.txt_nome_filme);
        txtSinopse = (TextView) view.findViewById(R.id.txt_sinopse);
        srb = view.findViewById(R.id.ratingBar);
        setDadosFilme(filme);

        this.srb.setOnTouchListener((View.OnTouchListener)(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                avaliarFilme((int)srb.getRating());
                srb.setRating(srb.getRating());
                return true;
            }
        }));
        return view;
    }

    public void avaliarFilme(int rating){
        Avaliacao avl = new Avaliacao();
        avl.setFilme(filme);
        avl.setUsuario(UtilAutenticacao.USUARIO);
        avl.setNota(Avaliacao.Nota.values()[rating]);
        Call<Avaliacao> post = apiAvaliacao.avaliar(avl);
        post.enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Toast.makeText(view.getContext(), "Avaliado com sucesso",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setDadosFilme(Filme filme){
        txtNomeFilme.setText(filme.getNome());
        txtSinopse.setText(filme.getSinopse());
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
