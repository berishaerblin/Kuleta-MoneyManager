package com.berishaerblin.moneymanager.Category.Kursimet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Savings;
import com.berishaerblin.moneymanager.dataBase.model.SavingsItem;

import java.util.List;

/**
 * Created by mergimkrasniqi on 1/17/17.
 */

public class CustomAdapterKursimet extends BaseAdapter {

    List<Savings> kursimets;
    Context context;
    private static LayoutInflater inflater = null;
    DataBaseSource dataBaseSource;


    public CustomAdapterKursimet(Context context, List<Savings> kursimet){
        this.context = context;
        this.kursimets = kursimet;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBaseSource = new DataBaseSource(context);
    }

    @Override
    public int getCount() {
        return kursimets.size();
    }

    @Override
    public Object getItem(int i) {
        return kursimets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.adapterkursimetrow,null);

        TextView titulli = (TextView)view.findViewById(R.id.kursime_title);
        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.kursime_progres);

        Savings s = kursimets.get(i);
        int vlera;
        try {
            vlera = (int) dataBaseSource.getSumOfSavingsById(s.getIdSavings());
        } catch (Exception e){
            vlera = 0;
        }
        titulli.setText(s.getSavingsTitle());
        progressBar.setMax(new Double(s.getSavingsValue()).intValue());
        progressBar.setProgress(vlera);

        return view;
    }
}
