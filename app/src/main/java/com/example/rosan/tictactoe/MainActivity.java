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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    // Does something when a button in the gridLayout is clicked
    public void tileClicked(View view) {
        int id = view.getId();
        //Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();

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
        TextView display = findViewById(R.id.display);
        TextView count = findViewById(R.id.count);

        // Based on the draw method, UI is changed
        switch(tile) {
            case CROSS:
                button.setText("X");
                display.setText(R.string.player2);
                count.setText("Turns: " + String.valueOf(game.getMoves()));
                break;
            case CIRCLE:
               button.setText("O");
                display.setText("Player 1 (X)");
                count.setText("Turns: " + String.valueOf(game.getMoves()));
               break;
            case INVALID:
                Toast.makeText(MainActivity.this, "Sorry, choose another tile.",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        // When tic tac toe
        GameState state = game.gameState();

        TextView winner = findViewById(R.id.winner);

        // Change UI according to different GameState(s)
        switch (state) {
            case PLAYER_ONE:
                winner.setText(R.string.player1won);
                break;
            case PLAYER_TWO:
                winner.setText(R.string.player2won);
                break;
            case DRAW:
                winner.setText(R.string.adraw);
        }
    }

    // Does something when the reset button is clicked
    public void resetClicked(View view) {
        game = new Game();
        // Reset the UI, not very nice sorry
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        TextView winner = findViewById(R.id.winner);
        TextView count = findViewById(R.id.count);
        TextView display = findViewById(R.id.display);

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        winner.setText("");
        count.setText("");
        display.setText("Player 1 (X)");
    }

    // Actually UpdateUI method to change tiles according to board (couldn't do it)
}
