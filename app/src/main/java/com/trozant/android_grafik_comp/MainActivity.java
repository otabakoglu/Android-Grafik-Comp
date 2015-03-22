package com.trozant.android_grafik_comp;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import SimpleRingChartTR.ChartColors;
import SimpleRingChartTR.RingChart;
import SimpleRingChartTR.RingChartValue;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RingChart mRingChart = (RingChart) findViewById( R.id.chart );

        //Deger Ekleme- Add Value
        mRingChart.addChartValue("Elma", 50, ChartColors.RED);
        mRingChart.addChartValue("Şeftali", 150, ChartColors.ORANGE);
        mRingChart.addChartValue("Armut", 80, ChartColors.GREEN);
        mRingChart.addChartValue("Kiraz", 10, ChartColors.GREY);

        //Coklu Deger Ekleme- Add Values
        List<RingChartValue> values = new ArrayList<RingChartValue>();
        values.add( new RingChartValue("Elma", 50, ChartColors.RED));
        values.add( new RingChartValue("Armut", 100, ChartColors.ORANGE));
        mRingChart.addChartValues(values);

        //Deger Silme - id ile
        mRingChart.deleteChartValue( 0 );
        //Deger Silme - isim ile
        mRingChart.deleteChartValue( "Elma" );
        //butun degerleri sil.
        mRingChart.deleteAllValue();

        //id ile deger guncelleme
        mRingChart.updateChartValue( 0,"Elma", 50, ChartColors.RED );
        mRingChart.updateChartValue( 0,new RingChartValue("Şeftali", 80, ChartColors.GREEN) );

        //Deger Secme
        mRingChart.selectValue(2);

        //Yaziyi Goster/Gizle
        mRingChart.setTextShow( true );


    }




}
