package com.schneewittchen.rosandroid.widgets.chart;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.DetailListener;

import std_msgs.Float32;


/**
 * TODO: Description
 *
 * @author
 * @version 1.0.0
 * @created
 * @updated
 * @modified by
 */
public class ChartDetailVH extends BaseDetailViewHolder<WidgetChartEntity> {

    EditText topicNameText;

    public ChartDetailVH(@NonNull View view, DetailListener updateListener) {
        super(view, updateListener);
    }


    @Override
    public void init(View view) {
        topicNameText = view.findViewById(R.id.topicNameText);
    }

    @Override
    public void bind(WidgetChartEntity entity) {
        topicNameText.setText(entity.subPubNoteEntity.topic);

    }

    @Override
    public void updateEntity() {
        entity.subPubNoteEntity.messageType =  Float32._TYPE;
        entity.subPubNoteEntity.topic = topicNameText.getText().toString();
    }
}
