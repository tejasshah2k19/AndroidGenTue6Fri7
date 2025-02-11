package com.royal.androidgentuefri;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactActivity extends AppCompatActivity {

    ImageButton imgBtnCall;
    ImageButton imgBtnCloud;
    ImageButton imgBtnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgBtnCall = findViewById(R.id.imgBtnCall);
        imgBtnCloud = findViewById(R.id.imgBtnCloud);
        imgBtnChat  = findViewById(R.id.imgBtnChat);

        Drawable cloudFill = ContextCompat.getDrawable(getApplicationContext(),R.drawable.cloud_fill_24);
        Drawable chatEmpty = ContextCompat.getDrawable(getApplicationContext(),R.drawable.chat_outline_24);
        Drawable cloudEmpty = ContextCompat.getDrawable(getApplicationContext(),R.drawable.cloud_empty_24);
        Drawable chatFill  = ContextCompat.getDrawable(getApplicationContext(),R.drawable.chat_bubble_24);
        imgBtnCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtnCloud.setImageDrawable(cloudFill);
                imgBtnChat.setImageDrawable(chatEmpty);
            }
        });

        imgBtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtnCloud.setImageDrawable(cloudEmpty);
                imgBtnChat.setImageDrawable(chatFill);
            }
        });


    }
}