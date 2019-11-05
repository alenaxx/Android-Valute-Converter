package com.example.icandoit;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class History extends AppCompatActivity {
    //Объявим переменные компонентов
    Button button;
    TextView textView;

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

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

        //Найдем компоненты в XML разметке
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        //Пропишем обработчик клика кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";


                Cursor cursor = mDb.rawQuery("SELECT * FROM ConverterHistory ORDER BY `id` DESC LIMIT 10 ", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(0) +" "+cursor.getString(1)+" = "+cursor.getString(2)+" "+cursor.getString(3)+  "\n";
                    cursor.moveToNext();
                }
                cursor.close();
             /*   int i=3;
                final String Insert_Data="INSERT INTO ConverterHistory VALUES('JPY','1','JPY','1',$i)";
                mDb.execSQL(Insert_Data);*/
                textView.setText(product);
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
