package com.schneewittchen.rosandroid.widgets.string;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.DetailListener;

import std_msgs.String;


/**
 * TODO: Description
 *
 * @author
 * @version 1.0.0
 * @created
 * @updated
 * @modified by
 */
public class StringDetailVH extends BaseDetailViewHolder<WidgetStringEntity> {

    EditText topicNameText;

    public StringDetailVH(@NonNull View view, DetailListener updateListener) {
        super(view, updateListener);
    }


    @Override
    public void init(View view) {
        topicNameText = view.findViewById(R.id.topicNameText);
    }

    @Override
    public void bind(WidgetStringEntity entity) {
        topicNameText.setText(entity.subPubNoteEntity.topic);

    }

    @Override
    public void updateEntity() {
        entity.subPubNoteEntity.messageType =  String._TYPE;
        entity.subPubNoteEntity.topic = topicNameText.getText().toString();
    }
}
