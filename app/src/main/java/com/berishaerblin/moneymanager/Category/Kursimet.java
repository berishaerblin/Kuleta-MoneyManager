package com.berishaerblin.moneymanager.Category;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.berishaerblin.moneymanager.R;

/**
 * Created by mergimkrasniqi on 1/14/17.
 */

public class Kursimet extends Fragment {

    FloatingActionButton k_button;
    ListView k_listview;

    public Kursimet() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_kursimet, container, false);
            k_button = (FloatingActionButton)w.findViewById(R.id.k_add);
            k_listview = (ListView)w.findViewById(R.id.kursimetlsw);

        return w;
    }

}
