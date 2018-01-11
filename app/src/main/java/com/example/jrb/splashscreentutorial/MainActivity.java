package com.example.jrb.splashscreentutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getIntent().getParcelableExtra("parse");
        String jsonData = (String) getIntent().getExtras().get("parse");

        ObjectMapper mapper = new ObjectMapper();

        if (jsonData != null) {
            List topics = new ArrayList<>();


            try {
                JSONArray jArray = new JSONArray(jsonData);

                for (int index = 0; index < jArray.length(); index++) {
                    JSONObject jsonObject = jArray.getJSONObject(index);
                    try {
                        Topic topic = mapper.readValue(jsonObject.toString(), Topic.class);
                        System.out.println(topic.getId());
                        topics.add(topic);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                jArray.length();


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            final ListView list_view = (ListView) findViewById(R.id.list_view);



            String[] id = new String[]{



            };

            final List<String> list_id = new ArrayList<String>(Arrays.asList(id));
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, list_id);
            list_view.setAdapter(arrayAdapter);

            arrayAdapter.notifyDataSetChanged();




        }


    }
}
