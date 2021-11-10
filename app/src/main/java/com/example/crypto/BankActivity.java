package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BankActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        ArrayList<BankIcon> icons = new ArrayList<>();
        icons.add(new BankIcon("بانک کشاورزی",R.drawable.keshavarzi,"https://ib.bki.ir/pid2.lmx"));
        icons.add(new BankIcon("بانک ملی",R.drawable.meli,"https://bmi.ir/"));
        icons.add(new BankIcon("بانک سپه",R.drawable.sepah,"https://www.ebanksepah.ir/"));
        icons.add(new BankIcon("پست بانک ایران",R.drawable.post,"https://www.postbank.ir/"));
        icons.add(new BankIcon("بانک تجارت",R.drawable.tejarat,"https://www.tejaratbank.ir/index.php"));
        icons.add(new BankIcon("بانک ملت",R.drawable.melat,"http://en.bankmellat.ir/"));
        icons.add(new BankIcon("بانک صادرات ایران",R.drawable.saderat,"https://bsi.ir/Pages/Home.aspx"));
        icons.add(new BankIcon("بانک توسعه تعاون",R.drawable.tosetavon,"https://ttbank.ir/fa/"));
        icons.add(new BankIcon("بانک صنعت و معدن",R.drawable.sanatomadan,"https://www.bim.ir/fa-IR/Portal/1/page/%D8%B5%D9%81%D8%AD%D9%87-%D8%A7%D8%B5%D9%84%DB%8C"));
        icons.add(new BankIcon("بانک مسکن",R.drawable.maskan,"https://ibn.bank-maskan.ir/login"));
        icons.add(new BankIcon("بانک رفاه کارگران",R.drawable.refah,"https://www.refah-bank.ir/"));
        icons.add(new BankIcon("بانک اقتصاد نوین",R.drawable.eqtesad,"https://www.enbank.ir/"));
        icons.add(new BankIcon("بانک پارسیان",R.drawable.parsian,"https://www.parsian-bank.ir/"));
        icons.add(new BankIcon("بانک کارآفرین",R.drawable.karafarin,"https://www.karafarinbank.ir/"));
        icons.add(new BankIcon("بانک سامان",R.drawable.saman,"https://sb24.ir/"));
        icons.add(new BankIcon("بانک پاسارگاد",R.drawable.pasargad,"https://www.bpi.ir/"));
        icons.add(new BankIcon("بانک سرمایه",R.drawable.sarmaye,"https://www.sbank.ir/sis_products_services/102065/102065.htm"));
        icons.add(new BankIcon("بانک سینا",R.drawable.sina,"https://www.sinabank.ir/"));
        icons.add(new BankIcon("بانک شهر",R.drawable.shahr,"https://www.shahr-bank.ir/"));
        icons.add(new BankIcon("بانک گردشگری",R.drawable.ardeshgari,"https://ibank.tourism-bank.com/"));
        icons.add(new BankIcon("بانک ایران زمین",R.drawable.iranzamin,"https://www.izbank.ir/"));
        //icons.add(new BankIcon("بانک خاورمیانه",R.drawable.khavarmianeh,"https://en.middleeastbank.ir/"));
        icons.add(new BankIcon("بانک آینده",R.drawable.ayande,"https://ba24.ir/"));
        icons.add(new BankIcon("بانک دی",R.drawable.dey,"https://www.bank-day.ir/"));
        icons.add(new BankIcon("بانک قرض الحسنه مهر ایران",R.drawable.mehr,"https://qmb.ir/index.aspx?tempname=Average&lang=1&sub=0&epageId=1310&isPopUp=false"));

        gridView = findViewById(R.id.bank_grid);
        BankAdapter adapter = new BankAdapter(icons);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(icons.get(i).getUrl())));

            }
        });
    }

    class BankAdapter extends BaseAdapter {
        ArrayList<BankIcon> icons ;
        public BankAdapter(ArrayList<BankIcon> icons){
            this.icons=icons;
        }


        @Override
        public int getCount() {
            return icons.size();
        }

        @Override
        public BankIcon getItem(int position) {
            return icons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_grid,parent,false);
            }
            TextView text;
            ImageView image;
            text = view.findViewById(R.id.currency_name);
            image = view.findViewById(R.id.currency_icon);
            text.setText(icons.get(position).getName());
            image.setImageResource(icons.get(position).getImage());
            return view;
        }
    }
}