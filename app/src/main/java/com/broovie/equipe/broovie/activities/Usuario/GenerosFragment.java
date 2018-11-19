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
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Genero;
import com.broovie.equipe.broovie.resources.GeneroResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerosFragment extends Fragment {
    protected EditText txtName;
    protected EditText txtGeneroDescricao;

    protected List<Genero> generos = new ArrayList<Genero>();
    protected List<Genero> generosLst = new ArrayList<Genero>();
    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
    protected ListView listViewCategory;
    GeneroResource apiUserResouce;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View var10000 = inflater.inflate(R.layout.fragment_generos, container, false);
        final View view = var10000;
        addCategory(view);
        return view;
    }

    public void addCategory(final View view) {
//        apiUserResouce = APIClient.getClient().create(GeneroResource.class);
//
//
//        Call<List<Genero>> get = apiUserResouce.get();
//
//        get.enqueue(new Callback<List<Genero>>() {
//
//            @Override
//            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
//                listViewCategory = view.findViewById(R.id.lst_generos);
//
//                generosLst = response.body();
//
//                for (Genero g: generosLst) {
//                    //Criar dados para adapter
//                    HashMap<String,String> mapUser = new HashMap<String,String>();
//                    mapUser.put("id",String.valueOf(g.getId()));
//                    mapUser.put("descricao",g.getDescricao());
//
//                    colecao.add(mapUser);
//                }
//
//                String[] from = {"descricao"};
//                int[] to = {R.id.label_genero_descricao};
//
//                SimpleAdapter simpleAdapter =
//                        new SimpleAdapter(
//                                view.getContext(),
//                                colecao,
//                                R.layout.item_checkbox,
//                                from,
//                                to);
//
//                listViewCategory.setAdapter(simpleAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Genero>> call, Throwable t) {
//                Toast.makeText(view.getContext(), t.toString(),Toast.LENGTH_LONG).show();
//            }
//        });


        listViewCategory = view.findViewById(R.id.lst_generos);

        String[] generosVt = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        for (String genero: generosVt) {
            String descricao;
            descricao = genero;

            generos.add(new Genero(descricao));

            HashMap<String,String> item = new HashMap<String,String>();
            item.put("descricao",descricao);

            colecao.add(item);

            String[] from =  {"descricao"};
            int[] to = {R.id.label_genero_descricao};

            SimpleAdapter adapter = new SimpleAdapter( getContext(),colecao,R.layout.item_checkbox,from,to);

            listViewCategory.setAdapter(adapter);
        }
    }
}
