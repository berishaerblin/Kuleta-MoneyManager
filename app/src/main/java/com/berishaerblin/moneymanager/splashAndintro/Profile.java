package com.berishaerblin.moneymanager.splashAndintro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.berishaerblin.moneymanager.R;

public class Profile extends Fragment {

    EditText name,surname;

    public Profile() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = (EditText) v.findViewById(R.id.name);
        surname = (EditText) v.findViewById(R.id.surname);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((IntroScreenSlider)getActivity()).onTextChangedName(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ((IntroScreenSlider)getActivity()).onTextChangedSurname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        return v;
    }



}
