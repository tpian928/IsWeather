
package com.ctc.isweather.control.charts;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.ctc.isweather.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FinalLineChart extends Activity {

    private String[] xs,ys;

    public FinalLineChart(String[] x,String[] y){
        xs = x;
        ys = y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_linechart);

        setChart((LineChart)findViewById(R.id.chart1),xs,ys);
    }


    public static void setChart(LineChart mChart,String[] xs,String[] ys){

        // no description text
        mChart.setDescription("");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);


        XAxis x = mChart.getXAxis();
        x.setEnabled(true);

        YAxis y = mChart.getAxisLeft();
        y.setStartAtZero(false);
        y.setLabelCount(5);
        y.setEnabled(false);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(mChart,6,xs,ys);

        mChart.getLegend().setEnabled(true);

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    public static void setData(LineChart mChart, int count, String[] xvals,String[] yvals) {

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> vals1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            xVals.add(xvals[i]);
            vals1.add(new Entry(Float.valueOf(yvals[i]), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(vals1, "最低温度");
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        //set1.setDrawFilled(true);
        set1.setDrawCircles(true);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));
        set1.setFillColor(ColorTemplate.getHoloBlue());

        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);
    }

    // Two types
    public static void setChart(LineChart mChart,String[] xs,String[] ys,String[] y2s){
        // no description text
        mChart.setDescription("");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

        XAxis x = mChart.getXAxis();
        x.setEnabled(true);

        YAxis y = mChart.getAxisLeft();
        y.setStartAtZero(false);
        y.setLabelCount(6);
        y.setEnabled(true);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(mChart, 6, xs, ys,y2s);

        mChart.getLegend().setEnabled(true);
        mChart.getLegend().setTextColor(Color.WHITE);
        mChart.getLegend().setFormSize(8f);
        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    public static void setData(LineChart mChart, int count, String[] xvals,String[] yvals,String[] y2vals) {

        ArrayList<String> x = new ArrayList<String>();
        ArrayList<Entry> y = new ArrayList<Entry>();
        ArrayList<Entry> y2 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            x.add(xvals[i]);
            y.add(new Entry(Float.valueOf(yvals[i]), i));
            y2.add(new Entry(Float.valueOf(y2vals[i]),i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(y, "最低温度");
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        //set1.setDrawFilled(true);
        set1.setDrawCircles(true);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));
        set1.setFillColor(ColorTemplate.getHoloBlue());

        LineDataSet set2 = new LineDataSet(y2, "最高温度");
        set2.setDrawCubic(true);
        set2.setCubicIntensity(0.2f);
        //set1.setDrawFilled(true);
        set2.setDrawCircles(true);
        set2.setLineWidth(2f);
        set2.setCircleSize(5f);
        set2.setHighLightColor(Color.rgb(244, 117, 117));
        set2.setColor(Color.rgb(104, 141, 175));
        set2.setFillColor(ColorTemplate.getHoloBlue());


        // create a data object with the datasets
        ArrayList<LineDataSet> ads = new ArrayList<LineDataSet>();
        ads.add(set1);
        ads.add(set2);
        LineData data = new LineData(x,ads);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);
    }


}
