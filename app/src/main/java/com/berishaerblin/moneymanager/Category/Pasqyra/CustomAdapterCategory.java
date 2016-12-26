package com.berishaerblin.moneymanager.Category.Pasqyra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.model.Category;

import java.util.List;

/**
 * Created by mergimkrasniqi on 12/26/16.
 */

public class CustomAdapterCategory extends BaseAdapter {

    private Context context;
    private List<Category> categories;
    private static LayoutInflater inflater = null;

    public CustomAdapterCategory(Context context,List<Category> categories){
        this.context = context;
        this.categories = categories;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.adaptercategoryrow, null);

        ImageView icon = (ImageView) view.findViewById(R.id.list_image);
        TextView title = (TextView) view.findViewById(R.id.list_title);

        int resId = context.getResources().getIdentifier(categories.get(i).getCategoryImage().split("\\.")[2], "drawable", context.getPackageName());
        icon.setImageResource(resId);
        title.setText(categories.get(i).getCategoryName());
        return view;
    }
}
