package com.ctc.isweather.interfaces;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by chris on 2015/7/14.
 * Import Data -> LineDataSet  -> LineData  -> showChart
 */
public interface Charts {

    /**
     * import data( double[] ) into LineData
     *
     * @param datas     the set of temperature
     * @param lineColor the color of the line
     * @return the data structure fo drawing graphes
     */
    LineDataSet getLineDataSet(double[] datas, int lineColor, String label);

    /**
     * import lineDataSet and organize to be LineData
     *
     * @return the data structure fo drawing graphes
     */
    LineData getLineData(double[] datas, int lineColor, String label, int xaxis);

    /**
     * More lines in one chart
     *
     * @param xaxis     The length of x axis
     * @param linedatas the set of linedataset
     * @return the data structure fo drawing graphes
     */
    LineData getLineData(int xaxis, ArrayList<LineDataSet> linedatas);

    /**
     * Draw line chart
     *
     * @param lineChart Component, (LineChart) findViewById(lineChart);
     * @param lineData  Data
     * @param desc      Description of Chart
     * @param gridColor gird line color
     * @param bkgColor  background color
     * @param textColor textColof
     */
    void showChart(LineChart lineChart, LineData lineData, String desc, int gridColor, int bkgColor, int textColor);

}
