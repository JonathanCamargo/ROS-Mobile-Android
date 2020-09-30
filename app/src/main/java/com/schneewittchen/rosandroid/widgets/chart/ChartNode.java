package com.schneewittchen.rosandroid.widgets.chart;

import android.util.Log;

import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.gridmap.GridMapData;

import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import java.util.ArrayDeque;
import java.util.Deque;

import std_msgs.Float32;

import std_msgs.String;

/**
 * TODO: Description
 *
 * @author Jonathan Camargo
 * @version 1.0.0
 * @created on
 * @updated on
 * @modified by
 */
public class ChartNode extends BaseNode {

    private static final java.lang.String TAG = ChartNode.class.getSimpleName();

    public static final java.lang.Integer QUEUESIZE=100;
    public static final java.lang.Integer INCREMENT=3;// Update every 10 samples
    private ArrayDeque<Float> data= new ArrayDeque<Float>(QUEUESIZE);
    private java.lang.Integer counts=0;

    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<std_msgs.Float32> subscriber = connectedNode.newSubscriber(
                widget.subPubNoteEntity.topic,
                widget.subPubNoteEntity.messageType
        );
        Log.i("message",widget.subPubNoteEntity.messageType);
        subscriber.addMessageListener(msag-> {
            //ChartData data = new ChartData(msag);
            //data.setId(widget.id);
            //listener.onNewData(data);
            this.AddData(msag);
            //listener.onNewData(data);

        });
    }

    public void AddData(Float32 msg){
        Float value = msg.getData();
        Log.i("new data added",value.toString());
        this.data.add(value);
        if (this.data.size()>QUEUESIZE){
            this.data.remove();
        }
        this.counts=this.counts+1;

        if (this.counts>INCREMENT) {
            this.counts=0;
            // Send the buffer to the listener to update the viewer with the full plot
            ChartData data = new ChartData(this.data);
            data.setId(widget.id);
            listener.onNewData(data);
        }
    }


    @Override
    public void onNewData(BaseData data) {

    }
}
