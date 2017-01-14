package com.berishaerblin.moneymanager.splashAndintro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berishaerblin.moneymanager.R;

/**
 * Created by mergimkrasniqi on 1/14/17.
 */

public class Page extends Fragment {
    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;

    String text = "";
    int color = 0;
    int img = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splashpaget, container,false);

        imageView = (ImageView)v.findViewById(R.id.photoo);
        textView = (TextView)v.findViewById(R.id.textt);
        linearLayout = (LinearLayout)v.findViewById(R.id.laa);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(!text.equals("")){
            textView.setText(text);
        }
        if(color != 0){
            linearLayout.setBackgroundColor(getResources().getColor(color));
        }
        if (img != 0){
            imageView.setImageResource(img);
        }
    }

    public void changeText(String newText){
        text=newText;
    }
    public void changePhoto(int img){
        if(img == 0)
        {
            this.img = getResources().getColor(R.color.white);
        }
        else{
            this.img=img;
        }
    }
    public void changeBG(int color) {
        if(color == 0)
        {
            this.color = getResources().getColor(R.color.white);
        }
        else{
            this.color=color;
        }
    }
}
