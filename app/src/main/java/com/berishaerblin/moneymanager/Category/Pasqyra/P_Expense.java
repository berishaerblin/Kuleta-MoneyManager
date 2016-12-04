package com.berishaerblin.moneymanager.Category.Pasqyra;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.berishaerblin.moneymanager.R;
import com.berishaerblin.moneymanager.dataBase.model.Category;

import java.util.ArrayList;

/**
 * Created by mKrasniqi on 12/3/16.
 */

public class P_Expense extends Fragment {

    RadioGroup radioGroup;
    EditText editText;
    TextInputLayout textInputLayout;
    ArrayList<Category> arrayListCategories;
    FloatingActionButton floatingActionButton;

    public P_Expense() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_p_expense, container, false);
        arrayListCategories = new ArrayList<Category>();
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.p_expense_add);
        editText = (EditText) v.findViewById(R.id.input_value);
        textInputLayout = (TextInputLayout) v.findViewById(R.id.input_layout_value);

        // Te dhena Testuese >>>>
        Category c1 = new Category(1, "Test 1", "Test1", R.drawable.ic_home);
        Category c2 = new Category(2, "Test 2", "Test2", R.drawable.ic_cilesimet);
        Category c3 = new Category(3, "Test 3", "Test3", R.drawable.ic_ftoni_miqte);
        Category c4 = new Category(4, "Test 4", "Test4", R.drawable.ic_histori);
        Category c5 = new Category(5, "Test 5", "Test5", R.drawable.ic_kategori);
        Category c6 = new Category(6, "Test 6", "Test6", R.drawable.ic_huazimet);
        Category c7 = new Category(7, "Test 7", "Test6", R.drawable.ic_huazimet);
        Category c8 = new Category(8, "Test 8", "Test6", R.drawable.ic_huazimet);
        Category c9 = new Category(9, "Test 9", "Test6", R.drawable.ic_huazimet);

        arrayListCategories.add(c1);
        arrayListCategories.add(c2);
        arrayListCategories.add(c3);
        arrayListCategories.add(c4);
        arrayListCategories.add(c5);
        arrayListCategories.add(c6);
        arrayListCategories.add(c7);
        arrayListCategories.add(c8);
        arrayListCategories.add(c9);
        //


//            arrayListCategories.addAll();
//      Masi te krijohet e gjitha databasa do te plotesohet funksioni me larte duke i marruar kategorit prej DB-s

        radioGroup = (RadioGroup) v.findViewById(R.id.p_expense_radiogroup);
        createRadioButton(radioGroup);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editText.getText().toString().isEmpty()) {
                    //Shtimi i te dhenave ne Databaze
                } else {
                    textInputLayout.setError(getString(R.string.empty));
                }
            }
        });

        return v;
    }


    public void createRadioButton(RadioGroup rgp) {
        for (int i = 0; i <= arrayListCategories.size() - 1; i++) {

            Drawable img = getResources().getDrawable(arrayListCategories.get(i).getCategoryImage());
            String title = arrayListCategories.get(i).getCategoryName();
            int id = arrayListCategories.get(i).getIdCategory();

            RadioButton rbn = new RadioButton(getActivity());
            rbn.setId(id);
            rbn.setText(title);
            rbn.setPadding(75, 75, 75, 75);
            rbn.setButtonDrawable(null);
            rbn.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
            rbn.setBackground(getResources().getDrawable(R.drawable.radiobutton_background));

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            rgp.addView(rbn);
        }
    }

}
