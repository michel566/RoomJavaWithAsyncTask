package com.example.roomtestapp2.ui;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Product;

class ProductHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvId;
    private TextView tvQtd;

    ProductHolder(View view) {
        super(view);
        tvName = view.findViewById(R.id.tv_name);
        tvId = view.findViewById(R.id.tv_id);
        tvQtd = view.findViewById(R.id.tv_qtd);
    }

    void bindItem(Product product) {
        if (product != null) {
            tvId.setText(String.valueOf(product.getId()));
            tvName.setText(product.getName());
            tvQtd.setText(String.valueOf(product.getQuantity()));
        }
    }

}
