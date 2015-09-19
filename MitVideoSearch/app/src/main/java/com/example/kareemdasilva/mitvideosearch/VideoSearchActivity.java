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

import java.util.List;


public class VideoSearchActivity extends ActionBarActivity {

    public static VideoView videoPlayer;
    public ListView timeStamps;
    public MediaController control;
    private int position = 0;


    //Setup video player by creating controls/video source
    public void videoSetup() {
        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
        //Loads video to the video player
        if (control == null) {

            control= new MediaController(VideoSearchActivity.this);

        }

        videoPlayer.setMediaController(control);

        String uriPath = "android.resource://"+getPackageName()+"/raw/vacation";
        Uri uri = Uri.parse(uriPath);
        videoPlayer.setVideoURI(uri);
        control = (MediaController) findViewById(R.id.controller);


        timeStamps = (ListView) findViewById(R.id.timeMarks);
       videoPlayer.requestFocus();

        videoPlayer.setOnPreparedListener(new OnPreparedListener() {
           public void onPrepared(MediaPlayer mediaPlayer) {

                videoPlayer.seekTo(position);

                if (position == 0) {

                    videoPlay();

                } else {

                    //if we come from a resumed activity, video playback will be paused

                    videoPause();
                }
            }
        });
    }
    public void videoPlay(){
        videoPlayer.start();
    }
    public void  videoPause(){
        videoPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_search);
        videoSetup();
        videoPlay();

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
