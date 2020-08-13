package com.schneewittchen.rosandroid.widgets.string;

import android.util.Log;

import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.gridmap.GridMapData;

import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

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
public class StringNode extends BaseNode {

    private static final java.lang.String TAG = StringNode.class.getSimpleName();

    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<std_msgs.String> subscriber = connectedNode.newSubscriber(
                widget.subPubNoteEntity.topic,
                widget.subPubNoteEntity.messageType
        );
        subscriber.addMessageListener(msg-> {
            StringData data = new StringData(msg);
            data.setId(widget.id);
            listener.onNewData(data);
        });
    }

    @Override
    public void onNewData(BaseData data) {
    }
}