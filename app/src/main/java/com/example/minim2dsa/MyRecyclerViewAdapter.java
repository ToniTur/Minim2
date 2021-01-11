package com.example.minim2dsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    List<Repository> listaRepos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView lenguaje;
        public View layout;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            layout = itemLayoutView;
            nombre = (TextView) itemLayoutView.findViewById(R.id.textView1);
            lenguaje = (TextView) itemLayoutView.findViewById(R.id.textView2);
        }
    }

    public MyRecyclerViewAdapter(List<Repository> listaRepos) {
        this.listaRepos = listaRepos;
    }

    public void add (int position, Repository item){
        listaRepos.add(position, item);
        notifyItemInserted(position);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.nombre.setText(listaRepos.get(position).getName());
        viewHolder.lenguaje.setText(listaRepos.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return listaRepos.size();
    }


}