package com.royal.androidgentuefri;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TicTacToeActivity extends AppCompatActivity {

    TextView tvPlayerName;

    ImageButton imgBtn[] = new ImageButton[9];
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

        tvPlayerName.setText("Player 1");

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
            imgBtn[i].setOnClickListener(this::play);
        }


    }

    private void play(View view) {
        ImageButton btnClick = findViewById(view.getId());
        btnClick.setBackground(getDrawable(R.drawable.cloud_fill_24));
    }
}