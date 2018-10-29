package com.broovie.equipe.broovie.activities.Usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.models.Genero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerosFragment extends Fragment {
    protected EditText txtName;
    protected EditText txtGeneroDescricao;

    protected List<Genero> generos = new ArrayList<Genero>();
    protected List<HashMap<String,String>> dados = new ArrayList<HashMap<String,String>>();
    protected ListView listViewCategory;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View var10000 = inflater.inflate(R.layout.fragment_generos, container, false);
        View view = var10000;
        addCategory(view);
        return view;
    }

    public void addCategory(View view) {
        String[] generosVt = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        for (String genero: generosVt) {
            String descricao;
            descricao = genero;

            generos.add(new Genero(descricao));

            HashMap<String,String> item = new HashMap<String,String>();
            item.put("descricao",descricao);

            dados.add(item);

            String[] from =  {"descricao"};
            int[] to = {R.id.label_genero_descricao};

            listViewCategory = view.findViewById(R.id.lst_generos);

            SimpleAdapter adapter = new SimpleAdapter(getContext(),dados,R.layout.item_checkbox,from,to);

            listViewCategory.setAdapter(adapter);
        }
    }
}
