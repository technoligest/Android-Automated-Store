package com.example.technoligest.lastdraft;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    //call the different methods depending on the button clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addrow:
                addRowActivity();
                return true;
            case R.id.refresh:
                getData();
                Toast.makeText(MainActivity.this, "Refreshed List!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.restock:
                restock();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void addRowActivity() {
        Intent intent=new Intent(this,AddMenu.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_screen_menu, menu);
        return true;
    }

    //get the data off of the server and then update the listview
    public void getData() {
        try{
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ooper.space/VoltaHackathon2016/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            final IjsonQueries zaherInterface = retrofit.create(IjsonQueries.class);
            Call<List<Product>> call = zaherInterface.api("refresh", "wordpass");
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        Log.d("Message", "Sucess");
                        bindListView(sort((ArrayList)response.body()));

                    } else
                        Log.d("Message", "falure" + response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                    Log.d("Message", "failure" + t.getMessage());
                }
            });}
        catch (Exception e){
            Log.d("Exception",e.getMessage());
        }
    }

    //send the command to the server to restock the front, and then update the list
    public void restock() {
        try{
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ooper.space/VoltaHackathon2016/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            final IjsonQueries zaherInterface = retrofit.create(IjsonQueries.class);
            Call<List<Product>> call = zaherInterface.api("restock", "wordpass");
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        Log.d("Message", "Sucess");
                        Toast.makeText(MainActivity.this, "Command sent to robots!", Toast.LENGTH_LONG).show();
                        getData();
                    } else
                        Log.d("Message", "falure" + response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                    Log.d("Message", "failure" + t.getMessage());
                }
            });}
        catch (Exception e){
            Log.d("Exception",e.getMessage());
        }
    }

    //refresh the listview on the app
    private  void bindListView(ArrayList<Product> l)
    {
       ProductListAdapter productListAdapter=new ProductListAdapter(MainActivity.this,l);
        ListView lvBanaList= (ListView) findViewById(R.id.BananaList);
        lvBanaList.setAdapter(productListAdapter);
    }

    //take in an arraylist and sort them according to the number in the front
    public ArrayList<Product> sort(ArrayList<Product> list){
        int n =list.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (Integer.parseInt(list.get(i).getFront()) > Integer.parseInt(list.get(k).getFront())) {
                    Product temp;
                    temp = list.get(i);
                    list.set(i, list.get(k));
                    list.set(k, temp);
                }
            }
        }
        return list;
    }
}
