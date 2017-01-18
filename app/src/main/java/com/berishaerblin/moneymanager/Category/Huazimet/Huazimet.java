package com.berishaerblin.moneymanager.Category.Huazimet;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.berishaerblin.moneymanager.Category.Kursimet.CustomAdapterKursimet;
import com.berishaerblin.moneymanager.Category.Kursimet.addItemintokursimet;
import com.berishaerblin.moneymanager.Category.Kursimet.addKursime;
import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Borrowing;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Huazimet extends Fragment {


    FloatingActionButton borrowButton;
    ListView borrowingListView;
    CustomAapterHuzaimet customAapterHuzaimet;
    List<Borrowing> borrowings;
    DataBaseSource dataBaseSource;
    private boolean shouldRefreshOnResume = false;

    public Huazimet() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseSource = new DataBaseSource(getContext());
        borrowings = new ArrayList<Borrowing>();
        borrowings.addAll(dataBaseSource.getAllBorrowings());
        customAapterHuzaimet = new CustomAapterHuzaimet(getContext(), borrowings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_huazimet, container, false);
        borrowButton = (FloatingActionButton)w.findViewById(R.id.huazimetAdd);
        borrowingListView = (ListView)w.findViewById(R.id.huazimetList);
        borrowingListView.setAdapter(customAapterHuzaimet);
        addListDetails();

        borrowingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), OpenBorrow.class);
                intent.putExtra("HuazimetItemID", customAapterHuzaimet.getItem(i).toString());
                startActivity(intent);
            }
        });


        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),addHuazimet.class));
            }
        });

        return w;
    }

    public void addListDetails(){
        borrowings = new ArrayList<Borrowing>();
        borrowings.addAll(dataBaseSource.getAllBorrowings());
        customAapterHuzaimet = new CustomAapterHuzaimet(getContext(),borrowings);
        customAapterHuzaimet.notifyDataSetChanged();
        borrowingListView.setAdapter(customAapterHuzaimet);
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
        borrowings.clear();
    }

}
