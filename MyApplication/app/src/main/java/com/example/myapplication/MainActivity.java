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

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    String p1 = "p1";
    String p2 = "p2";
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("start/" + p1);
                myRef.setValue("p1Start");
            }
        });
        myRef = database.getReference("start/" + p1);
        //System.out.println(myRef.value);
        addEventListener();
        //player = true;
        myRef.setValue("BRUH");
    }

    /*public void start(View v) {
        Intent intent = new Intent(this, Player_Selection.class);
        startActivity(intent);
    }*/
    private void addEventListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("p1Start"))
                {
                    Intent intent = new Intent(MainActivity.this, Player_Selection.class);
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