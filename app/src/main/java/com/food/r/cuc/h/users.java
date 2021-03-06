package com.food.r.cuc.h;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.food.r.cuc.h.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class users extends AppCompatActivity
{
    DatabaseReference databaseReference;

    // array lists
    // for the spinner in the format : City_no : City , State. Eg : 144 : New Delhi , India
    ArrayList<String> listSpinner=new ArrayList<String>();
    // to store the city and state in the format : City , State. Eg: New Delhi , India
    ArrayList<String> listAll=new ArrayList<String>();
    // for listing all states
    ArrayList<String> listState=new ArrayList<String>();
    // for listing all cities
    ArrayList<String> listCity=new ArrayList<String>();
    // access all auto complete text views
    AutoCompleteTextView act;

    String phone, name;
    private DatabaseReference mDatabase;
    DatabaseReference newDB;
    private EditText e1,e2;
    private MultiAutoCompleteTextView multitext;
    private CheckBox cb1, cb2, cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        callAll();

        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("ngo");
        newDB = mDatabase.push();
        String key  = newDB.getKey();
    }


    public void findData(View view) {
        AutoCompleteTextView editText = (AutoCompleteTextView)findViewById(R.id.actAll);
        final String string = editText.getText().toString();
        DatabaseReference mchild = databaseReference.child("ngo").child(editText.getText().toString());
        mchild.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Map<String, Object> stringmap = (Map<String, Object>) dataSnapshot.getValue();

                Log.v("Database","Data is:" +stringmap.toString());

                StringBuffer buffer = new StringBuffer();
                buffer.append(stringmap.get("Name").toString());

//                MessageBox(stringMap.toString(),"");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public  void usersubmit(View view)
    {

//
//        newDB.child("ngoname").setValue(e1.getText().toString());
//        newDB.child("ngophone").setValue(e2.getText().toString());
//        newDB.child("ngoaddress").setValue(e3.getText().toString());
//        newDB.child("ngopin").setValue(e4.getText().toString());
//        newDB.child("ngoncity").setValue(e5.getText().toString());
//        newDB.child("ngopwd1").setValue(e6.getText().toString());
//        newDB.child("ngoemail").setValue(e8.getText().toString());
    }



    public void callAll()
    {
        obj_list();
        addToSpinner();
        addToAll();
        addCity();
        addState();
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

    // Add the data items to the spinner
    void addToSpinner()
    {
        Spinner spinner=(Spinner)findViewById(R.id.spCity);
        // Adapter for spinner
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // The first auto complete text view
    void addToAll()
    {
        act=(AutoCompleteTextView)findViewById(R.id.actAll);
        adapterSetting(listAll);
    }

    // The second auto complete text view
    void addCity()
    {
        act=(AutoCompleteTextView)findViewById(R.id.actCity);
        adapterSetting(listCity);
    }

    // The third auto complete text view
    void addState()
    {
        Set<String> set = new HashSet<String>(listState);
        act=(AutoCompleteTextView)findViewById(R.id.actState);
        adapterSetting(new ArrayList(set));
    }

    // setting adapter for auto complete text views
    void adapterSetting(ArrayList arrayList)
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,arrayList);
        act.setAdapter(adapter);
        hideKeyBoard();
    }

    // hide keyboard on selecting a suggestion
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
}
