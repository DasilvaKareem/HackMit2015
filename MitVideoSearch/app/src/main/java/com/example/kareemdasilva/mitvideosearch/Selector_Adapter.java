package com.example.kareemdasilva.mitvideosearch;

/**
 * Created by kareemdasilva on 9/20/15.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.ClipData.Item;
import android.widget.ArrayAdapter;
import java.util.List;
public class Selector_Adapter extends ArrayAdapter<Item> {

    public Selector_Adapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public Selector_Adapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.time_stamp, null);
        }

        Item p = getItem(position);

        if (p != null) {
            TextView timer = (TextView) v.findViewById(R.id.TimeStamp);
            //Button goToTime = (Button) v.findViewById(R.id.goToTime);
            //timer.setText(timer.get(VideoSearchActivity.videoPlayer));

            if (timer != null) {
                //timer.setText(p.getText());

            }

            /*if (goToTime != null) {
                goToTime.setText(p.getText());
            }*/

        }

        return v;
    }
    }


