package com.royal.androidgentuefri;

import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.royal.androidgentuefri.fragment.CallFragment;
import com.royal.androidgentuefri.fragment.ChatFragment;
import com.royal.androidgentuefri.fragment.CloudFragment;

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

        Drawable callEmpty = ContextCompat.getDrawable(getApplicationContext(),R.drawable.call_empty_24);
        Drawable callFill = ContextCompat.getDrawable(getApplicationContext(),R.drawable.call_fill_24);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMasterContact,new ChatFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        imgBtnCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtnCloud.setImageDrawable(cloudFill);
                imgBtnChat.setImageDrawable(chatEmpty);
                imgBtnCall.setImageDrawable(callEmpty);

                //how to load fragment

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameMasterContact,new CloudFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        imgBtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtnCloud.setImageDrawable(cloudEmpty);
                imgBtnChat.setImageDrawable(chatFill);
                imgBtnCall.setImageDrawable(callEmpty);


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameMasterContact,new ChatFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imgBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgBtnCall.setImageDrawable(callFill);
                imgBtnCloud.setImageDrawable(cloudEmpty);
                imgBtnChat.setImageDrawable(chatEmpty);


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameMasterContact,new CallFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



    }
}