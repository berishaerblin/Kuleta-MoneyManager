package com.berishaerblin.moneymanager.Category;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;


public class RrethNesh extends Fragment {

    TextView zhvilluesit;
    TextView emrat;
    TextView rrethnesh;
    TextView rrethneshdescription;
    TextView kontakt;
    LinearLayout contactForm;
    EditText subjekti;
    EditText mesazhi;
    Button dergo;

    public RrethNesh() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rreth_nesh, container, false);

        rrethnesh = (TextView) v.findViewById(R.id.rrethnesh);
        rrethneshdescription = (TextView) v.findViewById(R.id.description);
        zhvilluesit = (TextView) v.findViewById(R.id.zhvilluesit);
        emrat = (TextView) v.findViewById(R.id.emratzh);
        kontakt = (TextView) v.findViewById(R.id.kontakt);
        subjekti = (EditText) v.findViewById(R.id.subject);
        mesazhi= (EditText) v.findViewById(R.id.mesazhi);
        dergo=(Button) v.findViewById(R.id.dergo);
        contactForm = (LinearLayout) v.findViewById(R.id.contactForm);

        rrethnesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rrethneshdescription.getVisibility() == View.GONE){
                    rrethneshdescription.setVisibility(View.VISIBLE);
                } else {
                    rrethneshdescription.setVisibility(View.GONE);
                }
            }
        });

        zhvilluesit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emrat.getVisibility() == View.GONE) {
                    emrat.setVisibility(View.VISIBLE);
                } else {
                    emrat.setVisibility(View.GONE);
                }
            }
        });


        kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contactForm.getVisibility() == View.GONE){
                    contactForm.setVisibility(View.VISIBLE);
                } else {
                    contactForm.setVisibility(View.GONE);
                }
            }
        });

        dergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = subjekti.getText().toString();
                String msg = mesazhi.getText().toString();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "");
                intent.putExtra(Intent.EXTRA_SUBJECT,sub);
                intent.putExtra(Intent.EXTRA_TEXT,msg);
                try {
                    startActivity(Intent.createChooser(intent,"Dergo mesazh...!"));
                    subjekti.setText("");
                    mesazhi.setText("");
                    contactForm.setVisibility(View.GONE);
                } catch (ActivityNotFoundException e )
                {
                    e.printStackTrace();
                }
            }
        });



        return v;
    }

}
