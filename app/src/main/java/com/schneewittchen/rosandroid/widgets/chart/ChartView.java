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
import com.anychart.graphics.vector.Stroke;
import com.anychart.enums.TooltipPositionMode;

/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 27.04.19
 * @updated on
 * @modified by
 */


public class ChartView extends BaseView {

    public static final String TAG = "ChartView";

    Paint paint;
    Paint paintText;
    float cornerWidth;
    Button b1;
    TextView test;

    AnyChartView anychart;
    Cartesian cartesian;
    Set set;
    List<Line> series;
    ChartData data;
    boolean isReady;
    Line series1;


    public ChartView(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chart_view, this, true);

        test = (TextView) getChildAt(0);
        test.setText("holaaaa");
        anychart = (AnyChartView) getChildAt(1);

        data = new ChartData();
        series= new ArrayList<Line>();

        init();

    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chart_view, this, true);

        test = (TextView) getChildAt(0);
        test.setText("holaaaa");
        anychart = (AnyChartView) getChildAt(1);

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


        /*
        List<DataEntry> seriesData = new ArrayList<>();
        Float[] array=this.data.queue.toArray(new Float[0]);
        for (int i=0; i<this.data.queue.size(); i++ ){
            seriesData.add(new ValueDataEntry(i,array[i]));
        }

        cartesian = AnyChart.line();
        set = Set.instantiate();
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
        cartesian.title("Data");
        anychart.setChart(cartesian);

        */
        cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Incoming data");

        //cartesian.yAxis(0).title("Number of Bottles Sold (thousands)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);



        set = Set.instantiate();

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anychart.setChart(cartesian);


        this.setBackgroundColor(0x8000bcd4);

    }

    @Override
    public void onSetWidgetEntity() {
        super.onSetWidgetEntity();
        //series1.name(this.getWidgetEntityName());
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
            Log.i(TAG, data.queue.toString());
        } else {
            canvas.drawRoundRect(leftViz, topViz, width, height, cornerWidth, cornerWidth, paint);
        }

        //Update chart with new data
        /*
        List<DataEntry> seriesData = new ArrayList<>();
        Object[] array = this.data.queue.toArray();
        //Object array is an array of float[]
        for (int i = 0; i < (array.length); i++) {
            seriesData.add(new CustomDataEntry(i,(float[])array[i]));
        }
        */
        //this.set.data(seriesData);
        /*
        if ((this.isReady==false) && (array.length>0)){
            float[] row=(float[])array[0];
            for (int i=0;i<row.length;i++){
                String mappingstr="{x:'x', value: "+String.format("'%d'",i)+"}";
                Mapping seriesMapping = set.mapAs(mappingstr);
                Line serie = cartesian.line(seriesMapping);
                serie.name(String.format("%d",i));
                serie.hovered().markers().enabled(true);
                serie.hovered().markers()
                        .type(MarkerType.CIRCLE)
                        .size(4d);
                serie.tooltip()
                        .position("right")
                        .anchor(Anchor.LEFT_CENTER)
                        .offsetX(5d)
                        .offsetY(5d);
                this.series.add(serie);
            }
            this.isReady=true;
            }
            */

        // New upcoming data
        List<DataEntry> seriesData2 = new ArrayList<>();
        Object[] array = this.data.queue.toArray();
        for (int i = 0; i < (array.length); i++) {
            seriesData2.add(new CustomDataEntry2(i,(float[])array[i]));
        }

        if ((this.isReady==false) && (array.length>0)){
            /*
            List<DataEntry> seriesData = new ArrayList<>();
            seriesData.add(new CustomDataEntry("1986", 3.6, 2.3, 2.8));
            seriesData.add(new CustomDataEntry("1987", 7.1, 4.0, 4.1));
            seriesData.add(new CustomDataEntry("1988", 8.5, 6.2, 5.1));
            seriesData.add(new CustomDataEntry("1989", 9.2, 11.8, 6.5));
            seriesData.add(new CustomDataEntry("1990", 10.1, 13.0, 12.5));
            seriesData.add(new CustomDataEntry("1991", 11.6, 13.9, 18.0));
            seriesData.add(new CustomDataEntry("1992", 16.4, 18.0, 21.0));
            seriesData.add(new CustomDataEntry("1993", 18.0, 23.3, 20.3));
            seriesData.add(new CustomDataEntry("1994", 13.2, 24.7, 19.2));
            seriesData.add(new CustomDataEntry("1995", 12.0, 18.0, 14.4));
            seriesData.add(new CustomDataEntry("1996", 3.2, 15.1, 9.2));
            seriesData.add(new CustomDataEntry("1997", 4.1, 11.3, 5.9));
            seriesData.add(new CustomDataEntry("1998", 6.3, 14.2, 5.2));
            seriesData.add(new CustomDataEntry("1999", 9.4, 13.7, 4.7));
            seriesData.add(new CustomDataEntry("2000", 11.5, 9.9, 4.2));
            seriesData.add(new CustomDataEntry("2001", 13.5, 12.1, 1.2));
            seriesData.add(new CustomDataEntry("2002", 14.8, 13.5, 5.4));
            seriesData.add(new CustomDataEntry("2003", 16.6, 15.1, 6.3));
            seriesData.add(new CustomDataEntry("2004", 18.1, 17.9, 8.9));
            seriesData.add(new CustomDataEntry("2005", 17.0, 18.9, 10.1));
            seriesData.add(new CustomDataEntry("2006", 16.6, 20.3, 11.5));
            seriesData.add(new CustomDataEntry("2007", 14.1, 20.7, 12.2));
            seriesData.add(new CustomDataEntry("2008", 15.7, 21.6, 10));
            seriesData.add(new CustomDataEntry("2009", 12.0, 22.5, 8.9));
            */
            set.data(seriesData2);
            float [] firstrow=(float[]) array[0];
            int n= firstrow.length;
            for (int i=0;i<n;i++){
                String mappingstr="{x:'index', value: "+String.format("'%d'",i)+"}";
                Mapping seriesMapping = set.mapAs(mappingstr);
                Line serie = cartesian.line(seriesMapping);
                serie.name(String.format("'%d'",i));
                serie.hovered().markers().enabled(true);
                serie.hovered().markers()
                        .type(MarkerType.CIRCLE)
                        .size(4d);
                serie.tooltip()
                        .position("right")
                        .anchor(Anchor.LEFT_CENTER)
                        .offsetX(5d)
                        .offsetY(5d);
                series.add(serie);
            }

            //Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
            //Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
            //Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
            /*
            Line series1 = cartesian.line(series1Mapping);
            series1.name("Brandy");
            series1.hovered().markers().enabled(true);
            series1.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series1.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

            Line series2 = cartesian.line(series2Mapping);
            series2.name("Whiskey");
            series2.hovered().markers().enabled(true);
            series2.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series2.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

            Line series3 = cartesian.line(series3Mapping);
            series3.name("Tequila");
            series3.hovered().markers().enabled(true);
            series3.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series3.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

             */
            this.isReady=true;
        }

        if ((this.isReady==true) && (array.length>0)){
            set.data(seriesData2);
        }

        this.getWidgetEntityName();
    }

    @Override
    public void setData(BaseData data) {
        // System.out.println("CameraView: SetData!");
        this.data = (ChartData) data;
        this.invalidate();
    }

    public class CustomDataEntry2 extends DataEntry {


        CustomDataEntry2(Number index, float[] values) {
            setValue("index",index);
            for (int i=0;i<values.length;i++){
                setValue(String.format("%d",i),values[i]);
            }

        }

    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }
}