package com.example.technoligest.lastdraft;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AddMenu extends AppCompatActivity {
    Button btnAdd;
    EditText productName, numInStock, location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        btnAdd= (Button) findViewById(R.id.button4);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productName = (EditText)findViewById(R.id.editText);
                numInStock  = (EditText)findViewById(R.id.editText2);
                location    = (EditText)findViewById(R.id.editText3);
                try{
                    Integer.parseInt(numInStock.getText().toString());
                    if(location.getText().toString().length()==4) {
                        int a= Integer.parseInt(location.getText().toString().substring(0,2));
                        int b= Integer.parseInt(location.getText().toString().substring(2,4));
                        if(!(a>-1&& a<6 &&b>-1&& b<26))
                            Integer.parseInt("a");


                        Integer.parseInt(location.getText().toString());

                    }
                    else
                        Integer.parseInt("a");

                    addProduct(productName.getText().toString(),numInStock.getText().toString(),location.getText().toString());
                }
                catch(Exception e){
                    Toast.makeText(AddMenu.this, "Could not add a product!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //takes in the values and adds the item to the list in the database
    protected void addProduct(String name, String back, String location) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ooper.space/VoltaHackathon2016/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final IjsonQueries zaherInterface = retrofit.create(IjsonQueries.class);
        Call<ResponseBody> call = zaherInterface.add("insert", "wordpass", name, back, location);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Toast.makeText(AddMenu.this, "Product Added!", Toast.LENGTH_LONG).show();
                    Log.d("Message", "Sucess");
                    backtToMain();

                } else
                    Log.d("Message", "falure" + response.body());
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    //go back to main menu
    protected void backtToMain(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
