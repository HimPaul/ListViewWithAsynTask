package com.example.admin.listviewwithasyntask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private String[] language = {"Java","Abap","Python","Sql","Apex","VisualForce","C#","C++","php","c"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }
    public class MyTask extends AsyncTask<Void,String,String>
    {
        ProgressBar progressBar;
        ArrayAdapter<String> adapter;
        int count;
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            progressBar.setMax(10);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
            count =0;
        }

        @Override
        protected String doInBackground(Void... params) {
            for(String Language : language)
            {
                publishProgress(Language);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "All the Lnaguages are added";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count);

        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);

        }
    }
}

