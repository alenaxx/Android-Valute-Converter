package com.example.icandoit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ValuteConvert extends MainActivity {

private TextView result;
private Spinner valute1;
private  Spinner valute2;
private EditText pushNumber;
private Button button;

public  String[] z = {"EUR", "USD", "RUB", "JPY"};

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valute);


        result = (TextView) findViewById(R.id.result);
        valute1 = (Spinner) findViewById(R.id.valute1);
        valute2 = (Spinner) findViewById(R.id.valute2);
        pushNumber = (EditText) findViewById(R.id.pushNumber);
        button = (Button) findViewById(R.id.button);
        mDBHelper = new DatabaseHelper(this);



        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        button.setOnClickListener(new View.OnClickListener() {
            int i=3;
            @Override
            public void onClick(View v) {
                if(pushNumber.getText().toString().length()>0) {
                    i++;
                    String pn =pushNumber.getText().toString();
                    String v1 =valute1.getSelectedItem().toString();
                    String v2 =valute2.getSelectedItem().toString();

                    switch (valute1.getSelectedItem().toString()) {
                        case "EUR":
                            switch (valute2.getSelectedItem().toString()) {
                                case "EUR":
                                    result.setText(pushNumber.getText().toString());
                                    String r =pushNumber.getText().toString();
                                    result.setText(r);
                                    final String Insert_Data1="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data1);
                                    break;
                                case "USD":
                                    double push1 = Double.parseDouble(pushNumber.getText().toString());
                                    double res = getUSD() / getEUR() * push1;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data);

                                    break;
                                case "RUB":
                                    double push2 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getEUR() * push2;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data3="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data3);
                                    break;
                                case "JPY":
                                    double push3 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getJPY() / getEUR() * push3;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data2="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data2);

                                    break;
                            }
                            break;
                        case "USD":
                            switch (valute2.getSelectedItem().toString()) {
                                case "USD":
                                    String r =pushNumber.getText().toString();
                                    result.setText(r);
                                    final String Insert_Data1="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data1);
                                    break;
                                case "EUR":
                                    double push1 = Double.parseDouble(pushNumber.getText().toString());
                                    double res = getEUR() / getUSD() * push1;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data);
                                    break;
                                case "RUB":
                                    double push2 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getUSD() * push2;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data4="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data4);
                                    break;
                                case "JPY":
                                    double push3 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getUSD() / getEUR() * push3;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data5="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data5);
                                    break;
                            }

                            break;
                        case "RUB":
                            switch (valute2.getSelectedItem().toString()) {
                                case "RUB":
                                    String r =pushNumber.getText().toString();
                                    result.setText(r);
                                    final String Insert_Data1="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data1);
                                    break;
                                case "EUR":
                                    double push1 = Double.parseDouble(pushNumber.getText().toString());
                                    double res = push1 / getEUR();
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data);
                                    break;
                                case "USD":
                                    double push2 = Double.parseDouble(pushNumber.getText().toString());
                                    res = push2 / getUSD();
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data8="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data8);

                                    break;
                                case "JPY":
                                    double push3 = Double.parseDouble(pushNumber.getText().toString());
                                    res = push3 / getJPY();
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data9="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data9);
                                    break;
                            }
                            break;
                        case "JPY":
                            switch (valute2.getSelectedItem().toString()) {
                                case "JPY":
                                    String r =pushNumber.getText().toString();
                                    result.setText(r);
                                    final String Insert_Data1="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data1);
                                    break;
                                case "EUR":
                                    double push1 = Double.parseDouble(pushNumber.getText().toString());
                                    double res = getEUR() / getJPY() * push1;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data);
                                    break;
                                case "RUB":
                                    double push2 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getJPY() * push2;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data0="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data0);
                                    break;
                                case "USD":
                                    double push3 = Double.parseDouble(pushNumber.getText().toString());
                                    res = getUSD() / getJPY() * push3;
                                    r = Math.round(res*100)/100.0d+"";
                                    result.setText(r);
                                    final String Insert_Data11="INSERT INTO ConverterHistory VALUES('" + v1 +"','" + pn +"' ,'" + v2 +" ','"+r+"' ,$i)";
                                    mDb.execSQL(Insert_Data11);
                                    break;
                            }

                            break;

                    }

                }
                else{
                    Toast.makeText(getBaseContext(), "введите сумму" , Toast.LENGTH_SHORT).show();
                }

            }
            public double getEUR() {
                String l =n[35].replaceAll("[^0-9]", "");
                double EUR = Double.parseDouble(l);
                EUR = EUR /10000;
                return EUR;

            }
            public double getUSD() {
                String l =n[32].replaceAll("[^0-9]", "");
                double USD = Double.parseDouble(l);
                USD = USD /10000;
                return USD;

            }
            public double getJPY() {
                String l =n[101].replaceAll("[^0-9]", "");
                double JPY = Double.parseDouble(l);
                JPY = JPY /10000;
                return JPY;

            }

        });

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, z);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.valute1);
        spinner1.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.valute2);
        spinner2.setAdapter(adapter);
        // заголовок
        spinner1.setPrompt("Выбери валюту");
        // выделяем элемент
        spinner1.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
             //   Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
               // Toast.makeText(getBaseContext(), "введите сумму" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

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






}
