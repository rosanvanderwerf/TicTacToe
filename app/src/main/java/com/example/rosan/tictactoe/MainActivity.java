package com.example.rosan.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity {

    Game game;
    Integer row;
    Integer column;

    TextView display;
    TextView count;
    TextView player1;
    TextView player2;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        display = findViewById(R.id.display);
        count = findViewById(R.id.count);

        // There were for the cooler version
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // Game game_saved = game;
        String player_saved = display.getText().toString();
        String count_saved = count.getText().toString();
        String p1_saved = player1.getText().toString();
        String p2_saved = player2.getText().toString();

        String button1_saved = button1.getText().toString();
        String button2_saved = button2.getText().toString();
        String button3_saved = button3.getText().toString();
        String button4_saved = button4.getText().toString();
        String button5_saved = button5.getText().toString();
        String button6_saved = button6.getText().toString();
        String button7_saved = button7.getText().toString();
        String button8_saved = button8.getText().toString();
        String button9_saved = button9.getText().toString();

        outState.putString("player_s", player_saved);
        outState.putString("count_s", count_saved);
        outState.putString("p1_saved", p1_saved);
        outState.putString("p2_saved", p2_saved);

        outState.putString("1s", button1_saved);
        outState.putString("2s", button2_saved);
        outState.putString("3s", button3_saved);
        outState.putString("4s", button4_saved);
        outState.putString("5s", button5_saved);
        outState.putString("6s", button6_saved);
        outState.putString("7s", button7_saved);
        outState.putString("8s", button8_saved);
        outState.putString("9s", button9_saved);

        // Couldn't save the board, so when the orientation is changed: The layout changes but
        // the board does not. I had not enough time to fix this, unfortunately
    }

    @Override
    public void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);

        String player_restored = inState.getString("player_s");
        String count_restored = inState.getString("count_s");
        String p1_restored = inState.getString("p1_saved");
        String p2_restored = inState.getString("p2_saved");

        display.setText(player_restored);
        count.setText(count_restored);
        player1.setText(p1_restored);
        player2.setText(p2_restored);

        button1.setText(inState.getString("1s"));
        button2.setText(inState.getString("2s"));
        button3.setText(inState.getString("3s"));
        button4.setText(inState.getString("4s"));
        button5.setText(inState.getString("5s"));
        button6.setText(inState.getString("6s"));
        button7.setText(inState.getString("7s"));
        button8.setText(inState.getString("8s"));
        button9.setText(inState.getString("9s"));
    }

    // Does something when a button in the gridLayout is clicked
    public void tileClicked(View view) {
        int id = view.getId();
        // Store row and column
        if(id==R.id.button){
            row = 0;
            column = 0; }
        if(id==R.id.button2){
            row = 0;
            column = 1; }
        if(id==R.id.button3){
            row = 0;
            column = 2; }
        if(id==R.id.button4){
            row = 1;
            column = 0; }
        if(id==R.id.button5){
            row = 1;
            column = 1; }
        if(id==R.id.button6){
            row = 1;
            column = 2; }
        if(id==R.id.button7){
            row = 2;
            column = 0; }
        if(id==R.id.button8){
            row = 2;
            column = 1; }
        if(id==R.id.button9){
            row = 2;
            column = 2; }

        // What to be done with the tile
        Tile tile = game.draw(row, column);

        Button button = findViewById(id);
        Button reset = findViewById(R.id.button10);

        // Based on the draw method, UI is changed
        switch(tile) {
            case CROSS:
                button.setText("X");
                display.setText(R.string.player2);
                count.setText("Turns: " + String.valueOf(game.getMoves()));
                if (game.gameState() != GameState.IN_PROGRESS){
                    Toast.makeText(MainActivity.this, "Player 1 won!",Toast.LENGTH_SHORT).show();
                    //player1.setText("P1: "+game.player1_wins());
                    resetAuto(reset);

                }
                break;
            case CIRCLE:
               button.setText("O");
                display.setText("Player 1 (X)");
                count.setText("Turns: " + String.valueOf(game.getMoves()));
                if (game.gameState() != GameState.IN_PROGRESS){
                    Toast.makeText(MainActivity.this, "Player 2 won!",Toast.LENGTH_SHORT).show();
                    //player2.setText("P2: "+game.player2_wins());
                    resetAuto(reset);

                }
               break;
            case INVALID:
                Toast.makeText(MainActivity.this, "Sorry, choose another tile.",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        // When tic tac toe
        GameState state = game.gameState();


        // Change UI according to different GameState(s)
        switch (state) {
            case PLAYER_ONE:
                //player1.setText(game.player1_wins());
                Toast.makeText(MainActivity.this, "Sorry, choose another tile.",
                        Toast.LENGTH_SHORT).show();
                break;
            case PLAYER_TWO:
                //player2.setText(game.player2_wins());
                break;
            case DRAW:
                break;
        }
    }

    // Does something when the reset button is clicked
    public void resetAuto(View view) {
        game = new Game();

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        //player1.setText("");
        //player2.setText("");
        count.setText("");
        display.setText("Player 1 (X)");
    }

    public void resetClicked(View view) {
        game = new Game();

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        player1.setText("P1: 0");
        player2.setText("P2: 0");
        count.setText("");
        display.setText("Player 1 (X)");
    }

    // Actually UpdateUI method to change tiles according to board (couldn't do it)
}
