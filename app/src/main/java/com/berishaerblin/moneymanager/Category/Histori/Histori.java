package com.berishaerblin.moneymanager.Category.Histori;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.History;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Histori extends Fragment {


    public Histori() {}

    ListView listView;
    CustomAdapterHistori customAdapterHistori;
    List<History> histories;
    DataBaseSource dataBaseSource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBaseSource = new DataBaseSource(getContext());
        histories = new ArrayList<History>();
        try {
            histories.add(dataBaseSource.getHistoryByMonth(1,"/01/"));
            histories.add(dataBaseSource.getHistoryByMonth(2,"/02/"));
            histories.add(dataBaseSource.getHistoryByMonth(3,"/03/"));
            histories.add(dataBaseSource.getHistoryByMonth(4,"/04/"));
            histories.add(dataBaseSource.getHistoryByMonth(5,"/05/"));
            histories.add(dataBaseSource.getHistoryByMonth(6,"/06/"));
            histories.add(dataBaseSource.getHistoryByMonth(7,"/07/"));
            histories.add(dataBaseSource.getHistoryByMonth(8,"/08/"));
            histories.add(dataBaseSource.getHistoryByMonth(9,"/09/"));
            histories.add(dataBaseSource.getHistoryByMonth(10,"/10/"));
            histories.add(dataBaseSource.getHistoryByMonth(11,"/11/"));
            histories.add(dataBaseSource.getHistoryByMonth(12,"/12/"));
        } catch (Exception e){
            e.printStackTrace();
        }
        customAdapterHistori = new CustomAdapterHistori(getContext(),histories);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_histori, container, false);
        listView = (ListView)w.findViewById(R.id.historylist);
        listView.setAdapter(customAdapterHistori);
        return w;
    }

}
