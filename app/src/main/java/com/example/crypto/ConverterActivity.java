package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ConverterActivity extends AppCompatActivity {

    String symbol_dec,symbol_source,num,symbol,name;
    Button source,dec,compute;
    TextInputEditText number;
    TextView text;
    String  url1 = "https://min-api.cryptocompare.com/data/generateAvg?fsym=" , url2 ="&tsym=USD&e=Kraken&api_key=e69f17b4f7de2e7e0b7dd6f4f2715d7a53574dca42c4191de7412c9a4b56474c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        source = findViewById(R.id.coinsource);
        dec = findViewById((R.id.coindec));
        compute = findViewById(R.id.compute);
        number = findViewById(R.id.number);
        text = findViewById(R.id.textView);

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v,0);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v,1);
            }
        });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = String.valueOf(number.getText());
                String price1 = "0",price2="0";
                Double totalMony,x;
                Data data1 = new Data(url1+symbol_source+url2);
                Data data2 = new Data(url1+symbol_dec+url2);
                data1.execute();
                data2.execute();
                try {
                     price1 = (String) data1.get();
                     price2 = (String) data2.get();
                     System.out.println(price1);
                    System.out.println(price2);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalMony = Integer.parseInt(num)* Double.parseDouble(price1);
                x = totalMony/Double.parseDouble(price2);

                text.setText(String.format("%.3f", x));

            }
        });
    }

    private void showMenu (View v,int index){
        PopupMenu menu = new PopupMenu(ConverterActivity.this,v);
        menu.getMenuInflater().inflate(R.menu.menu , menu.getMenu());
        String [] re  = new String[2];
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Bitcoin){
                    if (index==0){
                        symbol_source="BTC";
                        source.setText("Bitcoin");
                    }
                    if (index==1){
                       symbol_dec="BTC";
                       dec.setText("Bitcoin");
                    }
                }
                if (item.getItemId() == R.id.Binancecoin){
                    if (index==0){
                        symbol_source="BNB";
                        source.setText("Binancecoin");
                    }
                    if (index==1){
                        symbol_dec="BNB";
                        dec.setText("Binancecoin");
                    }
                }
                if (item.getItemId() == R.id.USDcoin){
                    if (index==0){
                        symbol_source="USDC";
                        source.setText("USDcoin");
                    }
                    if (index==1){
                        symbol_dec="USDC";
                        dec.setText("USDcoin");
                    }

                }
                if (item.getItemId() == R.id.Litecoin){
                    if (index==0){
                        symbol_source="LTC";
                        source.setText("Litecoin");
                    }
                    if (index==1){
                        symbol_dec="LTC";
                        dec.setText("Litecoin");
                    }
                }
                if (item.getItemId() == R.id.ChainLink){
                    if (index==0){
                        symbol_source="LINK";
                        source.setText("ChainLink");
                    }
                    if (index==1){
                        symbol_dec="LINK";
                        dec.setText("ChainLink");
                    }

                }
                if (item.getItemId() == R.id.Ox){
                    if (index==0){
                        symbol_source="ZRX";
                        source.setText("OX");
                    }
                    if (index==1){
                        symbol_dec="ZRX";
                        dec.setText("OX");
                    }
                }
                if (item.getItemId() == R.id.Etherium){
                    if (index==0){
                        symbol_source="ETH";
                        source.setText("Etherium");
                    }
                    if (index==1){
                        symbol_dec="ETH";
                        dec.setText("Etherium");
                    }
                }
                if (item.getItemId() == R.id.Cardano){
                    if (index==0){
                        symbol_source="ADA";
                        source.setText("Cardano");
                    }
                    if (index==1){
                        symbol_dec="ADA";
                        dec.setText("Cardano");
                    }
                }
                if (item.getItemId() == R.id.XRP){
                    if (index==0){
                        symbol_source="XRP";
                        source.setText("XRP");
                    }
                    if (index==1){
                        symbol_dec="XRP";
                        dec.setText("XRP");
                    }
                }
                if (item.getItemId() == R.id.Dogecoin){
                    if (index==0){
                        symbol_source="DOGE";
                        source.setText("Dogecoin");
                    }
                    if (index==1){
                        symbol_dec="DOGE";
                        dec.setText("Dogecoin");
                    }
                }
                if (item.getItemId() == R.id.Bitcoincash){
                    if (index==0){
                        symbol_source="BCH";
                        source.setText("Bitcoin cash");
                    }
                    if (index==1){
                        symbol_dec="BCH";
                        dec.setText("Bitcoin cash");
                    }
                }
                if (item.getItemId() == R.id.Algorand){
                    if (index==0){
                        symbol_source="ALGO";
                        source.setText("Algorand");
                    }
                    if (index==1){
                        symbol_dec="ALGO";
                        dec.setText("Algorand");
                    }
                }
                if (item.getItemId() == R.id.Stellarlumen){
                    if (index==0){
                        symbol_source="XLM";
                        source.setText("Stellar lumen");
                    }
                    if (index==1){
                        symbol_dec="XLM";
                        dec.setText("Stellar lumen");
                    }
                }
                if (item.getItemId() == R.id.Cosmos){
                    if (index==0){
                        symbol_source="ATOM";
                        source.setText("Cosmos");
                    }
                    if (index==1){
                        symbol_dec="ATOM";
                        dec.setText("Cosmos");
                    }
                }
                if (item.getItemId() == R.id.FileCoin){
                    if (index==0){
                        symbol_source="FIL";
                        source.setText("FileCoin");
                    }
                    if (index==1){
                        symbol_dec="FIL";
                        dec.setText("FileCoin");
                    }
                }
                if (item.getItemId() == R.id.Dai){
                    if (index==0){
                        symbol_source="DAI";
                        source.setText("Dai");
                    }
                    if (index==1){
                        symbol_dec="DAI";
                        dec.setText("Dai");
                    }
                }
                if (item.getItemId() == R.id.Zcash){
                    if (index==0){
                        symbol_source="ZEC";
                        source.setText("Zcash");
                    }
                    if (index==1){
                        symbol_dec="ZEC";
                        dec.setText("Zcash");
                    }
                }
                if (item.getItemId() == R.id.Etheriumclassic){
                    if (index==0){
                        symbol_source="ETC";
                        source.setText("Etherium classic");
                    }
                    if (index==1){
                        symbol_dec="ETC";
                        dec.setText("Etherium classic");
                    }
                }
                if (item.getItemId() == R.id.Decentralnd){
                    if (index==0){
                        symbol_source="MANA";
                        source.setText("Decentralnd");
                    }
                    if (index==1){
                        symbol_dec="MANA";
                        dec.setText("Decentralnd");
                    }
                }
                if (item.getItemId() == R.id.KyberNetwork){
                    if (index==0){
                        symbol_source="KNC";
                        source.setText("Kyber Network");
                    }
                    if (index==1){
                        symbol_dec="KNC";
                        dec.setText("Kyber Network");
                    }
                }
                if (item.getItemId() == R.id.OMGnetwork){
                    if (index==0){
                        symbol_source="OMG";
                        source.setText("OMG network");
                    }
                    if (index==1){
                        symbol_dec="OMG";
                        dec.setText("OMG network");
                    }
                }
                return true;
            }
        });
        menu.show();
    }
}

class Data extends AsyncTask {
    String url ;
    public Data(String url){
        this.url = url;
    }

    @Override
    protected String doInBackground(Object[] objects) {

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
            res = price;

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