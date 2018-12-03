package com.broovie.equipe.broovie.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.models.Filme;

public class FilmeActivity extends Fragment {
    View view;
    WebView mWebView;
    TextView txtNomeFilme;
    TextView txtSinopse;
    Filme filme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_filme, container, false);
        mWebView = (WebView) view.findViewById(R.id.filme_player);

        String frameVideo = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/voYDlnfcchs\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe></body></html>";

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

        txtNomeFilme = (TextView) view.findViewById(R.id.txt_nome_filme);
        txtSinopse = (TextView) view.findViewById(R.id.txt_sinopse);
        setDadosFilme(filme);
        return view;
    }

    public void setDadosFilme(Filme filme){
        txtNomeFilme.setText(filme.getNome());
        txtSinopse.setText(filme.getSinopse());
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
