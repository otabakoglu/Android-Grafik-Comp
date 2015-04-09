package com.trozant.android_grafik_comp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import SimpleRingChartTR.BarChart;
import SimpleRingChartTR.Utils.ChartColors;


public class BarChartActivity extends Activity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChart = (BarChart) findViewById( R.id.bar_chart );
        barChart.addChartValue("Elma", 50, ChartColors.RED);
        barChart.addChartValue("Şeftali", 150, ChartColors.ORANGE);
        barChart.addChartValue("Armut", 80, ChartColors.GREEN);
        barChart.addChartValue("Kiraz", 10, ChartColors.GREY);
        barChart.setTextSize(30);
        barChart.setLinesColor(ChartColors.BLUE);
        barChart.setBackgroundColor(ChartColors.YELLOW);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
