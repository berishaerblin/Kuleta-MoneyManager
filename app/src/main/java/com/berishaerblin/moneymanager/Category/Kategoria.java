package com.berishaerblin.moneymanager.Category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.berishaerblin.moneymanager.Category.Pasqyra.CustomAdapterCategory;
import com.berishaerblin.moneymanager.Category.Pasqyra.CustomAdapterPasqyra;
import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Kategoria extends Fragment {

    DataBaseSource db;
    CustomAdapterCategory customAdapterCategory;
    List<Category> lista;
    ListView listView;

    public Kategoria() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DataBaseSource(getContext());
        lista = new ArrayList<Category>();
        lista.addAll(db.getAllCategories());
        customAdapterCategory = new CustomAdapterCategory(getContext(),lista);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kategoria, container, false);
        listView = (ListView)v.findViewById(R.id.lstKategoria);
        listView.setAdapter(customAdapterCategory);

        return v;


    }

}
