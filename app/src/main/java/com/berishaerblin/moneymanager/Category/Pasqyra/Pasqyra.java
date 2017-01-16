package com.berishaerblin.moneymanager.Category.Pasqyra;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Balance;
import com.berishaerblin.moneymanager.dataBase.model.Expense;
import com.berishaerblin.moneymanager.dataBase.model.Income;
import com.berishaerblin.moneymanager.dataBase.model.IncomeExpense;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pasqyra extends Fragment {

    FloatingActionButton floatingActionButton;
    ListView listView;
    TextView shuma;

    CustomAdapterPasqyra customAdapterPasqyra;
    List<IncomeExpense> mirrors;
    DataBaseSource dataBaseSource;
    String totali;
    private boolean shouldRefreshOnResume = false;
    SimpleDateFormat sp;

    public Pasqyra() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBaseSource = new DataBaseSource(getContext());
        mirrors = new ArrayList<IncomeExpense>();

        sp = new SimpleDateFormat("MM");
        mirrors.addAll(dataBaseSource.getAllIncomeAndExpenseOfMonth(Integer.parseInt(sp.format(new Date()))));

        totali = dataBaseSource.getBalance().getTotalBalance()+"€";
        customAdapterPasqyra = new CustomAdapterPasqyra(getContext(), mirrors);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pasqyra, container, false);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_add);
        listView = (ListView)v.findViewById(R.id.Lstpasqyra);
        shuma = (TextView)v.findViewById(R.id.shumaE);
        shuma.setText(totali);

        listView.setAdapter(customAdapterPasqyra);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addInIncomeOrExpense.class));
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(shouldRefreshOnResume){
            shouldRefreshOnResume = false;
            mirrors.addAll(dataBaseSource.getAllIncomeAndExpenseOfMonth(Integer.parseInt(sp.format(new Date()))));
            totali = dataBaseSource.getBalance().getTotalBalance()+"€";
            shuma.setText(totali);
            ((CustomAdapterPasqyra)listView.getAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
        totali = "0.0";
        mirrors.clear();
    }

}
