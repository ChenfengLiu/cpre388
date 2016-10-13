package example.com.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ListActivity implements DownloadWebpageTask.ResultHandler {
    Button search;
    EditText et;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search);
        et = (EditText) findViewById(R.id.username);
        search.setOnClickListener(new OnClickListener() {

            /* (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            public void onClick(View v) {

                //TODO get the username to search for from the activity_main.xml view

                String artist = et.getText().toString();
                Log.e("Artist Searched", artist);
                String request = "https://itunes.apple.com/search?term="+ Uri.encode(artist)+"&entity=song&limit=20";

                //TODO execute a new DownloadWebpageTask
                DownloadWebpageTask t = new DownloadWebpageTask(MainActivity.this);
                t.execute(request);

            }
        });
    }


    @Override
    public void handleResult(String result) {
        //TODO Handle the Result of a Network Call

        try{
            ArrayList<ItunesRecord> records = new ArrayList<>();
            JSONObject jresult = new JSONObject(result);

            int numRecords = jresult.getInt("resultCount");
            JSONArray jresultArr = jresult.getJSONArray("results");

            for(int i = 0; i< numRecords; i++){
                JSONObject r = (JSONObject) jresultArr.get(i);
                ItunesRecord ir = new ItunesRecord(r.getString("collectionName"), r.getString("trackName"));
                records.add(ir);
            }

            ItunesAdapter ia = new ItunesAdapter(this, R.layout.row, records);
            setListAdapter(ia);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
