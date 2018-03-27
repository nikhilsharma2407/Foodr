package com.food.r.cuc.h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PublicKey;

public class SignUpNGOActivity extends AppCompatActivity {

    DatabaseReference rootRef,demoRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ngo);
        Toast.makeText(this, "All Fields are mandatory", Toast.LENGTH_SHORT).show();

        rootRef = FirebaseDatabase.getInstance().getReference();

        demoRef = rootRef.child("user");

        demoRef.push().setValue("test");
    }

    public void dbinsert(View view)
    {
    EditText e =  (EditText)findViewById(R.id.NGOName);
    //pu {sh creates a unique id in database

    }

    public boolean nullFields() {
        EditText e1 = (EditText) findViewById(R.id.NGOName);
        EditText e2 = (EditText) findViewById(R.id.NGOPhone);
        EditText e3 = (EditText) findViewById(R.id.City);
        EditText e4 = (EditText) findViewById(R.id.PinCode);
        EditText e5 = (EditText) findViewById(R.id.BuildingName);
        EditText e6 = (EditText) findViewById(R.id.State);
        EditText e7 = (EditText) findViewById(R.id.Area);
        String s1, s2, s3, s4, s5, s6,s7;
        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = e3.getText().toString();
        s4 = e4.getText().toString();
        s5 = e5.getText().toString();
        s6 = e6.getText().toString();
        s7 = e7.getText().toString();

//    if( chkString(s1) && chkString(s2) && chkString(s3) && chkString(s4) && chkString(s5))
        if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0 && s4.length() > 0 && s5.length() > 0 && s6.length() > 0 && s7.length() > 0)
            return true;
        else
            return false;


    }



    }




