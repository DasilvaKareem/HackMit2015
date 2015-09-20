package com.example.kareemdasilva.mitvideosearch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.widget.VideoView;
import android.net.Uri;
import android.media.*;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.List;
import java.io.File;
import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.ClarifaiRequest;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
//import android.nfc.Tag;
import android.util.Log;
import com.clarifai.api.Tag;


public class VideoSearchActivity extends ActionBarActivity {

    public static VideoView videoPlayer;
    public ListView timeStamps;
    public MediaController control;
    private int position = 0;
    public ArrayAdapter adapter;
    public Uri Path;


    //Setup video player by creating controls/video source
    public void videoSetup() {
        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
//        String data = getIntent().getExtras().getString("path");
        String data = "android.resource://"+getPackageName()+"/raw/bike.jpg";
        ClarifaiClient clarifai = new ClarifaiClient("_xsiBzEiiTTX7tC7D6Gk_E3FeECZEvKdK-jHnHZu", "PNgDmvpiT3juRpf_NqTMKLmSF3ISmNZhKbFsWN8I");
//        List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(new File(data)));
        RecognitionRequest r = new RecognitionRequest("http://www.clarifai.com/img/metro-north.jpg");
        List<RecognitionResult> results = clarifai.recognize(r);

        for (Tag tag : results.get(0).getTags()) {
            Log.d("Clarifai", tag.getName() + ": " + tag.getProbability());
        }


        Path = Uri.parse(getIntent().getExtras().getString("videoPath"));

        //Loads video to the video player
        if (control == null) {

            control= new MediaController(VideoSearchActivity.this);

        }

        videoPlayer.setMediaController(control);


        videoPlayer.setVideoURI(Path);
        control = (MediaController) findViewById(R.id.controller);


        timeStamps = (ListView) findViewById(R.id.timeMarks);
       videoPlayer.requestFocus();

        videoPlayer.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {

                videoPlayer.seekTo(position);

                if (position == 0) {
                    videoPlayer.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    videoPlayer.pause();
                }
            }
        });
        setupTimeStamps();
    }

    public void setupTimeStamps(){
        timeStamps = (ListView) findViewById(R.id.timeMarks);
        String[] values = new String[] { "400", "12000", "40",
                "66666", "10000", "12000", "80000", "34000",
                 };

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
         ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        timeStamps.setAdapter(adapter);
        timeStamps.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                int timestamp = Integer.parseInt(((TextView) view).getText().toString());
                            videoPlay(timestamp);

            }

        });
    }

    public void videoPlay(int timeStamp){
        videoPlayer.start();
        videoPlayer.seekTo(timeStamp);
    }
    public void  videoPause(){
        videoPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_search);
        videoSetup();
        videoPlay(5000);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_search, menu);
        int id = 3;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
