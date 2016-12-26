package com.berishaerblin.moneymanager.Category.Pasqyra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.DataBaseSource;
import com.berishaerblin.moneymanager.dataBase.model.Category;
import com.berishaerblin.moneymanager.dataBase.model.Expense;
import com.berishaerblin.moneymanager.dataBase.model.Income;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mergimkrasniqi on 12/26/16.
 */

public class CustomAdapterPasqyra extends BaseAdapter {

    private Context context;
    private List<Object> mirrors = new ArrayList<Object>();
    private static LayoutInflater inflater = null;
    DataBaseSource dataBaseSource;

    public CustomAdapterPasqyra(Context context, List<Object> mirrors){
        this.context = context;
        this.mirrors = mirrors;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBaseSource = new DataBaseSource(context);
    }

    @Override
    public int getCount() {
        return mirrors.size();
    }

    @Override
    public Object getItem(int position) {
        return mirrors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.adapterpasqyrarow, null);

            ImageView icon = (ImageView) view.findViewById(R.id.list_image);
            TextView title = (TextView) view.findViewById(R.id.list_title);
            TextView sum = (TextView) view.findViewById(R.id.list_sum);
            TextView date = (TextView) view.findViewById(R.id.list_date);

        Object i = mirrors.get(position);

        if(i instanceof Income){
            Income income = (Income) mirrors.get(position);
            sum.setTextColor(context.getResources().getColor(R.color.bg_screen2));
            sum.setText(String.valueOf(income.getIncomeValue()) + "€");

            Category c = dataBaseSource.getCategory(income.getfICategoryType());
            int resId = context.getResources().getIdentifier(c.getCategoryImage().split("\\.")[2], "drawable", context.getPackageName());

            date.setText(income.getIncomeDate());
            icon.setImageResource(resId);
            title.setText(c.getCategoryName());

        } else {
            Expense expense = (Expense) mirrors.get(position);
            sum.setTextColor(context.getResources().getColor(R.color.bg_screen1));
            sum.setText("-" + String.valueOf(expense.getExpenseValue()) + "€");

            Category c = dataBaseSource.getCategory(expense.getfECategoryType());
            int resId = context.getResources().getIdentifier(c.getCategoryImage().split("\\.")[2], "drawable", context.getPackageName());

            date.setText(expense.getExpenseDate());
            icon.setImageResource(resId);
            title.setText(c.getCategoryName());
        }
        return view;
    }
}
