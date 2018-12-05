package com.broovie.equipe.broovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.models.Usuario;

import java.util.List;


/**
 * Created by bruno on 07/04/18.
 */

public class AmigosBaseAdapter extends BaseAdapter {


    Context context;
    List<Usuario> colecao;
    LayoutInflater inflter;
    private AmigosAdapter.ItemClickListener itemClickListener;


    public AmigosBaseAdapter(final Context applicationContext,
                         final List<Usuario> colecao) {
        this.context = applicationContext;
        this.colecao = colecao;

    }

    @Override
    public int getCount() {
        return this.colecao != null ? this.colecao.size() : 0;
    }

    @Override
    public Usuario getItem(int i) {
        return this.colecao.get(i);
    }

    private Usuario parsetItem(int i){
        return this.colecao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setClickListener(AmigosAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // inflate the layout for each list row
        //'Infla' o layout(pega a referencia) para ser trabalhada
        //no método
        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.item_amigo,
                            viewGroup, false);
        }

        // pega o objeto corrente da lista
        Usuario usuario = parsetItem(i);

        //Neste ponto vc ira popular os dados do seu layout,
        //utilizando JAVA.

        TextView campoNome, campoUsuario;

        //CASO não queria declarar um campo
        //((TextView)view.findViewById(R.id.txtItemNome)).setText(pessoa.getNome());

        campoNome = view.findViewById(R.id.labelNome);
        campoUsuario = view.findViewById(R.id.labelUsuario);

        campoNome.setText(usuario.getNome());
        campoUsuario.setText(usuario.getEmail());

        return view;
    }
}
