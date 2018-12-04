package com.broovie.equipe.broovie.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.broovie.equipe.broovie.R;
import com.broovie.equipe.broovie.models.Filme;
import com.broovie.equipe.broovie.models.Genero;

import java.util.List;

public class GeneroAdapter extends RecyclerView.Adapter<GeneroAdapter.ViewHolder> {

    private List<Genero> genero;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public GeneroAdapter(Context context, List<Filme> filmes) {
        this.layoutInflater = LayoutInflater.from(context);
        this.genero = genero;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox checkBox;
        TextView txtGenero;

        ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_genero);
            txtGenero = itemView.findViewById(R.id.label_genero_descricao);
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
        View view = layoutInflater.inflate(R.layout.item_checkbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.checkBox).execute(APIClient.ENDPOINT + this.genero.get(position).getCode());
        holder.checkBox.setText(this.genero.get(position).getCode().toString());
        holder.txtGenero.setText(this.genero.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return this.genero.size();
    }

    public Genero getItem(int position) {
        return this.genero.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
