package com.schneewittchen.rosandroid.widgets.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;



/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.0.0
 * @created on 15.03.20
 * @updated on 21.04.20
 * @modified by Nils Rottmann
 */
public class BaseView extends LinearLayout implements Interactable {

    DataListener dataListener;
    long dataId;
    Position position;
    BaseEntity widgetEntity;
    RelativeLayout.LayoutParams params;


    public BaseView(Context context) {
        super(context);
        this.setWillNotDraw(false);
        this.setOrientation(VERTICAL);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);
        this.setOrientation(VERTICAL);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);
        this.setOrientation(VERTICAL);
    }

    @Override
    public void informDataChange(BaseData data) {
        if(dataListener != null) {
            data.setId(getDataId());
            dataListener.onNewData(data);
        }
    }

    public String getWidgetEntityName(){
        return this.widgetEntity.name;
    }

    @Override
    public void setData(BaseData data) {
        // Default data set, but nothing to see here
    }

    @Override
    public void setDataListener(DataListener listener) {
        this.dataListener = listener;
    }

    @Override
    public void removeDataListener() {
        this.dataListener = null;
    }

    @Override
    public void setDataId(long id) {
        this.dataId = id;
    }

    @Override
    public long getDataId() {
        return this.dataId;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void onSetWidgetEntity(){

    }
    public void setWidgetEntity(BaseEntity widgetEntity) {
        this.widgetEntity = widgetEntity;
        this.setDataId(widgetEntity.id);
        this.onSetWidgetEntity();
    }
    
    public boolean sameWidget(BaseEntity other) {
        return this.dataId == other.id;
    }
}
