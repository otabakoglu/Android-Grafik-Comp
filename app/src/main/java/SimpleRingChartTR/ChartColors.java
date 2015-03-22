package SimpleRingChartTR;

import android.graphics.Color;

/**
 * Created by Cry on 21.03.2015.
 */
public class ChartColors {

    public static final int RED = Color.parseColor( "#f46e6e" );
    public static final int BLUE = Color.parseColor("#5bbfe3");
    public static final int GREEN = Color.parseColor("#a7cb4b");
    public static final int GREY = Color.parseColor("#b7b7b7");
    public static final int ORANGE = Color.parseColor("#ffc65c");

    private static final int DARK_RED = Color.parseColor("#ef0707");
    private static final int DARK_BLUE = Color.parseColor("#00a1d5");
    private static final int DARK_GREEN = Color.parseColor("#82b600");
    private static final int DARK_GREY = Color.parseColor("#939393");
    private static final int DARK_ORANGE = Color.parseColor("#ffa900");

    public static int getDarkColor( int color ){

        if( color == RED ){
            return DARK_RED;
        }else if( color == BLUE ){
            return DARK_BLUE;
        }else if( color == GREEN ){
            return DARK_GREEN;
        }else if( color == GREY ){
            return DARK_GREY;
        }else if( color == ORANGE ){
            return DARK_ORANGE;
        }else{
            return GREY;
        }
    }

}
