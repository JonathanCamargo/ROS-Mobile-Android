package com.schneewittchen.rosandroid.widgets.chart;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.widgets.base.BaseData;

//import sensor_msgs.CompressedImage;
//import sensor_msgs.Image;
import std_msgs.Float32;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * TODO: Description
 *
 * @author Jonathan Camargo
 * @version 1.0.0
 * @created on 12.08.20
 * @updated on 12.08.20
 * @modified by
 */

public class ChartData extends BaseData {

    public static final java.lang.Integer QUEUESIZE=100;

    public static final java.lang.String TAG = "ChartData";
    public ArrayDeque<Float> queue= new ArrayDeque<Float>(QUEUESIZE);

    public ChartData() {
        for (int i=0; i<100; i++) {
            queue.push(new Float(10.0));
        }
    }

    public ChartData(ArrayDeque<Float> queue) {
        //ChannelBuffer buffer = msg.getData();
            this.queue=queue.clone();
        }

}