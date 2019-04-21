package com.example.sidenav;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InviteAdapter extends ArrayAdapter<Event> {
    private Context mContext;
    private List<Event> eventList = new ArrayList<>();
    private String activity;

    public InviteAdapter(@NonNull Context context, ArrayList<Event> list, String activity) {
        super(context, 0 , list);
        mContext = context;
        eventList = list;
        this.activity = activity;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.invite_list_item,parent,false);

        final Event currentInvite = eventList.get(position);


        TextView date = (TextView) listItem.findViewById(R.id.invite_date);
        date.setText(currentInvite.getDate());

        TextView time = (TextView) listItem.findViewById(R.id.invite_time);
        time.setText(currentInvite.getDate());

        TextView name = (TextView) listItem.findViewById(R.id.invite_name);
        name.setText(currentInvite.getName());

        Button button = (Button) listItem.findViewById(R.id.chat_btn);

        if(activity.equals("notaccepted")){
            button.setText("ACCEPT");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.equals("notaccepted")){
                    // make changes in database and make intent to mainactivity  accepted invite fragmment
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Invitation")
                            .whereEqualTo("invitationId",currentInvite.getId())
                            .whereEqualTo("userID", "+918440071009")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.d(TAG, document.getId() + " => " + document.getData());
                                            FirebaseFirestore.getInstance().collection("Invitation").document(document.getId())
                                                    .update("accepted",true)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            //TabLayout host = (TabLayout)((Activity)getContext()).findViewById(R.id.tablayout);
                                                            //host.getTabAt(0).select();
                                                            Fragment fragment = new MyInvites();
                                                            ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                                                                    .replace(R.id.framecontent, fragment).commit();
                                                        }
                                                    });
                                        }
                                    } else {
                                        Toast.makeText(getContext(),"Error occurred!!", Toast.LENGTH_SHORT);
                                        //Log.d(TAG, "Error getting documents: ", task.getException());
                                    }
                                }
                            });

                }
                else{
                    // show acceptants and other deatils on other fragment and chat option in other fragment
                    
                }
            }
        });


        return listItem;
    }

}
