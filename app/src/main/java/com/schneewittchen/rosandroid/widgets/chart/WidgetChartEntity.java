package com.schneewittchen.rosandroid.widgets.chart;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.SubPubNoteEntity;
import com.schneewittchen.rosandroid.model.entities.WidgetEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.BaseEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.base.BaseView;

import android.util.Log;

import std_msgs.Float32;

/**
 * TODO: Description
 *
 * @author
 * @version
 * @created
 * @updated
 * @modified
 */
public class WidgetChartEntity extends BaseEntity {

    int colorScheme;
    boolean drawBehind;
    boolean useTimeStamp;


    public WidgetChartEntity() {
        this.setType("Chart");

        this.width = 6;
        this.height = 6;
        this.subPubNoteEntity = new SubPubNoteEntity();
        this.subPubNoteEntity.topic = "float32multi";
        this.subPubNoteEntity.messageType = std_msgs.Float32MultiArray._TYPE;
    }


    @Override
    public String getName() {
        return "Chart";
    }

    @Override
    public Class<? extends BaseView> getViewType() {
        return ChartView.class;
    }

    // TODO: Add own layout for image style?
    @Override
    public int getWidgetDetailViewId() {
        return R.layout.widget_detail_chart;
    }

    @Override
    public Class<? extends BaseDetailViewHolder> getDetailViewHolderType() {
        return ChartDetailVH.class;
    }

    @Override
    public Class<? extends BaseNode> getNodeType() {
        return ChartNode.class;
    }


    @Override
    public void insert(WidgetEntity entity) {
        super.insert(entity);

        this.subPubNoteEntity.topic = entity.subPubNoteEntity.topic;
        this.subPubNoteEntity.messageType = entity.subPubNoteEntity.messageType;
    }

    @Override
    public WidgetChartEntity copy() {
        WidgetChartEntity newEnt = new WidgetChartEntity();
        newEnt.insert(this);

        return newEnt;
    }

    @Override
    public boolean equalContent(BaseEntity widget) {
        if (!(widget instanceof WidgetChartEntity))
            return false;

        WidgetChartEntity other = (WidgetChartEntity) widget;

        return this.subPubNoteEntity.equals(other.subPubNoteEntity);
    }
}

