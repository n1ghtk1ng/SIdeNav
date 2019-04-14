package com.example.sidenav;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MyEvents extends Fragment {
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String>arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public MyEvents(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_events, container, false);

        listView = (ListView) v.findViewById(R.id.list);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, arrayList);

        listView.setAdapter(arrayAdapter);
        arrayList.add("Delhi");
        arrayList.add("Chennai");
        arrayList.add("Mumbai");
        arrayList.add("Kolkata");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = arrayList.get(position);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.fun(city);
            }
        });

        return v;


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
