package com.royal.androidgentuefri;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {


    Button btnPlay, btnScore,btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnPlay = findViewById(R.id.btnMenuPlay);
        btnScore = findViewById(R.id.btnMenuLeaderboard);
        btnLogout  = findViewById(R.id.btnMenuLogout);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
                rotateAnimator.setDuration(500); // Rotation duration in milliseconds
                rotateAnimator.start();

                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(getApplicationContext(),GamePlayActivity.class);
                    startActivity(intent);

                }, 500);
            }
        });
    }
}