package com.schneewittchen.rosandroid.widgets.string;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import com.schneewittchen.rosandroid.widgets.base.BaseData;

import org.jboss.netty.buffer.ChannelBuffer;

//import sensor_msgs.CompressedImage;
//import sensor_msgs.Image;
import std_msgs.String;


/**
 * TODO: Description
 *
 * @author Jonathan Camargo
 * @version 1.0.0
 * @created on 12.08.20
 * @updated on 12.08.20
 * @modified by
 */

public class StringData extends BaseData {

    public static final java.lang.String TAG = "StringData";
    public java.lang.String string;
    //public Bitmap map;

    public StringData(String msg) {
        //ChannelBuffer buffer = msg.getData();
        this.string = msg.getData();
        //this.map = BitmapFactory.decodeByteArray(buffer.array(), buffer.arrayOffset(), buffer.readableBytes());
    }

}