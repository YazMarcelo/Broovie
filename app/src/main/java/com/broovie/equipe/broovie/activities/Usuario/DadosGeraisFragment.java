package com.broovie.equipe.broovie.activities.Usuario;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.broovie.equipe.broovie.R;

public class DadosGeraisFragment extends Fragment {
    EditText txtNome, txtEmail, txtDataNascimento, txtPais;

    private DadosGeraisFragment.onClickFragmentListener listener;

    public final DadosGeraisFragment.onClickFragmentListener getListener() {
        return this.listener;
    }

    public final void setListener(DadosGeraisFragment.onClickFragmentListener value) {
        this.listener = value;
    }

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dados_gerais, container, false);
        txtNome = rootView.findViewById(R.id.txt_nome);
        txtEmail = rootView.findViewById(R.id.txt_email);
        txtDataNascimento = rootView.findViewById(R.id.txt_data_nascimento);
        txtPais = rootView.findViewById(R.id.txt_pais);
        return rootView;
    }

    public interface onClickFragmentListener {
        void clicked();
    }
}
