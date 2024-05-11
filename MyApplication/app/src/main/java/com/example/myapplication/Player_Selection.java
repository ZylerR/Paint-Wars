package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Player_Selection extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    String p1 = "p1";
    String p2 = "p2";
    String scoreP1 = "0";
    String scoreP2 = "0";
    Button p1Butt;
    Button p2Butt;
    //Button playerVal;
    boolean player;
    int playersReady;
    int unique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        p1Butt = findViewById(R.id.player1);
        p2Butt = findViewById(R.id.player2);
        //playerVal = findViewById(R.id.boolVal);
        database = FirebaseDatabase.getInstance();
        //bruh = 0;
        playersReady = 0;
        //myRef = database.getReference("player/");

        //myRef.setValue("Hello, World! BRjjjhjnUH!!!!!");
        p1Butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("player/" + p1);
                addEventListener();
                player = true;
                p2Butt.setEnabled(false);
                myRef.setValue("p1Done");
            }
        });
        p2Butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("player/" + p1);
                addEventListener();
                player = false;
                p1Butt.setEnabled(false);
                myRef.setValue("p2Done");

            }
        });
        /*playerVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Player Bool is: " + player);
                //myRef = database.getReference("player/" + p1);
                if(player == true)
                {
                    myRef.setValue("changingTextP1");
                    //addEventListener();
                }
                if(player == false)
                {
                    myRef.setValue("changingTextP2");
                    //addEventListener();
                    //myRef.addValueEventListener()
                }
            }
        });*/
        myRef = database.getReference("player/" + p1);
        //System.out.println(myRef.value);
        addEventListener();
        //player = true;
        myRef.setValue("BRUH");
        //System.out.println("WHAT?? " + myRef.getRef());

    }

    private void addEventListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("p1Done"))
                {
                    p1Butt.setEnabled(false);
                    playersReady += 1;
                    System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(value.equals("p2Done"))
                {
                    p2Butt.setEnabled(false);
                    playersReady += 1;
                    System.out.println("PR: " + playersReady);

                    //player = false;
                }
                if(playersReady >= 4)
                {
                    Intent intent = new Intent(Player_Selection.this, Color_Selection.class);
                    intent.putExtra("player1", player);
                    startActivity(intent);
                }
                /*if(value.equals("changingTextP1") && p1Butt.isEnabled() == false && p2Butt.isEnabled() == false)
                {
                    //System.out.println("Runs in both?");
                    //bruh += 1;
                    //playerVal.setText("Player 1 Has Taken Over!" + bruh);
                    //myRef.setValue("bufferChange");
                }
                if(value.equals("changingTextP2") && p1Butt.isEnabled() == false && p2Butt.isEnabled() == false)
                {
                    //bruh -= 1;
                    //playerVal.setText("Player 2 Has Taken Over!" + bruh);
                    //myRef.setValue("bufferChange");
                }
                if(player == true)
                {
                    bruh += 1;
                }*/
                Log.d("DatabaseWatch", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BBB", "Failed to read value.", error.toException());
            }
        });
    }
}