package com.berishaerblin.moneymanager.Category.Pasqyra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berishaerblin.moneymanager.R;

/**
 * Created by mKrasniqi on 12/3/16.
 */

public class P_Expense extends Fragment {


    public P_Expense() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_p_expense, container, false);
    }

}
