package com.example.kareemdasilva.mitvideosearch;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.provider.MediaStore.Files;

//import android.nfc.Tag;




public class VideoSearchActivity extends ActionBarActivity {

    public static VideoView videoPlayer;
    public ListView timeStamps;
    public MediaController control;
    private int position = 0;
    public ArrayAdapter adapter;
    public Uri path;


    //Setup video player by creating controls/video source
    public void videoSetup() {
        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
       //String data = getIntent().getExtras().getString("videoPath");
       path = Uri.parse(getIntent().getExtras().getString("videoPath"));
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        String data = "http://download.wavetlan.com/SVV/Media/HTTP/H264/Talkinghead_Media/H264_test2_Talkinghead_mp4_480x320.mp4"; // "../res/raw/ad.mp4"

//        getApplicationContext().
//        InputStream inp = getAssets().open("ad.mp4");

//        URL resource = VideoSearchActivity.class.getResource("abc");
//        Paths.get(resource.toURI()).toFile();


        // /data/data/<application-package>/files/<file-name>
//        String data = "android.resource://"+getPackageName()+"/raw/ad.mp4";
        ClarifaiClient clarifai = new ClarifaiClient("EHpEfFklAy0-SjUS9G3-oQPg_1vADZohy5zrOc3X","fgMdPQWWmrxj7wEr8OJNm2qdravwaMNaKWE4JRyb");
        //File f = new File(path.getPath());

        InputStream initialStream = null;


        try {
            initialStream = new FileInputStream(path.getPath());
            byte[] buffer = new byte[0];
            buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            File targetFile = new File("src/main/resources/targetFile.tmp");
            File f = targetFile;
            RecognitionRequest r = new RecognitionRequest(f);
            List<RecognitionResult> results = clarifai.recognize(r);
            for (Tag tag : results.get(0).getTags()) {
                Log.d("Clarifai", tag.getName() + ": " + tag.getProbability());
            }
            //.write(buffer, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("fail","fail");
        }









//        RecognitionRequest r = new RecognitionRequest("http://www.clarifai.com/img/metro-north.jpg");
//        List<RecognitionResult> results = clarifai.recognize(r);


    //  InfoResult info = clarifai.getInfo();
      //  Toast.makeText(this, info.getMaxBatchSize(), Toast.LENGTH_LONG).show();
//        Toast toast(info.getMaxBatchSize());







        //Loads video to the video player
        if (control == null) {

            control= new MediaController(VideoSearchActivity.this);

        }

        videoPlayer.setMediaController(control);


//        videoPlayer.setVideoURI(Path);
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
        List kareemd;
        kareemd = new List() {
            @Override
            public void add(int location, Object object) {

            }

            @Override
            public boolean add(Object object) {
                return false;
            }

            @Override
            public boolean addAll(int location, Collection collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection collection) {
                return false;
            }

            @Override
            public Object get(int location) {
                return null;
            }

            @Override
            public int indexOf(Object object) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public int lastIndexOf(Object object) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator listIterator(int location) {
                return null;
            }

            @Override
            public Object remove(int location) {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection collection) {
                return false;
            }

            @Override
            public Object set(int location, Object object) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public List subList(int start, int end) {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }



        };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
         Selector_Adapter adapter = new Selector_Adapter(this,
                R.layout.time_stamp,kareemd);
        timeStamps.setAdapter(adapter);
        /*timeStamps.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                int timestamp = Integer.parseInt(((TextView) view).getText().toString());
                            videoPlay(timestamp);

            }

        });*/
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
        //videoSetup();
        //videoPlay(5000);
        setupTimeStamps();

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
