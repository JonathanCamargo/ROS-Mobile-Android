package com.schneewittchen.rosandroid.widgets.joystick;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.base.BaseView;


/**
 * TODO: Description
 *
 * @author Nico Studt
 * @version 1.1.0
 * @created on 18.10.19
 * @updated on 10.01.20
 * @modified by
 */
public class JoystickView extends BaseView {

    public static final String TAG = "JoystickView";

    UpdateListener updateListener;

    Paint outerPaint;
    Paint linePaint;
    Paint joystickPaint;

    float joystickRadius;
    float posX;
    float posY;

    TextView test;

    public JoystickView(Context context) {
        super(context);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.joystick_view, this, true);
        test=(TextView)getChildAt(0);
    }

    public JoystickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.joystick_view, this, true);
        test=(TextView)getChildAt(0);
    }

    private void init(){
        joystickRadius = Utils.cmToPx(getContext(), 1)/2;
        joystickPaint = new Paint();
        joystickPaint.setColor(getResources().getColor(R.color.colorAccent));

        outerPaint = new Paint();
        outerPaint.setColor(getResources().getColor(R.color.colorPrimary));
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 3));

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(Utils.dpToPx(getContext(), 2));
    }

    // Move to polarCoordinates
    private void moveTo(float x, float y){
        posX = x;
        posY = y;
        this.informDataChange(new JoystickData(posX, posY));
        //this.test.layout(50,50,200,200);
        // Redraw
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float[] polars = convertFromPxToPolar(eventX, eventY);

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                moveTo(0, 0);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                moveTo(polars[0], polars[1]);
                break;

            default:
                return false;
        }

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();

        float[] px = convertFromPolarToPx(posX, posY);

        // Outer ring
        canvas.drawCircle(width/2, height/2, width/2-joystickRadius, outerPaint);

        // Inner drawings
        canvas.drawCircle(width/2, height/2, width/4-joystickRadius/2, linePaint);
        canvas.drawLine(joystickRadius, height/2, width-joystickRadius, height/2,  linePaint);
        canvas.drawLine(width/2, joystickRadius , width/2, height-joystickRadius,  linePaint);

        // Stick
        canvas.drawCircle(px[0], px[1], joystickRadius, joystickPaint);

        //canvas.drawRoundRect(0, 0, width, height, 10,10,outerPaint);

    }



    private float[] convertFromPxToPolar(float x, float y) {
        float middleX = getWidth()/2f;
        float middleY = getHeight()/2f;
        float r = middleX -joystickRadius;

        float dx = x - middleX;
        float dy = y - middleY;
        double rad = Math.atan2(dy, dx);

        double len = Math.sqrt(dx*dx + dy*dy)/r;
        len = Math.min(1, len);

        float[] polar = new float[2];

        polar[0] = (float) (Math.cos(rad)*len);
        polar[1] = (float) (-Math.sin(rad)*len);

        return polar;
    }

    private float[] convertFromPolarToPx(float x, float y){
        float middleX = getWidth()/2f;
        float middleY = getHeight()/2f;
        float r = middleX -joystickRadius;

        float[] px = new float[2];
        px[0] = middleX + x*r;
        px[1] = middleY - y*r;

        return px;
    }


    public interface UpdateListener {

        void onUpdate(float x, float y);
    }
}
