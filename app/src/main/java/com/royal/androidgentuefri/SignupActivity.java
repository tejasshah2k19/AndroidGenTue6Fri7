package com.royal.androidgentuefri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SignupActivity extends AppCompatActivity {


    EditText edtFirstName;
    EditText edtEmail;
    EditText edtPassword;
    Button btnSubmit;

    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //bind
        edtFirstName = findViewById(R.id.edtSignupFirstName);
        edtEmail = findViewById(R.id.edtSignupEmail);
        edtPassword = findViewById(R.id.edtSignupPassword);
        btnSubmit = findViewById(R.id.btnSignupSubmit);
        tvLogin = findViewById(R.id.tvSignupLogin);



        //signup click ->
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //read
                String firstName= edtFirstName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                boolean isError = false;
                //validate
                if(firstName.isEmpty() || firstName.trim().length() < 2){
                    edtFirstName.setError("Firstname is required");
                    isError = true;
                }
                //email
                if(email.isEmpty()){
                    edtFirstName.setError("Email is required");
                    isError = true;
                }
                //password

                if(!isError){
                    //db -> save
                    //fn em pwd-> json
                    //gson

                    try {
                        HashMap<String,Object> jsonObject = new HashMap<>();
                        jsonObject.put("firstName", firstName);
                        jsonObject.put("email", email);
                        jsonObject.put("password", password);
                        jsonObject.put("lastName","NA");

                        Gson gson = new Gson();
                        String jsonData =  gson.toJson(jsonObject);

                        Log.i("api",jsonData);

                        //network
                        //not allow
                        //new thread ->

                       ExecutorService executorService =  Executors.newSingleThreadExecutor();

                    Future<Integer> ft = executorService.submit(new Callable<Integer>() {
                           @Override
                           public Integer call() throws Exception {
                               return signupApiCall(jsonData);
                           }
                       });

                       Integer respCode =  ft.get();

                        if(respCode == 201){
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    // login
                    //mysql pg mongodb
                    //sharedpref

                }

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private Integer signupApiCall(String jsonStr){

        String apiUrl = "https://diamondgame.onrender.com/api/auth/signup";
        //post
        //body:firstname lastname email password

        try {

            URL url = new URL(apiUrl);

            // Open connection
          HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            // Send data
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            os.close();

            int respCode = urlConnection.getResponseCode() ;
            Log.i("api",respCode+"");
            if(respCode == 201){
                return 201;
            }



        }catch(Exception e){
            e.printStackTrace();
        }

        return -1;
    }
}