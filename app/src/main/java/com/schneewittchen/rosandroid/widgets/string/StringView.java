package com.schneewittchen.rosandroid.widgets.string;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 27.04.19
 * @updated on
 * @modified by
 */
public class StringView extends BaseView {

    public static final String TAG = "StringView";

    Paint paint;
    Paint paintText;
    float cornerWidth;
    StringData data;
    TextView test;


    public StringView(Context context) {
        super(context);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.string_view, this, true);
        test=(TextView)getChildAt(0);
    }

    public StringView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.string_view, this, true);
        test=(TextView)getChildAt(0);
    }

    private void init() {
        this.cornerWidth = Utils.dpToPx(getContext(), 8);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.whiteHigh));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paintText = new Paint();
        paintText.setColor(getResources().getColor(R.color.whiteHigh));
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setStrokeWidth(2);
        paintText.setTextSize(32);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.i(TAG, "On draw");
        // Define Image Size based on the BitMap length and width
        float leftViz = 0F;
        float topViz = 0F;
        float widthViz = getWidth();
        float heightViz = getHeight();

        float width = widthViz;
        float height = heightViz;
        float left = leftViz;
        float top = topViz;

        if (data != null) {
            canvas.drawRoundRect(leftViz, topViz, widthViz, heightViz, cornerWidth, cornerWidth, paint);
            Log.i(TAG,data.string);
            canvas.drawText(data.string,leftViz,topViz+100,paintText);
        } else {
            canvas.drawRoundRect(leftViz, topViz, width, height, cornerWidth, cornerWidth, paint);
        }
    }

    @Override
    public void setData(BaseData data) {
        // System.out.println("CameraView: SetData!");
        this.data = (StringData) data;
        this.invalidate();
    }

}