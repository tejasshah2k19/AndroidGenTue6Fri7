package com.royal.androidgentuefri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddProductActivity extends AppCompatActivity {
    //img 3edit 1tv 1buto

    Button btnSubmit;

    EditText edtName;
    EditText edtQty;
    EditText edtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

    //  bind
        btnSubmit = findViewById(R.id.btnAddProductSubmit); //R  //binding
        edtName = findViewById(R.id.edtAddProductName);
        edtPrice = findViewById(R.id.edtAddProductPrice);
        edtQty = findViewById(R.id.edtAddProductQty);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Log.i("addproduct","button clicked.....");

                   String productName = edtName.getText().toString();
                   String strPrice = edtPrice.getText().toString();//read -> String
                   String strQty = edtQty.getText().toString();//read ->qty

                    Log.i("addproduct",productName);
                    Log.i("addproduct",strPrice);
                    Log.i("addproduct",strQty);

                    //validation
                    if(productName.isEmpty()){
                        edtName.setError("ProductName Required");
                    }

                    if(strQty.isEmpty()){
                        edtQty.setError("Qty Required");
                    }

                    if(strPrice.isEmpty()){
                        edtPrice.setError("Price Required");
                    }


            }
        });


    }
}