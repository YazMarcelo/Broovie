package com.broovie.equipe.broovie.activities.Usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.activities.TelaPrincipal.TelaPrincipalActivity;
import com.broovie.equipe.broovie.adapters.GeneroAdapter;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Genero;
import com.broovie.equipe.broovie.models.Recomendacao;
import com.broovie.equipe.broovie.resources.GeneroResource;
import com.broovie.equipe.broovie.util.UtilAutenticacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerosFragment extends Fragment implements GeneroAdapter.ItemClickListener  {
    GeneroAdapter generoAdapter;
    private List<Genero> lstGeneros = new ArrayList<>();
    private List<Genero> lstGenerosChecked = new ArrayList<>();
    private GeneroResource apiGenero;
    RecyclerView recyclerViewGenero;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            this.apiGenero = APIClient.getClient().create(GeneroResource.class);
            this.view = inflater.inflate(R.layout.fragment_generos, container, false);
            this.generoAdapter = new GeneroAdapter(getContext(), this.lstGeneros);
            this.generoAdapter.setClickListener(this);
            this.recyclerViewGenero = this.view.findViewById(R.id.lst_generos);
            getGeneros();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_genero);
        checkBox.setChecked(!checkBox.isChecked());
        if(checkBox.isChecked()){
            lstGenerosChecked.add(generoAdapter.getItem(position));
        }else {
            if(lstGenerosChecked.contains(generoAdapter.getItem(position))){
                lstGenerosChecked.remove(generoAdapter.getItem(position));
            }
        }
        Toast.makeText(getContext(), "You clicked " + generoAdapter.getItem(position).getDescricao() + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    public void getGeneros() {
        Call<List<Genero>> get = apiGenero.get();
                get.enqueue(new Callback<List<Genero>>() {

            @Override

            public void onResponse(Call<List<Genero>> call,
                                   Response<List<Genero>> response) {
                lstGeneros.addAll(response.body());
                recyclerViewGenero.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerViewGenero.setAdapter(generoAdapter);
            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                Toast.makeText(view.getContext(), t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }


//    protected EditText txtName;
//    protected EditText txtGeneroDescricao;
//
//    protected List<Genero> generos = new ArrayList<Genero>();
//    protected List<Genero> generosLst = new ArrayList<Genero>();
//    protected List<HashMap<String,String>> colecao = new ArrayList<HashMap<String,String>>();
//    protected ListView listViewCategory;
//    GeneroResource apiUserResouce;
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View var10000 = inflater.inflate(R.layout.fragment_generos, container, false);
//        final View view = var10000;
//        addCategory(view);
//        return view;
//    }
//
//    public void addCategory(final View view) {
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
//                    mapUser.put("id",String.valueOf(g.getCode()));
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


//        listViewCategory = view.findViewById(R.id.lst_generos);
//
//        String[] generosVt = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
//                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
//                "KitKat", "Lollipop", "Marshmallow", "Nougat" };
//
//        for (String genero: generosVt) {
//            String descricao;
//            descricao = genero;
//
//            generos.add(new Genero(descricao));
//
//            HashMap<String,String> item = new HashMap<String,String>();
//            item.put("descricao",descricao);
//
//            colecao.add(item);
//
//            String[] from =  {"descricao"};
//            int[] to = {R.id.label_genero_descricao};
//
//            SimpleAdapter adapter = new SimpleAdapter( getContext(),colecao,R.layout.item_checkbox,from,to);
//
//            listViewCategory.setAdapter(adapter);
//        }
//    }
}
