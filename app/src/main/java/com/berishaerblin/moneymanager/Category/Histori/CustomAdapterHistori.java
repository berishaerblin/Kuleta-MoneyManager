package com.berishaerblin.moneymanager.Category.Histori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.model.History;

import java.util.List;

public class CustomAdapterHistori extends BaseAdapter {
    Context context;
    List<History> histories;
    private static LayoutInflater inflater = null;


    public CustomAdapterHistori(Context context,List<History>histories){
        this.context = context;
        this.histories = histories;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public Object getItem(int i) {
        return histories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.adapterhistoryrow, null);

        TextView titulli = (TextView)view.findViewById(R.id.muaji);
        TextView hyrje = (TextView)view.findViewById(R.id.hyrjet);
        TextView dalje = (TextView)view.findViewById(R.id.daljet);

        History s = histories.get(i);

        titulli.setText(s.getHistoryTitle());
        hyrje.setText(s.getfIncomeHistory() + " €");
        dalje.setText("- "+s.getfExpenseHistory() + " €");

        return view;
    }
}
