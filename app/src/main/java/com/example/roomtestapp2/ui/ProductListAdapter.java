package com.example.roomtestapp2.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductHolder> implements Filterable {

    private final LayoutInflater layoutInflater;
    private List<Product> productList;
    private List<Product> filteredProductList;
    private int itemPos;
    private Context context;

    ProductListAdapter(Context context, LayoutInflater inflater) {
        this.layoutInflater = inflater;
        this.context = context;
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
        Log.d("ProductListAdapter", "NAME: " + productList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredProductList = productList;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product row : productList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filteredProductList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredProductList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProductList = (ArrayList<Product>) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();

                for (Product product : filteredProductList){
                    Log.d("publish", "product name filter: "+ product.getName());
                }
            }
        };
    }
}