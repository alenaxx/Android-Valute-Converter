package com.example.icandoit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    TextView date;
    ProgressDialog progressDialog;
    private ListView lv;
    public String n[]=new String[110];
    public String z[]=new String[6];





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date=(TextView)findViewById(R.id.date);
        tv=(TextView)findViewById(R.id.textView);
        lv = (ListView)findViewById(R.id.name);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Загрузка...");
        progressDialog.setCancelable(false);
        Date currentTime = Calendar.getInstance().getTime();
        date.setText(currentTime.toString());

        new Async().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.save_settings:
                Intent intent2 = new Intent(this, ValuteConvert.class);
                startActivity(intent2);
                break;
            case R.id.hist:
                Intent intent3 = new Intent(this, History.class);
                startActivity(intent3);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private class Async extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings){
            try {
               Document document = Jsoup.connect("https://www.cbr-xml-daily.ru/daily_utf8.xml")
                       .timeout(3000).followRedirects(true).ignoreContentType(true).userAgent("Mozilla")
                       .get();

                Elements elements = document.select("Valute");
                int i=0;
                int q=0;

              for(Element element: elements) {
                  if(element.select("CharCode").text().equals("EUR")||
                          element.select("CharCode").text().equals("USD")||
                          element.select("CharCode").text().equals("JPY")){
                      z[q]=element.select("CharCode").text();
                      q++;
                      z[q]=element.select("Value").text();
                      q++;
                  }

                  n[i] = element.select("Name").text();
                  i++;
                  n[i] = element.select("CharCode").text();
                  i++;
                  n[i] = element.select("Value").text();
                  i++;

              }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            ArrayAdapter listAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,z);
            lv.setAdapter(listAdapter);
        }
    }




}
