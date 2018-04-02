package com.food.r.cuc.h;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.ArrayList;

public class SignUpNGOActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    DatabaseReference newDB;
    private EditText e1,e2,e3,e4,e5,e6,e7,e8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_ngo);
        Toast.makeText(this,"All Fields are mandatory",Toast.LENGTH_SHORT).show();
        e1 = (EditText) findViewById(R.id.NGOName);
        e2 = (EditText) findViewById(R.id.NGOPhoneNo_);
        e3 = (EditText) findViewById(R.id.AddressNGO);
        e4 = (EditText) findViewById(R.id.PinCode);
        e5 = (EditText) findViewById(R.id.City);
        e6 = (EditText) findViewById(R.id.pwd1);
        e7 = (EditText) findViewById(R.id.pwd2);
        e8 = (EditText) findViewById(R.id.Email);
//        newDB = mDatabase.push();
//        String key  = newDB.getKey();
        callAll();

    }
    public void callAll()
    {
        obj_list();
        addToAll();
    }

    // Get the content of cities.json from assets directory and store it as string
    public String getJson()
    {
        String json=null;
        try
        {
            // Opening cities.json file
            InputStream is = getAssets().open("cities.json");
            // is there any content in the file
            int size = is.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            is.read(buffer);
            // close the stream --- very important
            is.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return json;
        }
        return json;
    }

    // This add all JSON object's data to the respective lists
    void obj_list()
    {
        // Exceptions are returned by JSONObject when the object cannot be created
        try
        {
            // Convert the string returned to a JSON object
            JSONObject jsonObject=new JSONObject(getJson());
            // Get Json array
            JSONArray array=jsonObject.getJSONArray("array");
            // Navigate through an array item one by one
            for(int i=0;i<array.length();i++)
            {
                // select the particular JSON data
                JSONObject object=array.getJSONObject(i);
                String city=object.getString("name");
                String state=object.getString("state");
                // add to the lists in the specified format
                listSpinner.add(String.valueOf(i+1)+" : "+city+" , "+state);
                listAll.add(city+" , "+state);
                listCity.add(city);
                listState.add(state);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    void addToAll()
    {
        act=(AutoCompleteTextView)findViewById(R.id.City);
        adapterSetting(listAll);
    }
    void adapterSetting(ArrayList arrayList)
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arrayList);
        act.setAdapter(adapter);
        hideKeyBoard();
    }
    public void hideKeyBoard()
    {
        act.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }


    ArrayList<String> listSpinner=new ArrayList<String>();
    // to store the city and state in the format : City , State. Eg: New Delhi , India
    ArrayList<String> listAll=new ArrayList<String>();
    // for listing all states
    ArrayList<String> listState=new ArrayList<String>();
    // for listing all cities
    ArrayList<String> listCity=new ArrayList<String>();
    // access all auto complete text views
    AutoCompleteTextView act;

    public  void NGOsubmit(View view)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("ngo").child(e5.getText().toString()).push();

        mDatabase.child("Name").setValue(e1.getText().toString());
        mDatabase.child("ngophone").setValue(e2.getText().toString());
        mDatabase.child("ngoaddress").setValue(e3.getText().toString());
        mDatabase.child("ngopin").setValue(e4.getText().toString());
//        mDatabase.child("ngoncity").setValue(e5.getText().toString());
        mDatabase.child("ngopwd1").setValue(e6.getText().toString());
        mDatabase.child("ngoemail").setValue(e8.getText().toString());
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
