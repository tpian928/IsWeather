package com.ctc.isweather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.ctc.tools.DrawCharts;
import com.github.mikephil.charting.charts.LineChart;

public class ChartActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        DrawCharts dchart = new DrawCharts();

        int count = 7;
        // Get the value
        double[] data = {26.5, 23.6, 28, 30, 32, 29, 27.5};
        LineChart c = (LineChart) findViewById(R.id.chart);
        dchart.showChart(c, dchart.getLineData(data,Color.BLACK,"Temps",count),
                "Temps Changes", Color.WHITE, Color.WHITE, Color.WHITE);
    }
}
