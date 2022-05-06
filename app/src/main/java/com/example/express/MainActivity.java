package com.example.express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.express.module.Bookkeeping;
import com.example.express.module.Express;
import com.example.express.module.Notify;
import com.example.express.module.Trip;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // 行程助手
    public void trip(View view){

        Intent intent = new Intent(this, Trip.class);
        startActivity(intent);
    }

    // 快递助手
    public void express(View view){

        Intent intent = new Intent(this, Express.class);
        startActivity(intent);
    }

    // 记账助手
    public void bookkeeping(View view){

        Intent intent = new Intent(this, Bookkeeping.class);
        startActivity(intent);
    }

    // 读取通知栏
    public void notify(View view){

        Intent intent = new Intent(this, Notify.class);
        startActivity(intent);
    }


}