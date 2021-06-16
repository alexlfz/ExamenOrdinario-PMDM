package com.example.examenordinario;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<ArrayList<String>> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    Adaptador(Context context){
        this.mInflater = LayoutInflater.from(context);
        mData = new ArrayList<ArrayList<String>>();
    }


    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_adapter, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        ArrayList<String> linea = mData.get(position);
        holder.tvNumeroBoleto.setText(linea.get(0));
        holder.tvSerieBoleto.setText(linea.get(1));
        holder.tvPremioBoleto.setText(linea.get(2));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void addData(ArrayList<ArrayList<String>> info) {
        mData.addAll(info);
        notifyDataSetChanged();
    }

    ArrayList<String> getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNumeroBoleto;
        TextView tvSerieBoleto;
        TextView tvPremioBoleto;

        ViewHolder(View itemView) {
            super(itemView);
            tvNumeroBoleto = itemView.findViewById(R.id.idNumero);
            tvSerieBoleto = itemView.findViewById(R.id.idSerie);
            tvPremioBoleto = itemView.findViewById(R.id.idPremio);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}