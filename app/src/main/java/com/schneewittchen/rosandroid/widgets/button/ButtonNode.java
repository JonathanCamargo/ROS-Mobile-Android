package com.schneewittchen.rosandroid.widgets.button;

import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.joystick.JoystickData;

import org.apache.commons.lang.ObjectUtils;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

import java.util.Timer;
import java.util.TimerTask;

import std_msgs.String;

import static org.apache.commons.lang.ObjectUtils.*;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.1
 * @created on 13.03.20
 * @updated on 07.05.20
 * @modified by
 */
public class ButtonNode extends BaseNode<WidgetButtonEntity> {

    private java.lang.String text;
    private Publisher<std_msgs.String> publisher;


    @Override
    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher(widget.subPubNoteEntity.topic,
                                                widget.subPubNoteEntity.messageType);
    }

    public void publish() {
        if (publisher == null){
            return;
        }
        std_msgs.String msg = publisher.newMessage();
        msg.setData(this.text);
        publisher.publish(msg);
    }

    @Override
    public void onNewData(BaseData data) {
        ButtonData buttonData = (ButtonData) data;
        this.text=((ButtonData) data).text;
        publish();
    }

}
