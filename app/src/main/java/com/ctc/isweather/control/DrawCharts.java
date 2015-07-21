package com.ctc.isweather.control;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by chris on 2015/7/14.
 * Implementation of Charts
 */
public class DrawCharts {

    public static LineDataSet getLineDataSet(double[] datas,
                                      int lineColor, int circleColor, int hlcolor,
                                      String label) {
        ArrayList<Entry> yvals = new ArrayList<Entry>();

        for (int i = 0; i < datas.length; i++) {
            yvals.add(new Entry((float) datas[i], i));
        }

        LineDataSet lds = new LineDataSet(yvals, label);
        lds.setLineWidth(2f);
        lds.setCircleSize(2f);
        lds.setColor(lineColor);
        lds.setCircleColor(circleColor);
        lds.setHighLightColor(hlcolor);
        return lds;
    }

    public static LineData getLineData(int xaxis, ArrayList<LineDataSet> linedatas) {
        ArrayList<String> x = new ArrayList<String>();
        for (int i = 0; i < xaxis; i++)
            x.add(i + "");

        LineData lindata = new LineData(x, linedatas);
        return lindata;
    }

    public static void showChart(LineChart lineChart, LineData lineData, String desc, int gridColor, int bkgColor, int textColor) {
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setStartAtZero(false);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setStartAtZero(false);

        lineChart.setDrawBorders(false);
        lineChart.setDescription(desc);

        // enable / disable grid background
        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(gridColor);
        lineChart.setBackgroundColor(bkgColor);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);

        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(false);

        // set the labels
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(6f);
        legend.setTextColor(textColor);

        // set data
        lineChart.setData(lineData);

        lineChart.animateX(1500);
    }

}
