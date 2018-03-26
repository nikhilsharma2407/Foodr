package com.food.r.cuc.h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void user(View view)
    {
        Intent intent = new Intent(this,OTP.class);
        startActivity(intent);
    }

    public void ngo(View view)
    {
        Intent intent = new Intent(this,SignUpNGOActivity.class);
        startActivity(intent);
    }


}
