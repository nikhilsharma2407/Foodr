package com.food.r.cuc.h;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpNGOActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ngo);
        Toast.makeText(this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
        EditText e1 = (EditText) findViewById(R.id.NGOName);
        EditText e2 = (EditText) findViewById(R.id.NGOPhoneNo_);
        EditText e3 = (EditText) findViewById(R.id.AddressNGO);
        EditText e4 = (EditText) findViewById(R.id.PinCode);
        EditText e5 = (EditText) findViewById(R.id.City);
        EditText e6 = (EditText) findViewById(R.id.pwd1);
        EditText e7 = (EditText) findViewById(R.id.pwd2);
        EditText e8 = (EditText) findViewById(R.id.Email);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("ngo");
        DatabaseReference newDB = mDatabase.push();
        String key  = newDB.getKey();
        newDB.child("ngoname").setValue(e1);
        newDB.child("ngophone").setValue(e2);
        newDB.child("ngoaddress").setValue(e3);
        newDB.child("ngopin").setValue(e4);
        newDB.child("ngoncity").setValue(e5);
        newDB.child("ngopwd1").setValue(e6);
        newDB.child("ngoemail").setValue(e7);

    }

    public boolean nullFields() {
        EditText e1 = (EditText) findViewById(R.id.NGOName);
        EditText e2 = (EditText) findViewById(R.id.NGOPhoneNo_);
        EditText e3 = (EditText) findViewById(R.id.AddressNGO);
        EditText e4 = (EditText) findViewById(R.id.PinCode);
        EditText e5 = (EditText) findViewById(R.id.City);
        EditText e6 = (EditText) findViewById(R.id.pwd1);
        EditText e7 = (EditText) findViewById(R.id.pwd2);
        EditText e8 = (EditText) findViewById(R.id.Email);

        String s1, s2, s3, s4, s5,s6,s7,s8;
        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = e3.getText().toString();
        s4 = e4.getText().toString();
        s5 = e5.getText().toString();
        s6 = e6.getText().toString();
        s7 = e7.getText().toString();
        s8 = e8.getText().toString();

//    if( chkString(s1) && chkString(s2) && chkString(s3) && chkString(s4) && chkString(s5))
        if (s1.length() > 0 && s2.length() > 0 && s3.length() > 0 && s4.length() > 0 && s5.length() > 0
                && s6.length()>0 && s7.length()>0 && s8.length()>0)
            return true;
        else
            return false;
    }

}
