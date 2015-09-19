package com.example.kareemdasilva.mitvideosearch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;
import android.net.Uri;


public class VideoSearchActivity extends ActionBarActivity {

    public static VideoView videoPlayer;

    //Setup video player by creating controls/video source
    public void videoSetup() {
        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
        String uriPath = "android.resource://"+getPackageName()+"/raw/vacation";
        Uri uri = Uri.parse(uriPath);
        videoPlayer.setVideoURI(uri);
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
