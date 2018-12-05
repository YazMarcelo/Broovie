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
import com.broovie.equipe.broovie.models.Avaliacao;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.util.DownloadImageTask;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

public class AvaliacaoAdapter extends RecyclerView.Adapter<AvaliacaoAdapter.ViewHolder> {

    private List<Avaliacao> avaliacoes;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public AvaliacaoAdapter(Context context, List<Avaliacao> avaliacoes) {
        this.layoutInflater = LayoutInflater.from(context);
        this.avaliacoes = avaliacoes;
        this.avaliacoes = avaliacoes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgFotoCapa;
        SimpleRatingBar srb;

        ViewHolder(View itemView) {
            super(itemView);
            imgFotoCapa = itemView.findViewById(R.id.imgAvaliacaoFotoCapa);
            srb = itemView.findViewById(R.id.filmeAvaliadoRatingBar);
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
        View view = layoutInflater.inflate(R.layout.item_filme_avaliado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        new DownloadImageTask(holder.imgFotoCapa).execute(APIClient.ENDPOINT + this.avaliacoes.get(position).getFilme().getFotoCapa());
        holder.srb.setRating(5);
    }

    @Override
    public int getItemCount() {
        return this.avaliacoes.size();
    }

    public Avaliacao getItem(int position) {
        return this.avaliacoes.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
