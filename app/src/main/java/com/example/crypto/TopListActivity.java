package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TopListActivity extends AppCompatActivity {

    GridView grid;
    TextView coin;
    ArrayList<CurrencyIcon> icons = new ArrayList<>();
    String symbol = "BTC";
    String url , url1 = "https://min-api.cryptocompare.com/data/top/volumes?tsym=" , url2 ="&api_key=e69f17b4f7de2e7e0b7dd6f4f2715d7a53574dca42c4191de7412c9a4b56474c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list);
        grid = findViewById(R.id.top_grid);
        coin = findViewById(R.id.symbol);
        refresh();
        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }



    private void showMenu (View v){
        PopupMenu menu = new PopupMenu(TopListActivity.this,v);
        menu.getMenuInflater().inflate(R.menu.menu , menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Bitcoin){
                    symbol="BTC";
                    coin.setText("Bitcoin");
                    refresh();
                }
                if (item.getItemId() == R.id.Binancecoin){
                    symbol="BNB";
                    coin.setText("Binancecoin");
                    refresh();
                }
                if (item.getItemId() == R.id.USDcoin){
                    symbol="USDC";
                    coin.setText("USDcoin");
                    refresh();
                }
                if (item.getItemId() == R.id.Litecoin){
                    symbol="LTC";
                    coin.setText("Litecoin");
                    refresh();
                }
                if (item.getItemId() == R.id.ChainLink){
                    symbol="LINK";
                    coin.setText("ChainLink");
                    refresh();
                }
                if (item.getItemId() == R.id.Ox){
                    symbol="ZRX";
                    coin.setText("OX");
                    refresh();
                }
                return true;
            }
        });
        menu.show();
    }

    private void refresh (){
        url = url1+symbol+url2;
        getData getdata = new getData(url);
        getdata.execute();
        try{

            icons = (ArrayList<CurrencyIcon>) getdata.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TopListAdapter topListAdapter = new TopListAdapter(icons);
        grid.setAdapter(topListAdapter);
    }
}


class TopListAdapter extends BaseAdapter {
    ArrayList<CurrencyIcon> icons ;

    public TopListAdapter(ArrayList<CurrencyIcon> icons) {
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toplist_grid,parent,false);
        }

        TextView name,price,grow;
        ImageView image;
        image =view.findViewById(R.id.currency_icon);
        name = view.findViewById(R.id.currency_name);
        price = view.findViewById(R.id.currency_price);
        name.setText(icons.get(position).getName());
        price.setText(icons.get(position).getPrice());
        image.setImageResource(icons.get(position).getImage());
        return view;
    }
}



class getData extends AsyncTask {
    String url ;
    int position;
    public getData(String url){
        this.position=position;
        this.url = url;
    }

    @Override
    protected ArrayList<CurrencyIcon> doInBackground(Object[] objects) {

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
        ArrayList<CurrencyIcon> ic = new ArrayList<>();
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
            JSONArray raw = responsJson.getJSONArray("Data");
            int i;
           // icons.add(new CurrencyIcon("Numeraire","NMR",new uncocoderDownloadImage(imageView).execute("http://uncocoder.com/imageview/image.png")));
            for (i=0; i<21 ;i++){
                responsJson = raw.getJSONObject(i);
                String image = null;
                String name = responsJson.getString("NAME");
                String sy = responsJson.getString("SYMBOL");
                Double vol = responsJson.getDouble("VOLUME24HOURTO");
                try {
                    String imageUrl = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=";
                    String imageKeyUrl = "&tsyms=USD,EUR&api_key=e69f17b4f7de2e7e0b7dd6f4f2715d7a53574dca42c4191de7412c9a4b56474c";
                    String finalUrl = imageUrl+sy+imageKeyUrl;
                    URL url = new URL(finalUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String li;
                    while ((li = bufferedReader.readLine()) !=null){
                        stringBuilder.append(li +"\n");
                    }
                    String respons = stringBuilder.toString();
                    JSONObject object = new JSONObject(respons);
                    String result = object.getString("RAW");
                    object = new JSONObject(result);
                    result = object.getString(sy);
                    object = new JSONObject(result);
                    result = object.getString("USD");
                    object = new JSONObject(result);
                    image = object.getString("IMAGEURL");

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (CurrencyIcon ico:icons) {
                    if (ico.getSymbol().equals(sy)){
                        ico.setPrice(vol.toString());
                        ic.add(ico);
                    }
                }
                System.out.println(sy);
                //responsJson = new JSONObject(raw);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ic;
    }
}

class LoadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public LoadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}