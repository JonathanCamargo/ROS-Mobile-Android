package com.schneewittchen.rosandroid.widgets.chart;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.anychart.AnyChartView;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Set;
import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.utility.Utils;
import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseView;

import androidx.fragment.app.FragmentManager;

import org.apache.commons.io.filefilter.TrueFileFilter;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anychart.data.Mapping;

import com.anychart.charts.Cartesian;

import com.anychart.enums.MarkerType;

import com.anychart.enums.Anchor;

import com.anychart.data.Set;

/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 27.04.19
 * @updated on
 * @modified by
 */
public class ChartView extends BaseView  {

    public static final String TAG = "ChartView";

    Paint paint;
    Paint paintText;
    float cornerWidth;
    Button b1;
    TextView test;

    AnyChartView anychart;
    Set set;
    Line series1;

    ChartData data;

    public ChartView(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chart_view, this, true);

        test=(TextView)getChildAt(0);
        test.setText("holaaaa");
        anychart=(AnyChartView)getChildAt(1);

        data = new ChartData();

        init();

    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.joystick_view, this, true);

        test=(TextView)getChildAt(0);
        test.setText("holaaaa");
        anychart=(AnyChartView)getChildAt(1);

        data = new ChartData();
        init();
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

        List<DataEntry> seriesData = new ArrayList<>();
        Float[] array=this.data.queue.toArray(new Float[0]);
        for (int i=0; i<this.data.queue.size(); i++ ){
            seriesData.add(new ValueDataEntry(i,array[i]));
        }

        Cartesian cartesian = AnyChart.line();
        set= Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{x:'x', value: 'value'}");
        series1 = cartesian.line(series1Mapping);
        series1.name("");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anychart.setChart(cartesian);

        this.setBackgroundColor(0x8000bcd4);

    }

    @Override
    public void onSetWidgetEntity() {
        super.onSetWidgetEntity();
        series1.name(this.getWidgetEntityName());
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
            Log.i(TAG,data.queue.toString());
        } else {
            canvas.drawRoundRect(leftViz, topViz, width, height, cornerWidth, cornerWidth, paint);
        }

        //Update chart with new data

        List<DataEntry> seriesData = new ArrayList<>();
        Float[] array=this.data.queue.toArray(new Float[0]);
        for (int i=0; i<array.length; i++ ){
            seriesData.add(new ValueDataEntry(i,array[i]));
        }
        this.set.data(seriesData);
        this.getWidgetEntityName();
    }

    @Override
    public void setData(BaseData data) {
        // System.out.println("CameraView: SetData!");
        this.data = (ChartData) data;
        this.invalidate();
    }

}