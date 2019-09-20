package com.example.roomtestapp2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private int productItemLayout;
    private List<Product> productList;
    private int itemPos;

    ProductListAdapter(int layoutId) {
        productItemLayout = layoutId;
    }

    void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(productItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ViewHolder holder, int listPosition) {
        setItemPos(listPosition);
        TextView tv_name = holder.tv_name;
        tv_name.setText(productList.get(listPosition).getName());
    }

    public int getItemPos() {
        return itemPos;
    }

    private void setItemPos(int itemPos) {
        this.itemPos = itemPos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_id, tv_qtd;

        ViewHolder(View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_qtd = itemView.findViewById(R.id.tv_qtd);
        }
    }

}