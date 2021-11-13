package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
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
        icons.add(new CurrencyIcon("Bitcoin","BTC",R.drawable.btc));
        icons.add(new CurrencyIcon("Etherium","ETH",R.drawable.eth));
        icons.add(new CurrencyIcon("Binance coin","BNB",R.drawable.bnb));
        icons.add(new CurrencyIcon("Cardano","ADA",R.drawable.ada));
        icons.add(new CurrencyIcon("XRP","XRP",R.drawable.xrp));
        icons.add(new CurrencyIcon("Dogecoin","DOGE",R.drawable.doge));
        icons.add(new CurrencyIcon("USD coin","USDC",R.drawable.usdc));
        icons.add(new CurrencyIcon("Litecoin","LTC",R.drawable.ltc));
        icons.add(new CurrencyIcon("ChainLink","LINK",R.drawable.link));
        icons.add(new CurrencyIcon("Algorand","ALGO",R.drawable.algo));
        icons.add(new CurrencyIcon("Bitcoin cash","BCH",R.drawable.bch));
        icons.add(new CurrencyIcon("Stellar lumen","XLM",R.drawable.xlm));
        icons.add(new CurrencyIcon("Cosmos","ATOM",R.drawable.atom));
        icons.add(new CurrencyIcon("FileCoin","FIL",R.drawable.fil));
        icons.add(new CurrencyIcon("Dai","DAI",R.drawable.dai));
        icons.add(new CurrencyIcon("Zcash","ZEC",R.drawable.zec));
        icons.add(new CurrencyIcon("Etherium classic","ETC",R.drawable.etc));
        //icons.add(new CurrencyIcon("UMA","UMA",R.drawable.uma));
        icons.add(new CurrencyIcon("Ox","ZRX",R.drawable.zrx));
        icons.add(new CurrencyIcon("OMG network","OMG",R.drawable.omg));
        icons.add(new CurrencyIcon("Basic Attention Token","BAT",R.drawable.bat));
        icons.add(new CurrencyIcon("Decentralnd","MANA",R.drawable.mana));
        icons.add(new CurrencyIcon("Kyber Network","KNC",R.drawable.knc));
        //icons.add(new CurrencyIcon("Civic","CVC",R.drawable.cvc));
        icons.add(new CurrencyIcon("Orchid","OXT",R.drawable.oxt));
        icons.add(new CurrencyIcon("Numeraire","NMR",R.drawable.nmr));
    }

    String url , url1 = "https://min-api.cryptocompare.com/data/generateAvg?fsym=" , url2 ="&tsym=USD&e=Kraken&api_key=e69f17b4f7de2e7e0b7dd6f4f2715d7a53574dca42c4191de7412c9a4b56474c";
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

        @SuppressLint("ResourceAsColor")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currncy_grid,parent,false);
            }

            TextView name,price,grow;
            ImageView image;
            ImageButton imageButton;
            imageButton =view.findViewById(R.id.imageButton);
            name = view.findViewById(R.id.currency_name);
            price = view.findViewById(R.id.currency_price);
            grow = view.findViewById(R.id.grow);
            image = view.findViewById(R.id.currency_icon);
            name.setText(icons.get(position).getName());
            price.setText(icons.get(position).getPrice());
            image.setImageResource(icons.get(position).getImage());
            Double color = icons.get(position).getChange();
            String gr = String.format("%.2f",color.floatValue());
            grow.setText("("+gr+")");
            if (color < 0) {
                imageButton.setImageResource(R.drawable.dec);
            }
            return view;
        }
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
                String raw = responsJson.getString("RAW");
                responsJson = new JSONObject(raw);
                String price = responsJson.getString("PRICE");
                String change = responsJson.getString("CHANGEPCT24HOUR");
                icons.get(position).setPrice(price);
                icons.get(position).setChange(Double.parseDouble(change));
                System.out.println(change);
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


}