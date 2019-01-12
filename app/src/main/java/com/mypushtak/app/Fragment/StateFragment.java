package com.mypushtak.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mypushtak.app.Activity.AddAddress;
import com.mypushtak.app.Activity.Donationform;
import com.mypushtak.app.Activity.EditAddress;
import com.mypushtak.app.DatabaseHelper.StateDatabaseHelper;
import com.mypushtak.app.R;
import com.mypushtak.app.Helper.State;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StateFragment extends DialogFragment {

    ListView lvStates;
    ArrayList<State> states=new ArrayList<>();
    ArrayAdapter<State> stateAdapter;

    StateDatabaseHelper db;

    int i;

    public static StateFragment newInstance(int i) {

        Bundle args = new Bundle();

        StateFragment fragment = new StateFragment();
        args.putInt("intent",i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.state_fragment, null);
        db=new StateDatabaseHelper(getActivity());
        i=getArguments().getInt("intent");
        lvStates=v.findViewById(R.id.lvStaes);
        stateAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, states);

        lvStates.setAdapter(stateAdapter);
        refreshStateList();

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        lvStates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(i==1) {
                    Donationform callingActivity = (Donationform) getActivity();
                    callingActivity.onUserStateSelectValue(states.get(position).getState_name(), states.get(position).getId());
                }

                else if (i==2){
                    EditAddress callingEditAddressActivity= (EditAddress) getActivity();
                    callingEditAddressActivity.onUserStateSelectValue(states.get(position).getState_name(),states.get(position).getId());
                }

                else {
                    AddAddress callingAddAddressActivity = (AddAddress) getActivity();
                    callingAddAddressActivity.onUserStateSelectValue(states.get(position).getState_name(), states.get(position).getId());
                }
            }
        });

        builder.setView(v);
        return builder.create();
    }

    private void refreshStateList(){
        List<State> stateslist=db.getAllStates();
        states.clear();
        states.addAll(stateslist);
        stateAdapter.notifyDataSetChanged();
    }
}
