package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ArrayList<Icon> icons = new ArrayList<>();
        icons.add(new Icon("نرخ ارزها", R.raw.curr, new Intent(MainActivity.this,CurrencyActivity.class)));
        icons.add(new Icon("تاپ لیست معاملات", R.raw.topl, new Intent(MainActivity.this,TopListActivity.class)));
        icons.add(new Icon("تبدیلگر ارز", R.raw.conv, new Intent(MainActivity.this,ConverterActivity.class)));
        icons.add(new Icon("دسترسی به بانکها", R.raw.bankk, new Intent(MainActivity.this,BankActivity.class)));


        gridView = findViewById(R.id.main_grid);
        GridAdapter adapter = new GridAdapter(icons);
        gridView.setAdapter(adapter);
        gridView.setVerticalSpacing(13);
        gridView.setHorizontalSpacing(13);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProgressDialog progressDialog;
                if(i==0 || i==1) {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("در حال دریافت داده ها . .");
                    progressDialog.setMessage("کمی صبر کنید . . .");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    startActivity(icons.get(i).getActivity());
                    finish();
                }else { startActivity(icons.get(i).getActivity()); finish();}
            }
        });



    }
    class wait extends AsyncTask{
        Intent activity;
        public  wait(Intent a)
        {
            activity=a;

        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            startActivity(activity);

            return null;
        }
    }

    class GridAdapter extends BaseAdapter {

        ArrayList<Icon> icons ;
        public GridAdapter(ArrayList<Icon> icons){
            this.icons=icons;
        }


        @Override
        public int getCount() {
            return icons.size();
        }

        @Override
        public Icon getItem(int position) {
            return icons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_grid,parent,false);
            }
            TextView text;
            LottieAnimationView l ;
            text = view.findViewById(R.id.button_text);
            l = view.findViewById(R.id.animation_view);
            text.setText(icons.get(position).getName());
            l.setAnimation(icons.get(position).getId());
                    //setImageResource();
            return view;
        }
    }
    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}