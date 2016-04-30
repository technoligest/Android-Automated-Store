package com.zackrooney.bloopbot;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zackrooney on 2016-04-29.
 */
public class MainActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        listView = (ListView) findViewById(R.id.BananaList);


        String[] values = {"Apples", "Bananas", "Cereal", "Bread", "Oreos", "Red Bull"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, bananas.class));
            }

        });
    }


    /* final ListView listview = (ListView) findViewById(R.id.listview);
     String[] values = new String[] { "Apples", "Bananas", "Cereal", "Bread", "Oreos", "Red Bull" };

     final ArrayList<String> list = new ArrayList<String>();
     for (int i = 0; i < values.length; ++i) {
         list.add(values[i]);
     }
     final StableArrayAdapter adapter = new StableArrayAdapter(this,
             android.R.layout.simple_list_item_1, list);
     listview.setAdapter(adapter);

     listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

         @Override
         public void onItemClick(AdapterView<?> parent, final View view,
         int position, long id) {
             final String item = (String) parent.getItemAtPosition(position);
             view.animate().setDuration(2000).alpha(0)
                     .withEndAction(new Runnable() {
                         @Override
                         public void run() {
                             list.remove(item);
                             adapter.notifyDataSetChanged();
                             view.setAlpha(1);
                         }
                     });
         }

     });
 }

 private class StableArrayAdapter extends ArrayAdapter<String> {

     HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

     public StableArrayAdapter(Context context, int textViewResourceId,
                               List<String> objects) {
         super(context, textViewResourceId, objects);
         for (int i = 0; i < objects.size(); ++i) {
             mIdMap.put(objects.get(i), i);
         }
     }

     @Override
     public long getItemId(int position) {
         String item = getItem(position);
         return mIdMap.get(item);
     }

     @Override
     public boolean hasStableIds() {
         return true;
     }

 */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.restock) {
           //wait for more information
        }

        if (id == R.id.addrow) {

        }

        return super.onOptionsItemSelected(item);
    }

}
//}


