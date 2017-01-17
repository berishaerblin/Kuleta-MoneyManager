package com.berishaerblin.moneymanager.Category.Kursimet;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.berishaerblin.moneymanager.Category.Pasqyra.CustomAdapterPasqyra;
import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mergimkrasniqi on 1/14/17.
 */

public class Kursimet extends Fragment {

    FloatingActionButton k_button;
    ListView k_listview;
    CustomAdapterKursimet customAdapterKursimet;
    List<Savings> kursimet;
    DataBaseSource dataBaseSource;
    private boolean shouldRefreshOnResume = false;

    public Kursimet() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseSource = new DataBaseSource(getContext());
        kursimet = new ArrayList<Savings>();
        kursimet.addAll(dataBaseSource.getAllSavings());
        customAdapterKursimet = new CustomAdapterKursimet(getContext(), kursimet);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_kursimet, container, false);
            k_button = (FloatingActionButton)w.findViewById(R.id.k_add);
            k_listview = (ListView)w.findViewById(R.id.kursimetlsw);
            k_listview.setAdapter(customAdapterKursimet);
            addListDetails();

        k_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(), addItemintokursimet.class);
                    intent.putExtra("KursimetItemID", customAdapterKursimet.getItem(i).toString());
                    startActivity(intent);
                }
            });


            k_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),addKursime.class));
                }
            });

        return w;
    }

    public void addListDetails(){
        kursimet = new ArrayList<Savings>();
        kursimet.addAll(dataBaseSource.getAllSavings());
        customAdapterKursimet = new CustomAdapterKursimet(getContext(),kursimet);
        customAdapterKursimet.notifyDataSetChanged();
        k_listview.setAdapter(customAdapterKursimet);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(shouldRefreshOnResume){
            shouldRefreshOnResume = false;
            addListDetails();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
        kursimet.clear();
    }
}
