package com.broovie.equipe.broovie.activities.Usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.broovie.equipe.broovie.R;

public class DadosLoginFragment extends Fragment {
    EditText txtNomeUsuario, txtSenha, txtConfirmarSenha;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dados_login, container, false);
        txtNomeUsuario = rootView.findViewById(R.id.txt_nome_usuario);
        txtConfirmarSenha = rootView.findViewById(R.id.txt_confirmar_senha);
        txtSenha = rootView.findViewById(R.id.txt_senha);
        return rootView;
    }
}