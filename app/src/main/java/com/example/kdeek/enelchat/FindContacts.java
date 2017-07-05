package com.example.kdeek.enelchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FindContacts extends AppCompatActivity {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    ArrayList<User> userList = new ArrayList<>();
    static HashMap<String,User> usenames =  new HashMap<>();
    RecyclerView contactsView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_contacts);
        contactsView = (RecyclerView) findViewById(R.id.contactsView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference myRef = mDatabase.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    userList.add(user);
                    usenames.put(postSnapshot.getKey(),postSnapshot.getValue(User.class));
                }



                contactsAdapter adapter = new contactsAdapter(FindContacts.this,userList, new contactsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {

                        Intent intent = new Intent(FindContacts.this,ContackProfileView.class);
                        Log.d("sent",userList.get(position).getID());
                        intent.putExtra("user",userList.get(position).getID());
                        startActivityForResult(intent,002);
                    }
                });

                contactsView.setAdapter(adapter);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(FindContacts.this, LinearLayoutManager.VERTICAL, false);
                contactsView.setLayoutManager(layoutManager1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
