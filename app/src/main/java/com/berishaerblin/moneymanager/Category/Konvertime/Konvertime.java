package com.berishaerblin.moneymanager.Category.Konvertime;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.berishaerblin.moneymanager.MySingleton;
import com.berishaerblin.moneymanager.R;
;import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;

/**
 * Created by Elvudin on 15-Jan-17.
 */
public class Konvertime extends Fragment{

    double vleraDollarit = 0.0;
    EditText euro,dollar;
//    TextView momentale;

    String URL = "http://api.fixer.io/latest?symbols=USD";

    public Konvertime(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, (String) null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            vleraDollarit = Double.valueOf(rates.getString("USD"));
                            Log.d("onCreate", vleraDollarit+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e("response Errorhome", error + "");
                if (error instanceof NoConnectionError) {
                    Log.d("NoConnectionError>>>>", "NoConnectionError.......");
                } else if (error instanceof AuthFailureError) {
                    Log.d("AuthFailureError>>>>>>", "AuthFailureError.......");
                } else if (error instanceof ServerError) {
                    Log.d("ServerError>>>>>>>>>", "ServerError.......");
                } else if (error instanceof NetworkError) {
                    Log.d("NetworkError>>>>>>>>>", "NetworkError.......");
                } else if (error instanceof ParseError) {
                    Log.d("ParseError>>>>>>>>>", "ParseError.......");
                }else if (error instanceof TimeoutError) {
                    Log.d("TimeoutError>>>>>>>>>", "TimeoutError.......");
                }
            }

        });
        MySingleton.getInstance(getContext()).addToRequestque(jsonObjectRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.convert, container, false);
            euro = (EditText) w.findViewById(R.id.value_euro);
            dollar = (EditText) w.findViewById(R.id.value_usd);
//            momentale = (TextView) w.findViewById(R.id.result);

            euro.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    dollar.getText().clear();
                    double vlerakonvertuar;
                    try{
                        vlerakonvertuar = Double.valueOf(E2D(Double.valueOf(charSequence.toString())));
                    } catch (Exception e){
                        vlerakonvertuar = 0.0;
                    }
                    dollar.setHint(String.valueOf(vlerakonvertuar));
                    Log.d("EURO",charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {}
            });

        dollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                euro.getText().clear();
                double vlerakonvertuar;
                try{
                    vlerakonvertuar = Double.valueOf(D2E(Double.valueOf(charSequence.toString())));
                } catch (Exception e){
                    vlerakonvertuar = 0.0;
                }
                euro.setHint(String.valueOf(vlerakonvertuar));
                Log.d("Dollar",charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return w;
    }

    public String D2E(double dollari){
        DecimalFormat s = new DecimalFormat("#.##");
        return s.format(dollari / Double.valueOf(vleraDollarit));
    }

    public String E2D(double euro){
        DecimalFormat s = new DecimalFormat("#.##");
        return s.format(euro * Double.valueOf(vleraDollarit));
    }
}