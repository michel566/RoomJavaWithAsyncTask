package com.example.roomtestapp2.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Product;

import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ProductListAdapter adapter;

    private TextView productId;
    private EditText productName;
    private EditText productQuantity;

    static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        productId = getView().findViewById(R.id.tv_productId);
        productName = getView().findViewById(R.id.ed_productName);
        productQuantity = getView().findViewById(R.id.ed_qtProduct);

        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void listenerSetup() {
        Button addButton = getView().findViewById(R.id.bt_add);
        Button findButton = getView().findViewById(R.id.bt_find);
        Button deleteButton = getView().findViewById(R.id.bt_delete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = productName.getText().toString();
                String quantity = productQuantity.getText().toString();

                if (!name.equals("") && !quantity.equals("")) {
                    Product product = new Product(name,
                            Integer.parseInt(quantity));
                    mViewModel.insertProduct(product);
                    clearFields();
                } else {
                    productId.setText("Incomplete information");
                }
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.findProduct(productName.getText().toString());
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteProduct(productName.getText().toString());
                clearFields();
            }
        });
    }

    private void clearFields() {
        adapter.notifyDataSetChanged();
        productId.setText("");
        productName.setText("");
        productQuantity.setText("");
    }

    private void observerSetup() {
        mViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                adapter.setProductList(products);
                adapter.notifyDataSetChanged();
            }
        });

        mViewModel.getSearchResults().observe(this,
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> products) {

                        if (products.size() > 0) {
                            productId.setText(String.format(Locale.US, "%d",
                                    products.get(0).getId()));
                            productName.setText(products.get(0).getName());
                            productQuantity.setText(String.format(Locale.US, "%d",
                                    products.get(0).getQuantity()));
                        } else {
                            productId.setText("No Match");
                        }
                    }
                });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ProductListAdapter(R.layout.product_list_item);
        recyclerView = getView().findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
