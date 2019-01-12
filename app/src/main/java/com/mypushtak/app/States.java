package com.mypushtak.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.DatabaseHelper.StateDatabaseHelper;
import com.mypushtak.app.Helper.State;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class States extends AppCompatActivity {

    Button btn;
    ListView lvStates;
    ArrayList<State> states=new ArrayList<>();
    ArrayAdapter<State> stateAdapter;

    StateDatabaseHelper db;

    @Override
    protected void onStart() {
        super.onStart();
        RequestQueue queue = Volley.newRequestQueue(States.this);
        String url = ConstantUrl.URL+"getState";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject state_object = jsonArray.getJSONObject(i);
                        String state_name = state_object.getString("stateName");
                        db.insertNote(state_name);
                        refreshStateList();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("onResponse", "response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(postRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);

        btn=findViewById(R.id.btn);
        lvStates=findViewById(R.id.lvStaes);

        stateAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, states);

        db=new StateDatabaseHelper(this);

        lvStates.setAdapter(stateAdapter);

        refreshStateList();

            RequestQueue queue = Volley.newRequestQueue(States.this);
            String url = ConstantUrl.URL+"getState";
            StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject state_object = jsonArray.getJSONObject(i);
                            String state_name = state_object.getString("stateName");
                            db.insertNote(state_name);
                            refreshStateList();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("onResponse", "response: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(postRequest);

    }

    private void refreshStateList(){
        List<State> stateslist=db.getAllStates();
        states.clear();
        states.addAll(stateslist);
        stateAdapter.notifyDataSetChanged();
    }


}
