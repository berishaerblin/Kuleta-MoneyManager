package com.berishaerblin.moneymanager.Category.Huazimet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Borrowing;
import com.berishaerblin.moneymanager.dataBase.model.Savings;

import java.util.List;

/**
 * Created by berishaerblin on 1/17/17.
 */

public class CustomAapterHuzaimet extends BaseAdapter {

    List<Borrowing> borrowing;
    Context context;
    private static LayoutInflater inflater = null;
    DataBaseSource dataBaseSource;


    public CustomAapterHuzaimet(Context context, List<Borrowing> borrow){
        this.context = context;
        this.borrowing = borrow;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBaseSource = new DataBaseSource(context);
    }

    @Override
    public int getCount() {
        return borrowing.size();
    }

    @Override
    public Object getItem(int i) {
        return borrowing.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.adapterhuazimetitem,null);

        TextView titulli = (TextView)view.findViewById(R.id.borrow_title);

        Borrowing b = borrowing.get(i);
        titulli.setText(b.getBorrowingTitle());

        return view;
    }
}
