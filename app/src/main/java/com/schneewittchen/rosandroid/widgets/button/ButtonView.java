package com.schneewittchen.rosandroid.widgets.button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseView;


/**
 * TODO: Description
 *
 * @author Jonathan Camargo
 * @version 1.1.0
 * @created on 18.10.19
 * @updated on 10.01.20
 * @modified by
 */
public class ButtonView extends BaseView {

    public static final String TAG = "ButtonView";
    TextView topic;
    Button button;

    public ButtonView(Context context) {
        super(context);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_view, this, true);
        button=(Button)findViewById(R.id.button);
        topic=(TextView)findViewById(R.id.topicNameText);
        configureButton();
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_view, this, true);
        button=(Button)findViewById(R.id.button);
        topic=(TextView)findViewById(R.id.topicNameText);
        configureButton();
    }

    protected void sendMessage(){
        Log.i("buttonview","sending message");
        this.informDataChange(new ButtonData(button.getText().toString()));
    }
    protected View.OnClickListener  onSendMessageClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            sendMessage();
        }
    };

    private void configureButton(){
        this.button.setOnClickListener(onSendMessageClick);
    }

    private void init() {
    }

    @Override
    public void onSetWidgetEntity(){
        WidgetButtonEntity entity=(WidgetButtonEntity) this.widgetEntity;
        this.button.setText(entity.messageStr.toString());
        this.topic.setText(entity.subPubNoteEntity.topic.toString());
        this.invalidate();
    }

    @Override
    public void setData(BaseData data){
        ButtonData buttonData = (ButtonData) data;

    }


}
