package com.entawaspada.cuaca;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CuacaViewHolder extends RecyclerView.ViewHolder {

    public ImageView cuacaImageView;
    public TextView deskripsiTextView, namaTextView, suhuTextView, tglWaktuTextView;

    public CuacaViewHolder(View itemView) {
        super(itemView);

        cuacaImageView = itemView.findViewById(R.id.cuacaImageView);
        deskripsiTextView = itemView.findViewById(R.id.deskripsiTextView);
        namaTextView = itemView.findViewById(R.id.namaTextView);
        suhuTextView = itemView.findViewById(R.id.suhuTextView);
        tglWaktuTextView = itemView.findViewById(R.id.tglWaktuTextView);
    }

}