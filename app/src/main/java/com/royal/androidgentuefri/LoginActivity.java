package com.royal.androidgentuefri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginActivity extends AppCompatActivity {

    Button btnSubmit;

    EditText edtEmail;
    EditText edtPassword;

    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSubmit = findViewById(R.id.btnLoginSubmit);
        edtEmail  =findViewById(R.id.edtLoginEmail);
        edtPassword  = findViewById(R.id.edtLoginPassword);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                //validation

                ExecutorService ex =  Executors.newSingleThreadExecutor();

              Future<Integer> ft =  ex.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return loginApi(email,password);
                    }
                });

            //
                try {
                    Integer respCode = ft.get();

                    if(respCode == 200){
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }//onCreate


    private Integer loginApi(String email,String password){

        String apiUrl = "https://diamondgame.onrender.com/api/auth/login";

        try {

            HashMap<String,Object> map = new HashMap<>();
            map.put("email",email);
            map.put("password",password);


            Gson gson = new Gson() ;
            String jsonStr = gson.toJson(map);


            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            //transfer
            //byte
            //OutputStream

            OutputStream out = connection.getOutputStream();
            out.write(jsonStr.getBytes());

            //inputstream
            Integer respCode = connection.getResponseCode();

            Log.i("api",respCode+"");

            return  respCode;

        } catch (Exception e) {
            Log.i("api",e.getMessage());
        }
        return -1;
     }

}//class