package com.berishaerblin.moneymanager.Category.Pasqyra;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Balance;
import com.berishaerblin.moneymanager.dataBase.model.Expense;
import com.berishaerblin.moneymanager.dataBase.model.Income;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mergimkrasniqi on 12/26/16.
 * Edited by berishaerblin & mergimkrasniqi on 12/27/16.
 */

public class Pasqyra extends Fragment {

    FloatingActionButton floatingActionButton;
    ListView listView;
    CustomAdapterPasqyra customAdapterPasqyra;
    List<Object> mirrors;
    DataBaseSource dataBaseSource;

    public Pasqyra() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pasqyra, container, false);

        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_add);

        listView = (ListView)v.findViewById(R.id.Lstpasqyra);
        dataBaseSource = new DataBaseSource(getContext());
        mirrors = new ArrayList<Object>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        mirrors.addAll(dataBaseSource.getAllIncomeAndExpenseOfMonth("01/01/2017"));

        customAdapterPasqyra = new CustomAdapterPasqyra(getContext(),mirrors);
        listView.setAdapter(customAdapterPasqyra);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addInIncomeOrExpense.class));
            }
        });

        return v;
    }

}
