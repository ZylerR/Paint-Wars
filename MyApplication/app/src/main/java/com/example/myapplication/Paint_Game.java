package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Paint_Game extends AppCompatActivity {
    int blueToken;
    int greenToken;
    int redToken;
    int yellowToken;
    int blueToken1;
    int greenToken1;
    int redToken1;
    int yellowToken1;
    int blueToken2;
    int greenToken2;
    int redToken2;
    int yellowToken2;
    boolean player1;
    String p1 = "p1";
    String p2 = "p2";
    int player1Color;
    int player2Color;
    ImageView player1Start;
    ImageView player2Start;
    int player1position;
    int player2position;
    int player1SelectedColor;
    int player2SelectedColor;
    int player1Squares;
    int player2Squares;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ImageButton leftArrow;
    ImageButton downArrow;
    ImageButton upArrow;
    ImageButton rightArrow;
    int player1Head;
    int player2Head;
    String prevP2Move;
    String prevP1Move;
    TextView countdown;
    ImageView[][] board = new ImageView[16][13];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_game);

        // creating variables for easy access to color changes on board
        blueToken = R.drawable.bluestar;
        greenToken = R.drawable.greenstar;
        redToken = R.drawable.redstar;
        yellowToken = R.drawable.yellowstar;
        prevP1Move = "";
        prevP2Move = "";
        // creating variables for easy access to initial start nodes
        blueToken1 = R.drawable.bluestarp1;
        blueToken2 = R.drawable.bluestarp2;
        greenToken1 = R.drawable.greenstarp1;
        greenToken2 = R.drawable.greenstarp2;
        redToken1 = R.drawable.redstarp1;
        redToken2 = R.drawable.redstarp2;
        yellowToken1 = R.drawable.yellowstarp1;
        yellowToken2 = R.drawable.yellowstarp2;
        leftArrow = findViewById(R.id.buttonLeft);
        downArrow = findViewById(R.id.buttonDown);
        rightArrow = findViewById(R.id.buttonRight);
        upArrow = findViewById(R.id.buttonUp);


        //Firebase Database Instance
        database = FirebaseDatabase.getInstance();

        //Getting Color/Player Data from previous activity
        Intent intent = getIntent();
        player1 = intent.getBooleanExtra("player", false);
        player1Color = intent.getIntExtra("player1Color", 0);
        //System.out.println("Player 1 color is " + player1Color);
        player2Color = intent.getIntExtra("player2Color", 0);
        //System.out.println("Player 2 color is " + player2Color);

        // Player 1 starting place is imageView33
        player1Start = findViewById(R.id.imageView33);
        player1position = 33;
        // Player 2 starting place is imageView176
        player2Start = findViewById(R.id.imageView176);
        player2position = 176;

        // Set player 1 starting position based on their color selection
        if (player1Color == 100) {
            player1Head = blueToken1;
            player1Start.setImageResource(blueToken1);
            player1SelectedColor = blueToken;
        } else if (player1Color == 200) {
            player1Head = greenToken1;
            player1Start.setImageResource(greenToken1);
            player1SelectedColor = greenToken;
        } else if (player1Color == 300) {
            player1Head = redToken1;
            player1Start.setImageResource(redToken1);
            player1SelectedColor = redToken;
        } else {
            player1Head = yellowToken1;
            player1Start.setImageResource(yellowToken1);
            player1SelectedColor = yellowToken;
        }

        // Set player 2 starting position based on their color selection.
        if (player2Color == 100) {
            player2Head = blueToken2;
            player2Start.setImageResource(blueToken2);
            player2SelectedColor = blueToken;
        } else if (player2Color == 200) {
            player2Head = greenToken2;
            player2Start.setImageResource(greenToken2);
            player2SelectedColor = greenToken;
        } else if (player2Color == 300) {
            player2Head = redToken2;
            player2Start.setImageResource(redToken2);
            player2SelectedColor = redToken;
        } else {
            player2Head = yellowToken2;
            player2Start.setImageResource(yellowToken2);
            player2SelectedColor = yellowToken;
        }

        // Initialize board with ImageViews to make it easier to access
        // Row 0
        board[0][0] = findViewById(R.id.imageView);
        board[0][1] = findViewById(R.id.imageView2);
        board[0][2] = findViewById(R.id.imageView3);
        board[0][3] = findViewById(R.id.imageView4);
        board[0][4] = findViewById(R.id.imageView5);
        board[0][5] = findViewById(R.id.imageView6);
        board[0][6] = findViewById(R.id.imageView7);
        board[0][7] = findViewById(R.id.imageView8);
        board[0][8] = findViewById(R.id.imageView9);
        board[0][9] = findViewById(R.id.imageView10);
        board[0][10] = findViewById(R.id.imageView11);
        board[0][11] = findViewById(R.id.imageView12);
        board[0][12] = findViewById(R.id.imageView13);

        // Row 1
        board[1][0] = findViewById(R.id.imageView14);
        board[1][1] = findViewById(R.id.imageView15);
        board[1][2] = findViewById(R.id.imageView16);
        board[1][3] = findViewById(R.id.imageView17);
        board[1][4] = findViewById(R.id.imageView18);
        board[1][5] = findViewById(R.id.imageView19);
        board[1][6] = findViewById(R.id.imageView20);
        board[1][7] = findViewById(R.id.imageView21);
        board[1][8] = findViewById(R.id.imageView22);
        board[1][9] = findViewById(R.id.imageView23);
        board[1][10] = findViewById(R.id.imageView24);
        board[1][11] = findViewById(R.id.imageView25);
        board[1][12] = findViewById(R.id.imageView26);

        // Row 2
        board[2][0] = findViewById(R.id.imageView27);
        board[2][1] = findViewById(R.id.imageView28);
        board[2][2] = findViewById(R.id.imageView29);
        board[2][3] = findViewById(R.id.imageView30);
        board[2][4] = findViewById(R.id.imageView31);
        board[2][5] = findViewById(R.id.imageView32);
        board[2][6] = findViewById(R.id.imageView33);
        board[2][7] = findViewById(R.id.imageView34);
        board[2][8] = findViewById(R.id.imageView35);
        board[2][9] = findViewById(R.id.imageView36);
        board[2][10] = findViewById(R.id.imageView37);
        board[2][11] = findViewById(R.id.imageView38);
        board[2][12] = findViewById(R.id.imageView39);

        // Row 3
        board[3][0] = findViewById(R.id.imageView40);
        board[3][1] = findViewById(R.id.imageView41);
        board[3][2] = findViewById(R.id.imageView42);
        board[3][3] = findViewById(R.id.imageView43);
        board[3][4] = findViewById(R.id.imageView44);
        board[3][5] = findViewById(R.id.imageView45);
        board[3][6] = findViewById(R.id.imageView46);
        board[3][7] = findViewById(R.id.imageView47);
        board[3][8] = findViewById(R.id.imageView48);
        board[3][9] = findViewById(R.id.imageView49);
        board[3][10] = findViewById(R.id.imageView50);
        board[3][11] = findViewById(R.id.imageView51);
        board[3][12] = findViewById(R.id.imageView52);

        // Row 4
        board[4][0] = findViewById(R.id.imageView53);
        board[4][1] = findViewById(R.id.imageView54);
        board[4][2] = findViewById(R.id.imageView55);
        board[4][3] = findViewById(R.id.imageView56);
        board[4][4] = findViewById(R.id.imageView57);
        board[4][5] = findViewById(R.id.imageView58);
        board[4][6] = findViewById(R.id.imageView59);
        board[4][7] = findViewById(R.id.imageView60);
        board[4][8] = findViewById(R.id.imageView61);
        board[4][9] = findViewById(R.id.imageView62);
        board[4][10] = findViewById(R.id.imageView63);
        board[4][11] = findViewById(R.id.imageView64);
        board[4][12] = findViewById(R.id.imageView65);

        // Row 5
        board[5][0] = findViewById(R.id.imageView66);
        board[5][1] = findViewById(R.id.imageView67);
        board[5][2] = findViewById(R.id.imageView68);
        board[5][3] = findViewById(R.id.imageView69);
        board[5][4] = findViewById(R.id.imageView70);
        board[5][5] = findViewById(R.id.imageView71);
        board[5][6] = findViewById(R.id.imageView72);
        board[5][7] = findViewById(R.id.imageView73);
        board[5][8] = findViewById(R.id.imageView74);
        board[5][9] = findViewById(R.id.imageView75);
        board[5][10] = findViewById(R.id.imageView76);
        board[5][11] = findViewById(R.id.imageView77);
        board[5][12] = findViewById(R.id.imageView78);

        // Row 6
        board[6][0] = findViewById(R.id.imageView79);
        board[6][1] = findViewById(R.id.imageView80);
        board[6][2] = findViewById(R.id.imageView81);
        board[6][3] = findViewById(R.id.imageView82);
        board[6][4] = findViewById(R.id.imageView83);
        board[6][5] = findViewById(R.id.imageView84);
        board[6][6] = findViewById(R.id.imageView85);
        board[6][7] = findViewById(R.id.imageView86);
        board[6][8] = findViewById(R.id.imageView87);
        board[6][9] = findViewById(R.id.imageView88);
        board[6][10] = findViewById(R.id.imageView89);
        board[6][11] = findViewById(R.id.imageView90);
        board[6][12] = findViewById(R.id.imageView91);

        // Row 7
        board[7][0] = findViewById(R.id.imageView92);
        board[7][1] = findViewById(R.id.imageView93);
        board[7][2] = findViewById(R.id.imageView94);
        board[7][3] = findViewById(R.id.imageView95);
        board[7][4] = findViewById(R.id.imageView96);
        board[7][5] = findViewById(R.id.imageView97);
        board[7][6] = findViewById(R.id.imageView98);
        board[7][7] = findViewById(R.id.imageView99);
        board[7][8] = findViewById(R.id.imageView100);
        board[7][9] = findViewById(R.id.imageView101);
        board[7][10] = findViewById(R.id.imageView102);
        board[7][11] = findViewById(R.id.imageView103);
        board[7][12] = findViewById(R.id.imageView104);

        // Row 8
        board[8][0] = findViewById(R.id.imageView105);
        board[8][1] = findViewById(R.id.imageView106);
        board[8][2] = findViewById(R.id.imageView107);
        board[8][3] = findViewById(R.id.imageView108);
        board[8][4] = findViewById(R.id.imageView109);
        board[8][5] = findViewById(R.id.imageView110);
        board[8][6] = findViewById(R.id.imageView111);
        board[8][7] = findViewById(R.id.imageView112);
        board[8][8] = findViewById(R.id.imageView113);
        board[8][9] = findViewById(R.id.imageView114);
        board[8][10] = findViewById(R.id.imageView115);
        board[8][11] = findViewById(R.id.imageView116);
        board[8][12] = findViewById(R.id.imageView117);

        // Row 9
        board[9][0] = findViewById(R.id.imageView118);
        board[9][1] = findViewById(R.id.imageView119);
        board[9][2] = findViewById(R.id.imageView120);
        board[9][3] = findViewById(R.id.imageView121);
        board[9][4] = findViewById(R.id.imageView122);
        board[9][5] = findViewById(R.id.imageView123);
        board[9][6] = findViewById(R.id.imageView124);
        board[9][7] = findViewById(R.id.imageView125);
        board[9][8] = findViewById(R.id.imageView126);
        board[9][9] = findViewById(R.id.imageView127);
        board[9][10] = findViewById(R.id.imageView128);
        board[9][11] = findViewById(R.id.imageView129);
        board[9][12] = findViewById(R.id.imageView130);

        // Row 10
        board[10][0] = findViewById(R.id.imageView131);
        board[10][1] = findViewById(R.id.imageView132);
        board[10][2] = findViewById(R.id.imageView133);
        board[10][3] = findViewById(R.id.imageView134);
        board[10][4] = findViewById(R.id.imageView135);
        board[10][5] = findViewById(R.id.imageView136);
        board[10][6] = findViewById(R.id.imageView137);
        board[10][7] = findViewById(R.id.imageView138);
        board[10][8] = findViewById(R.id.imageView139);
        board[10][9] = findViewById(R.id.imageView140);
        board[10][10] = findViewById(R.id.imageView141);
        board[10][11] = findViewById(R.id.imageView142);
        board[10][12] = findViewById(R.id.imageView143);

        // Row 11
        board[11][0] = findViewById(R.id.imageView144);
        board[11][1] = findViewById(R.id.imageView145);
        board[11][2] = findViewById(R.id.imageView146);
        board[11][3] = findViewById(R.id.imageView147);
        board[11][4] = findViewById(R.id.imageView148);
        board[11][5] = findViewById(R.id.imageView149);
        board[11][6] = findViewById(R.id.imageView150);
        board[11][7] = findViewById(R.id.imageView151);
        board[11][8] = findViewById(R.id.imageView152);
        board[11][9] = findViewById(R.id.imageView153);
        board[11][10] = findViewById(R.id.imageView154);
        board[11][11] = findViewById(R.id.imageView155);
        board[11][12] = findViewById(R.id.imageView156);

        // Row 12
        board[12][0] = findViewById(R.id.imageView157);
        board[12][1] = findViewById(R.id.imageView158);
        board[12][2] = findViewById(R.id.imageView159);
        board[12][3] = findViewById(R.id.imageView160);
        board[12][4] = findViewById(R.id.imageView161);
        board[12][5] = findViewById(R.id.imageView162);
        board[12][6] = findViewById(R.id.imageView163);
        board[12][7] = findViewById(R.id.imageView164);
        board[12][8] = findViewById(R.id.imageView165);
        board[12][9] = findViewById(R.id.imageView166);
        board[12][10] = findViewById(R.id.imageView167);
        board[12][11] = findViewById(R.id.imageView168);
        board[12][12] = findViewById(R.id.imageView169);

        // Row 13
        board[13][0] = findViewById(R.id.imageView170);
        board[13][1] = findViewById(R.id.imageView171);
        board[13][2] = findViewById(R.id.imageView172);
        board[13][3] = findViewById(R.id.imageView173);
        board[13][4] = findViewById(R.id.imageView174);
        board[13][5] = findViewById(R.id.imageView175);
        board[13][6] = findViewById(R.id.imageView176);
        board[13][7] = findViewById(R.id.imageView177);
        board[13][8] = findViewById(R.id.imageView178);
        board[13][9] = findViewById(R.id.imageView179);
        board[13][10] = findViewById(R.id.imageView180);
        board[13][11] = findViewById(R.id.imageView181);
        board[13][12] = findViewById(R.id.imageView182);

        // Row 14
        board[14][0] = findViewById(R.id.imageView183);
        board[14][1] = findViewById(R.id.imageView184);
        board[14][2] = findViewById(R.id.imageView185);
        board[14][3] = findViewById(R.id.imageView186);
        board[14][4] = findViewById(R.id.imageView187);
        board[14][5] = findViewById(R.id.imageView188);
        board[14][6] = findViewById(R.id.imageView189);
        board[14][7] = findViewById(R.id.imageView190);
        board[14][8] = findViewById(R.id.imageView191);
        board[14][9] = findViewById(R.id.imageView192);
        board[14][10] = findViewById(R.id.imageView193);
        board[14][11] = findViewById(R.id.imageView194);
        board[14][12] = findViewById(R.id.imageView195);

        // Row 15
        board[15][0] = findViewById(R.id.imageView196);
        board[15][1] = findViewById(R.id.imageView197);
        board[15][2] = findViewById(R.id.imageView198);
        board[15][3] = findViewById(R.id.imageView199);
        board[15][4] = findViewById(R.id.imageView200);
        board[15][5] = findViewById(R.id.imageView201);
        board[15][6] = findViewById(R.id.imageView202);
        board[15][7] = findViewById(R.id.imageView203);
        board[15][8] = findViewById(R.id.imageView204);
        board[15][9] = findViewById(R.id.imageView205);
        board[15][10] = findViewById(R.id.imageView206);
        board[15][11] = findViewById(R.id.imageView207);
        board[15][12] = findViewById(R.id.imageView208);

        // Initializing the board to show blank slate
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 13; j++) {
                board[i][j].setTag(-1);
            }
        }
        player1Squares = 0;
        player2Squares = 0;
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("game/" + p1);
                addEventListener();
                if (player1 && !prevP1Move.equals("leftP1")) {
                    myRef.setValue("leftP1");
                    //prevMove = "leftP1";
                }
                if (player1 == false && !prevP2Move.equals("leftP2")) {
                    myRef.setValue("leftP2");
                    //prevMove = "leftP2";
                }
            }
        });
        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("game/" + p1);
                addEventListener();
                if (player1 == true && !prevP1Move.equals("downP1")) {
                    myRef.setValue("downP1");
                }
                if (player1 == false && !prevP2Move.equals("downP2")) {
                    myRef.setValue("downP2");
                    //prevMove = "downP2";
                }
            }
        });
        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("game/" + p1);
                addEventListener();
                if (player1 == true && !prevP1Move.equals("upP1")) {
                    myRef.setValue("upP1");
                    //prevMove = "upP1";
                }
                if (player1 == false && !prevP2Move.equals("upP2")) {
                    myRef.setValue("upP2");
                    //prevMove = "upP2";
                }
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("game/" + p1);
                addEventListener();
                if (player1 == true && !prevP1Move.equals("rightP1")) {
                    myRef.setValue("rightP1");
                    //prevMove = "rightP1";
                }
                if (player1 == false && !prevP2Move.equals("rightP2")) {
                    myRef.setValue("rightP2");
                    //prevMove = "rightP2";
                }
            }
        });


        myRef = database.getReference("game/" + p1);
        //System.out.println(myRef.value);
        addEventListener();
        //player = true;
        myRef.setValue("BRUH");
        countdown = findViewById(R.id.countdown);

        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                long sec = (millisUntilFinished / 1000) % 60;
                countdown.setText("00:00:" + sec);
            }

            public void onFinish() {
                String winner = "";
                countdown.setText("00:00:00");
                if (player1Squares > player2Squares) {
                    winner = "PLAYER 1 :)";
                }
                else if(player1Squares == player2Squares) {
                    winner = "BOTH PLAYERS :)";
                }else {
                    winner = "PLAYER 2 :)";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(Paint_Game.this);
                dialog.setTitle("Game Over!! The winner was.... " + winner)
                        .setIcon(R.drawable.ic_launcher_background)
                        .setMessage("Would you like to play a new game?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                Intent intent = new Intent(Paint_Game.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).show();

            }
        }.start();

}

    /*public void goLeft(View v) {
        int leftBound;
        if(player1)
        {
            board[2][2].setImageResource(player2SelectedColor);
        }
        if(player1 == false)
        {
            board[2][2].setImageResource(player2SelectedColor);
        }

        // player 1 chose to go left
        if(player1) {
            for (int i = 15; i >= 0; i--) {
                leftBound = (13 * i) + 1;
                if((player1position - 1) == leftBound) {
                    board[i][0].setImageResource(player1SelectedColor);
                    player1position = leftBound;
                    break;
                }

                if(((player1position - 1) > leftBound)) {
                    int column = (player1position - 1) - leftBound;
                    board[i][column].setImageResource(player1SelectedColor);
                    player1position--;
                }
            }
        }
    }*/
    /*public void goDown (View v) {
        if(player1) {
            if(player1position < 196) {
                int row = player1position / 13;
                int col = player1position % 13;
                board[row + 1][col].setImageResource(player1SelectedColor);
                player1position += 13;
                player1Squares++;
            }
            else {
                Toast.makeText(this, "Cannot go down more", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
    private void addEventListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.println("Player 1's Score: " + player1Squares);
                System.out.println("Player 2's Score: " + player2Squares);
                if(value.equals("downP1"))
                {
                    if(!prevP1Move.equals("downP1"))
                    {
                        System.out.println("RUNS!!!");
                        if(player1position < 196) {
                            int row = player1position / 13;
                            int col = player1position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player1SelectedColor);
                            // new position
                            ImageView newSquare = board[row + 1][col - 1];
                            newSquare.setImageResource(player1Head);
                            player1position += 13;

                            if((int) newSquare.getTag() == -1) {
                                player1Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 2) {
                                player1Squares += 1;
                                player2Squares -= 1;
                            }
                            newSquare.setTag(1);
                        }
                        else {
                            Toast.makeText(Paint_Game.this, "Cannot go down more", Toast.LENGTH_SHORT).show();
                        }
                        //myRef.setValue("noMove");
                    }
                    prevP1Move = "downP1";
                    //board[2][2].setImageResource(player1SelectedColor);
                    /*if(player1position < 196) {
                        int row = player1position / 13;
                        int col = player1position % 13;
                        board[row][col - 1].setImageResource(player1SelectedColor);
                        board[row + 1][col - 1].setImageResource(player1Head);
                        player1position += 13;
                        player1Squares++;
                    }
                    else {
                        Toast.makeText(Paint_Game.this, "Cannot go down more", Toast.LENGTH_SHORT).show();
                    }*/

                }
                if(value.equals("downP2"))
                {
                    //board[2][2].setImageResource(player2SelectedColor);
                    if(!prevP2Move.equals("downP2"))
                    {
                        if(player2position < 196) {
                            int row = player2position / 13;
                            int col = player2position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player2SelectedColor);
                            // new position
                            ImageView newSquare = board[row + 1][col - 1];
                            newSquare.setImageResource(player2Head);
                            player2position += 13;

                            if((int) newSquare.getTag() == -1) {
                                player2Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 1) {
                                player2Squares += 1;
                                player1Squares -= 1;
                            }
                            newSquare.setTag(2);
                        }
                        else {
                            Toast.makeText(Paint_Game.this, "Cannot go down more", Toast.LENGTH_SHORT).show();
                        }
                    }
                    prevP2Move = "downP2";
                    //myRef.setValue("noMove");
                }
                if(value.equals("upP2"))
                {
                    //board[2][2].setImageResource(player2SelectedColor);
                    if(!prevP2Move.equals("upP2"))
                    {
                        if(player2position > 13) {
                            int row = player2position / 13;
                            int col = player2position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player2SelectedColor);
                            // new position
                            ImageView newSquare = board[row - 1][col - 1];
                            newSquare.setImageResource(player2Head);
                            player2position -= 13;
                            if((int) newSquare.getTag() == -1) {
                                player2Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 1) {
                                player2Squares += 1;
                                player1Squares -= 1;
                            }
                            newSquare.setTag(2);
                        }
                        else {
                            Toast.makeText(Paint_Game.this, "Cannot go down more", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //myRef.setValue("noMove");
                    prevP2Move = "upP2";
                }
                if(value.equals("upP1"))
                {
                    //board[2][2].setImageResource(player2SelectedColor);
                    //myRef.setValue("noMove");
                    if(!prevP1Move.equals("upP1"))
                    {
                        if(player1position > 13) {
                            int row = player1position / 13;
                            int col = player1position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player1SelectedColor);
                            // new position
                            ImageView newSquare = board[row - 1][col - 1];
                            newSquare.setImageResource(player1Head);
                            player1position -= 13;

                            if((int) newSquare.getTag() == -1) {
                                player1Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 2) {
                                player1Squares += 1;
                                player2Squares -= 1;
                            }
                            newSquare.setTag(1);
                        }
                        else {
                            Toast.makeText(Paint_Game.this, "Cannot go down more", Toast.LENGTH_SHORT).show();
                        }
                    }
                    prevP1Move = "upP1";
                }
                if(value.equals("rightP1"))
                {
                    if(!prevP1Move.equals("rightP1"))
                    {
                        int rightBound = 0;
                        int row = 0;
                        int col = 0;
                        boolean error = false;
                        System.out.println("Player Pos: " + player1position);
                        for(int i = 1; i <= 16; i++) {
                            rightBound = (13 * i);
                            if(player1position == rightBound) {
                                Toast.makeText(Paint_Game.this, "Cannot go to the right anymore", Toast.LENGTH_SHORT).show();
                                error = true;
                                break;
                            }
                        }
                        if(!error) {
                            row = player1position / 13;
                            col = player1position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player1SelectedColor);
                            ImageView newSquare = board[row][col];
                            newSquare.setImageResource(player1Head);
                            player1position += 1;
                            if((int) newSquare.getTag() == -1) {
                                player1Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 2) {
                                player1Squares += 1;
                                player2Squares -= 1;
                            }
                            newSquare.setTag(1);
                        }
                    }
                    prevP1Move = "rightP1";
                }
                if(value.equals("rightP2"))
                {
                    if(!prevP2Move.equals("rightP2"))
                    {
                        int rightBound = 0;
                        int row = 0;
                        int col = 0;
                        boolean error = false;
                        for(int i = 1; i <= 16; i++) {
                            rightBound = (13 * i);
                            if(player2position == rightBound) {
                                Toast.makeText(Paint_Game.this, "Cannot go to the right anymore", Toast.LENGTH_SHORT).show();
                                error = true;
                                break;
                            }
                        }
                        if(!error) {
                            row = player2position / 13;
                            col = player2position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player2SelectedColor);
                            ImageView newSquare = board[row][col];
                            newSquare.setImageResource(player2Head);
                            player2position += 1;
                            if((int) newSquare.getTag() == -1) {
                                player2Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 1) {
                                player2Squares += 1;
                                player1Squares -= 1;
                            }
                            newSquare.setTag(2);
                        }
                    }

                    prevP2Move = "rightP2";
                }
                if(value.equals("leftP1"))
                {
                    if(!prevP1Move.equals("leftP1"))
                    {
                        int leftBound = 0;
                        int row = 0;
                        int col = 0;
                        boolean error = false;
                        for(int i = 0; i < 16; i++) {
                            leftBound = (13 * i) + 1;
                            if(player1position == leftBound) {
                                Toast.makeText(Paint_Game.this, "Cannot go to the left anymore", Toast.LENGTH_SHORT).show();
                                error = true;
                                break;
                            }
                        }
                        if(!error) {
                            row = player1position / 13;
                            col = player1position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player1SelectedColor);
                            ImageView newSquare = board[row][col - 2];
                            newSquare.setImageResource(player1Head);
                            player1position -= 1;
                            if((int) newSquare.getTag() == -1) {
                                player1Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 2) {
                                player1Squares += 1;
                                player2Squares -= 1;
                            }
                            newSquare.setTag(1);
                        }
                    }
                    prevP1Move = "leftP1";
                }
                if(value.equals("leftP2"))
                {
                    if(!prevP2Move.equals("leftP2"))
                    {
                        int leftBound = 0;
                        int row = 0;
                        int col = 0;
                        boolean error = false;
                        for(int i = 0; i < 16; i++) {
                            leftBound = (13 * i) + 1;
                            if(player2position == leftBound) {
                                Toast.makeText(Paint_Game.this, "Cannot go to the left anymore", Toast.LENGTH_SHORT).show();
                                error = true;
                                break;
                            }
                        }
                        if(!error) {
                            row = player2position / 13;
                            col = player2position % 13;
                            if(col == 0)
                            {
                                row -= 1;
                                col = 13;
                            }
                            // current position
                            board[row][col - 1].setImageResource(player2SelectedColor);
                            ImageView newSquare = board[row][col - 2];
                            newSquare.setImageResource(player2Head);
                            player2position -= 1;
                            if((int) newSquare.getTag() == -1) {
                                player2Squares += 1;
                            }
                            else if((int) newSquare.getTag() == 1) {
                                player2Squares += 1;
                                player1Squares -= 1;
                            }
                            newSquare.setTag(2);
                        }
                    }
                    prevP2Move = "leftP2";
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