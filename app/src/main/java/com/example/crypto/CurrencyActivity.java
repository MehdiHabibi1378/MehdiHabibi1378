package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CurrencyActivity extends AppCompatActivity {

    GridView grid;
    ArrayList<CurrencyIcon> icons = new ArrayList<>();
    {
        icons.add(new CurrencyIcon("Bitcoin","BTC",R.drawable.currency));
    }
    String symbol;
    String url , url1 = "https://min-api.cryptocompare.com/data/price?fsym=" , url2 ="&tsyms=USD,JPY,EUR&api_key=e69f17b4f7de2e7e0b7dd6f4f2715d7a53574dca42c4191de7412c9a4b56474c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        grid = findViewById(R.id.curre_grid);
        for (int i=0;i<icons.size();i++){
            url = url1+icons.get(i).getSymbol()+url2;
            getData getdata = new getData(url,i);
            getdata.execute();
            try{

               getdata.get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        CurrenccyAdapter adapter = new CurrenccyAdapter(icons);
        grid.setAdapter(adapter);

    }
    class getData extends AsyncTask {
        String url ;
        int position;
        public getData(String url,int position){
            this.position=position;
            this.url = url;
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            String res="none";
            try {

                URL u = new URL(url);

                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line ;

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                res = sb.toString();
                JSONObject responsJson = new JSONObject(res);
                String price = responsJson.getString("USD");
                icons.get(position).setPrice(price);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    class CurrenccyAdapter extends BaseAdapter {
        ArrayList<CurrencyIcon> icons ;

        public CurrenccyAdapter(ArrayList<CurrencyIcon> icons) {
            this.icons = icons;
        }

        @Override
        public int getCount() {
            return icons.size();
        }

        @Override
        public CurrencyIcon getItem(int position) {
            return icons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currncy_grid,parent,false);
            }
            TextView name,price;
            ImageView image;
            name = view.findViewById(R.id.currency_name);
            price = view.findViewById(R.id.currency_price);
            image = view.findViewById(R.id.currency_icon);
            name.setText(icons.get(position).getName());
            price.setText(icons.get(position).getPrice());
            image.setImageResource(icons.get(position).getImage());
            return view;
        }
    }


}