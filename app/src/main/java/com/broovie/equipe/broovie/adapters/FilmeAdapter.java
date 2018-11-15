package com.broovie.equipe.broovie.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.bootstrap.APIClient;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.util.DownloadImageTask;

import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.ViewHolder> {

    private List<Filme> filmes;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public FilmeAdapter(Context context, List<Filme> filmes) {
        this.layoutInflater = LayoutInflater.from(context);
        this.filmes = filmes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgFotoCapa;

        ViewHolder(View itemView) {
            super(itemView);
            imgFotoCapa = itemView.findViewById(R.id.imgFotoCapa);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_filme_por_categoria, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        new DownloadImageTask(holder.imgFotoCapa).execute(APIClient.ENDPOINT + this.filmes.get(position).getFotoCapa());
    }

    @Override
    public int getItemCount() {
        return this.filmes.size();
    }

    public Filme getItem(int position) {
        return this.filmes.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
