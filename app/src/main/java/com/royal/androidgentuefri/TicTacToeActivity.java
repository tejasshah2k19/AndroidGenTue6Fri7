package com.royal.androidgentuefri;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TicTacToeActivity extends AppCompatActivity {

    TextView tvPlayerName;
    Integer player = 1;
    ImageButton imgBtn[] = new ImageButton[9];

    Integer gameState [] = new Integer[9];

    Integer gameCounter = 0;


    Integer winningPattern [][] = {
            {0,1,2},{3,4,5},{6,7,8}
            ,
            {0,3,6},{1,4,7},{2,5,8}
            ,
            {0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tic_tac_toe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvPlayerName = findViewById(R.id.tvTicTacToePlayerName);

        tvPlayerName.setText("Player "+player);

        imgBtn[0] = findViewById(R.id.imgBtn1);
        imgBtn[1] = findViewById(R.id.imgBtn2);
        imgBtn[2] = findViewById(R.id.imgBtn3);
        imgBtn[3] = findViewById(R.id.imgBtn4);
        imgBtn[4] = findViewById(R.id.imgBtn5);
        imgBtn[5] = findViewById(R.id.imgBtn6);
        imgBtn[6] = findViewById(R.id.imgBtn7);
        imgBtn[7] = findViewById(R.id.imgBtn8);
        imgBtn[8] = findViewById(R.id.imgBtn9);

        for(int i=0;i<imgBtn.length;i++){
            int finalI = i;
            imgBtn[i].setOnClickListener(view->play(view, finalI +1));
        }


    }

    private void play(View view,int buttonNumber) {
        ImageButton btnClick = findViewById(view.getId());


        if(btnClick.getBackground().toString().contains("Ripple")){

            gameCounter++;


            if(player == 1){

                gameState[buttonNumber-1]=0;
                player=2;
                btnClick.setBackground(getDrawable(R.drawable.circle));
                tvPlayerName.setText("Player "+player);
            }else if(player ==2 ){
                player =1;
                gameState[buttonNumber-1]=1;
                btnClick.setBackground(getDrawable(R.drawable.cross));
                tvPlayerName.setText("Player "+player);

            }

            if(gameCounter > 4){
                Integer status = checkForWinner();
                if(status == 1){
                    //winner
                    Toast.makeText(getApplicationContext(), "Player 2 Win the Game", Toast.LENGTH_SHORT).show();
                }else if(status == 0){
                    Toast.makeText(getApplicationContext(), "Player 1 Win the Game", Toast.LENGTH_SHORT).show();

                 }else if(status == -1){
                    //draw
                    Toast.makeText(getApplicationContext(), "DRAW", Toast.LENGTH_SHORT).show();
                }else if(status == 2){
                    //continue
                }

                if(status != 2 ){
                    Dialog dialog = new Dialog(this);
                    dialog.setTitle("Game Finish");
                    dialog.setContentView(R.layout.dialog_game);
                    dialog.show();
                }
            }
        }

    }

    private Integer checkForWinner(){

        for(int i=0,j=0;i<9;i=i+3,j++) {
            //0 1 2
            //3 4 5
            //6 7 8
            if (gameState[i] == gameState[i+1] && gameState[i+1] == gameState[i+2]) {
                return gameState[i];//0 1
            }

            if (gameState[j] == gameState[j+3] && gameState[j+3] == gameState[j+6]) {
                return gameState[j];//0 1
            }


        }
        if(gameState[0] == gameState[4] &&  gameState[4] == gameState[8]){
            return gameState[0];
        }
        if(gameState[2] == gameState[4] && gameState[4] == gameState[6]){
            return gameState[2];
        }
        if(gameCounter == 9){
            return -1; //draw
        }
        //win? return 1

        return 2;//no winner
    }

}