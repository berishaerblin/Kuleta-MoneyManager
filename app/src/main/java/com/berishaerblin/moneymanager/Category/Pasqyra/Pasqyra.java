package com.berishaerblin.moneymanager.Category.Pasqyra;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berishaerblin.moneymanager.R;


public class Pasqyra extends Fragment {

    FloatingActionButton floatingActionButton;

    public Pasqyra() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pasqyra, container, false);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_add);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addInIncomeOrExpense.class));
            }
        });

        return v;
    }

}
