package com.example.rr17d2.flappybird;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Database {
    FirebaseDatabase db;
    DatabaseReference dbRef;
    String name;
    long myScore = 0;
    List<String> scores;
    DatabaseReference topFive;

    public Database(String name) {
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Scores");
        this.name = name;
        scores = new ArrayList<String>();
        DatabaseReference myData = db.getReference("Scores/" + name);
        myData.addValueEventListener(newScorePost);

        Query topFiveQuery = dbRef.orderByValue().limitToLast(5);
        topFiveQuery.addChildEventListener(childEventListener);
    }

        public void saveHighScore(int score) {
            myScore = score;
            dbRef.child(this.name).setValue(myScore);
            MainActivity.highScore = score;
        }

        /*ValueEventListener newScorePost = new ValueEventListener() {
            @Override
            public void onDataChange(dataSnapshot dataSnapshot) {
                //protect against new node in db
                if(dataSnapshot.getValue() != null) {
                    myScore = (long)dataSnapshot.getValue();
                }
                MainActivity.highScore = myScore;
                MainActivity.highScoreTextView.setText(String.valueOf(myScore));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //do something
            }
        };
        */
        ValueEventListener newScorePost = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //protect against new node in db
                if(dataSnapshot.getValue() != null) {
                    myScore = (long)dataSnapshot.getValue();
                }
                MainActivity.highScore = myScore;
                MainActivity.highScoreTextView.setText(String.valueOf(myScore));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()) {
                    scores.add(dataSnapshot.getKey() + ":" + dataSnapshot.getValue());
                    if(MainActivity.scoreboardListView != null ) {
                        MainActivity.scoreboardListView.setSelection(MainActivity.adapter.getCount() - 1);
                    }
                }
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
        };
}
