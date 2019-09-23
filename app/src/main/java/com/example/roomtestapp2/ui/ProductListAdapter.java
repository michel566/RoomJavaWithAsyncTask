package com.example.roomtestapp2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductHolder> {

    private final LayoutInflater layoutInflater;
    private List<Product> productList;
    private int itemPos;

    ProductListAdapter(LayoutInflater inflater) {
        this.layoutInflater = inflater;
    }

    void setProductList(List<Product> productList) {
        this.productList = productList;
        //verificar
        notifyDataSetChanged();
    }

    private void setItemPos(int itemPos) {
        this.itemPos = itemPos;
    }

    public int getItemPos() {
        return itemPos;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductHolder(layoutInflater.inflate(R.layout.product_list_item, null));
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        setItemPos(position);
        holder.bindItem(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }
}