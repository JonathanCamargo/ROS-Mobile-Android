package com.schneewittchen.rosandroid.widgets.button;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.DetailListener;

import java.util.Locale;


/**
 * TODO: Description
 *
 * @author Jonathan Camargo
 * @version 1.0.2
 * @created on 13.02.20
 * @updated on 20.05.20
 * @modified by
 */
public class ButtonDetailVH extends BaseDetailViewHolder<WidgetButtonEntity> {

    private EditText topicNameText;
    private EditText messageText;

    public ButtonDetailVH(@NonNull View view, DetailListener updateListener) {
        super(view, updateListener);
    }


    @Override
    public void init(View view) {
        topicNameText = view.findViewById(R.id.topicNameText);
        messageText = view.findViewById(R.id.messageText);
    }

    @Override
    public void bind(WidgetButtonEntity entity) {
        topicNameText.setText(entity.subPubNoteEntity.topic);
        messageText.setText(entity.messageStr);
    }

    @Override
    public void updateEntity() {
        entity.subPubNoteEntity.messageType = std_msgs.String._TYPE;
        entity.subPubNoteEntity.topic = topicNameText.getText().toString();
        entity.messageStr=messageText.getText().toString();
    }
}
