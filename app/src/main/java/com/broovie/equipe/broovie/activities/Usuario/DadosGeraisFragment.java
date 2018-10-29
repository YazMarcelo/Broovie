package com.broovie.equipe.broovie.activities.Usuario;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.broovie.equipe.broovie.R;

public class DadosGeraisFragment extends Fragment {
    private DadosGeraisFragment.onClickFragmentListener listener;

    public final DadosGeraisFragment.onClickFragmentListener getListener() {
        return this.listener;
    }

    public final void setListener(DadosGeraisFragment.onClickFragmentListener value) {
        this.listener = value;
    }

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_dados_gerais, container, false);
//        View var10000 = view.findViewById(R.id.button);
//        Button btnNextFragment = (Button)var10000;
//        btnNextFragment.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
//            public final void onClick(View it) {
//                DadosGeraisFragment.onClickFragmentListener var10000 = DadosGeraisFragment.this.getListener();
//                if (var10000 != null) {
//                    var10000.clicked();
//                }
//
//            }
//        }));
        return inflater.inflate(R.layout.fragment_dados_gerais, container, false);
    }

    public interface onClickFragmentListener {
        void clicked();
    }
}
