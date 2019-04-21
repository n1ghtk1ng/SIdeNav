package com.example.sidenav;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AcceptedInvites extends Fragment {

    private static final String TAG = "AcceptedInvites";
    private ArrayList<Event> events;
    private InviteAdapter adapter;
    public AcceptedInvites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accepted_invites, container, false);

        ListView listView = (ListView) v.findViewById(R.id.accepted_invites);
        events = new ArrayList<Event>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        // fetch accepted invites
        FirebaseApp.initializeApp(getContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String phNo = "+919205430070";
        db.collection("Invitation").whereEqualTo("accepted",true)
                .whereEqualTo("userID",phNo)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String>arrayList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData().get("invitationId").toString());
                                arrayList.add(document.getData().get("invitationId").toString());
                            }
                            getInfo(arrayList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        adapter = new InviteAdapter(getContext(), events, "accepted");
        listView.setAdapter(adapter);

        return v;
    }

    private void getInfo(final ArrayList<String> invitations) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Events").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Event>acceptedInvites = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                Event e = new Event(document.get("address").toString()
                                        ,document.get("latitude").toString(),
                                        document.get("longitude").toString(),
                                        document.get("date").toString(),
                                        document.get("name").toString(),
                                        document.get("organiserId").toString(),
                                        document.get("time").toString(),
                                        document.getId().toString()
                                );
                                if(invitations.contains(e.getId())){
                                    acceptedInvites.add(e);
                                }
                            }
                            events.clear();
                            events.addAll(acceptedInvites);

                            adapter.notifyDataSetChanged();

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
