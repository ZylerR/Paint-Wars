package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Color_Selection extends AppCompatActivity {
    ImageButton blue;
    ImageButton green;
    ImageButton red;
    ImageButton yellow;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int clickCounter;
    boolean player1;
    int player1color = 0;
    int player2color = 0;
    String p1 = "p1";
    String p2 = "p1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        database = FirebaseDatabase.getInstance();

        clickCounter = 0;

        Intent intent = getIntent();
        player1 = intent.getBooleanExtra("player1",false);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("color/" + p1);
                addEventListener();
                //player = true;
                //p2Butt.setEnabled(false);
                blue.setEnabled(false);
                green.setEnabled(false);
                red.setEnabled(false);
                yellow.setEnabled(false);
                blue.setImageResource(R.drawable.whitestar);
                green.setImageResource(R.drawable.whitestar);
                red.setImageResource(R.drawable.whitestar);
                yellow.setImageResource(R.drawable.whitestar);
                if(player1 == true)
                {
                    player1color = 100;
                    //System.out.println("P1 Color = " + player1color);
                    myRef.setValue("blueP1");

                }
                if(player1 == false)
                {
                    player2color = 100;
                    myRef.setValue("blueP2");
                }
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("color/" + p1);
                addEventListener();
                //player = true;
                //p2Butt.setEnabled(false);
                blue.setEnabled(false);
                green.setEnabled(false);
                red.setEnabled(false);
                yellow.setEnabled(false);
                blue.setImageResource(R.drawable.whitestar);
                green.setImageResource(R.drawable.whitestar);
                red.setImageResource(R.drawable.whitestar);
                yellow.setImageResource(R.drawable.whitestar);
                if(player1 == true)
                {
                    player1color = 200;
                    myRef.setValue("greenP1");
                }
                if(player1 == false)
                {
                    player2color = 200;
                    myRef.setValue("greenP2");
                }
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("color/" + p1);
                addEventListener();
                //player = true;
                //p2Butt.setEnabled(false);
                blue.setEnabled(false);
                green.setEnabled(false);
                red.setEnabled(false);
                yellow.setEnabled(false);
                blue.setImageResource(R.drawable.whitestar);
                green.setImageResource(R.drawable.whitestar);
                red.setImageResource(R.drawable.whitestar);
                yellow.setImageResource(R.drawable.whitestar);
                if(player1 == true)
                {
                    player1color = 300;
                    myRef.setValue("redP1");

                }
                if(player1 == false)
                {
                    player2color = 300;
                    myRef.setValue("redP2");
                }
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("color/" + p1);
                addEventListener();
                //player = true;
                //p2Butt.setEnabled(false);
                blue.setEnabled(false);
                green.setEnabled(false);
                red.setEnabled(false);
                yellow.setEnabled(false);
                blue.setImageResource(R.drawable.whitestar);
                green.setImageResource(R.drawable.whitestar);
                red.setImageResource(R.drawable.whitestar);
                yellow.setImageResource(R.drawable.whitestar);
                if(player1 == true)
                {
                    player1color = 400;
                    myRef.setValue("yellowP1");

                }
                if(player1 == false)
                {
                    player2color = 400;
                    myRef.setValue("yellowP2");
                }
            }
        });
        myRef = database.getReference("color/" + p1);
        //System.out.println(myRef.value);
        addEventListener();
        //player = true;
        myRef.setValue("BRUH");
    }

    public void colorSelection(View v) {
        // number 100 for intent sending data
        /*if(v.getId() == R.id.blue) {
            blue.setEnabled(false);

            // Assign color to each player based on who clicked first (player 1 clicked first)
            if(clickCounter == 0) {
                player1color = 100;
            }
            else {
                player2color = 100;
            }
        }
        // number 200 for intent sending data
        else if (v.getId() == R.id.green) {
            green.setEnabled(false);

            // Assign color to each player based on who clicked first (player 1 clicked first)
            if(clickCounter == 0) {
                player1color = 200;
            }
            else {
                player2color = 200;
            }
        }
        // number 300 for intent sending data
        else if(v.getId() == R.id.red) {
            red.setEnabled(false);

            // Assign color to each player based on who clicked first (player 1 clicked first)
            if(clickCounter == 0) {
                player1color = 300;
            }
            else {
                player2color = 300;
            }
        }
        // number 400 for intent sending data
        else {
            yellow.setEnabled(false);

            // Assign color to each player based on who clicked first (player 1 clicked first)
            if(clickCounter == 0) {
                player1color = 400;
            }
            else {
                player2color = 400;
            }
        }
        clickCounter++;

        // Each player has picked, and now start the game passing in each player's choice of color

         */
        /*if(clickCounter == 2) {
            Intent intent = new Intent(this, Paint_Game.class);
            intent.putExtra("player", player1);
            intent.putExtra("player1Color", player1color);
            //System.out.println("Player 1 color is " + player1color);
            intent.putExtra("player2Color", player2color);
            //System.out.println("Player 1 color is " + player2color);
            startActivity(intent);
        }*/
    }
    private void addEventListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.println("Clickcounter = " + clickCounter);
                if(value.equals("blueP1"))
                {
                    blue.setEnabled(false);
                    blue.setImageResource(R.drawable.whitestar);
                    System.out.println("P1 Color is " + player1color);
                    player1color = 100;
                    clickCounter += 1;
                    //System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(value.equals("blueP2"))
                {
                    blue.setEnabled(false);
                    blue.setImageResource(R.drawable.whitestar);
                    System.out.println("P2 Color is " + player1color);
                    player2color = 100;
                    clickCounter += 1;
                }
                if(value.equals("greenP2"))
                {
                    green.setEnabled(false);
                    green.setImageResource(R.drawable.whitestar);
                    player2color = 200;
                    clickCounter += 1;
                }
                if(value.equals("greenP1"))
                {
                    green.setEnabled(false);
                    green.setImageResource(R.drawable.whitestar);
                    player1color = 200;
                    clickCounter += 1;
                }
                if(value.equals("redP1"))
                {
                    red.setEnabled(false);
                    red.setImageResource(R.drawable.whitestar);
                    player1color = 300;
                    clickCounter += 1;
                    //System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(value.equals("redP2"))
                {
                    red.setEnabled(false);
                    red.setImageResource(R.drawable.whitestar);
                    player2color = 300;
                    clickCounter += 1;
                    //System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(value.equals("yellowP1"))
                {
                    yellow.setEnabled(false);
                    yellow.setImageResource(R.drawable.whitestar);
                    player1color = 400;
                    clickCounter += 1;
                    //System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(value.equals("yellowP2"))
                {
                    yellow.setEnabled(false);
                    yellow.setImageResource(R.drawable.whitestar);
                    player2color = 400;
                    clickCounter += 1;
                    //System.out.println("PR: " + playersReady);
                    //player = true;
                }
                if(clickCounter == 4)
                {
                    System.out.println("RUNS???");
                    Intent intent = new Intent(Color_Selection.this, Paint_Game.class);
                    intent.putExtra("player", player1);
                    intent.putExtra("player1Color", player1color);
                    System.out.println("Player 1 color is " + player1color);
                    intent.putExtra("player2Color", player2color);
                    System.out.println("Player 2 color is " + player2color);
                    startActivity(intent);
                }
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