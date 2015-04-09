package SimpleRingChartTR.Utils;

import android.graphics.Color;

/**
 * Created by Cry on 21.03.2015.
 */
public class ChartColors {

   //RED
    public  static final int RED      = Color.parseColor("#E57373");
    private static final int DARK_RED = Color.parseColor("#D32F2F");

   //PINK
    public  static final int PINK      = Color.parseColor("#e91e63");
    private static final int DARK_PINK = Color.parseColor("#c2185b");

   //PURPLE
    public  static final int PURPLE      = Color.parseColor("#9c27b0");
    private static final int DARK_PURPLE = Color.parseColor("#7b1fa2");

   //DARK PURPLE
    public  static final int D_PURPLE      = Color.parseColor("#673ab7");
    private static final int DARK_D_PURPLE = Color.parseColor("#512da8");

   //INDIGO
    public  static final int INDIGO      = Color.parseColor("#3f51b5");
    private static final int DARK_INDIGO = Color.parseColor("#303f9f");

   //BLUE
    public  static final int BLUE      = Color.parseColor("#2196f3");
    private static final int DARK_BLUE = Color.parseColor("#1976d2");

   //CYAN
    public  static final int CYAN      = Color.parseColor("#00bcd4");
    private static final int DARK_CYAN = Color.parseColor("#0097a7");

   //TEAL
    public  static final int TEAL      = Color.parseColor("#009688");
    private static final int DARK_TEAL = Color.parseColor("#00796b");

   //GREEN
    public  static final int GREEN      = Color.parseColor("#4caf50");
    private static final int DARK_GREEN = Color.parseColor("#388e3c");

   //LIGHT GREEN
    public  static final int L_GREEN      = Color.parseColor("#8bc34a");
    private static final int DARK_L_GREEN = Color.parseColor("#689f38");

   //LIME
    public  static final int LIME      = Color.parseColor("#cddc39");
    private static final int DARK_LIME = Color.parseColor("#afb42b");

   //YELLOW
    public  static final int YELLOW      = Color.parseColor("#ffeb3b");
    private static final int DARK_YELLOW = Color.parseColor("#fbc02d");

   //AMBER
    public  static final int AMBER      = Color.parseColor("#ffc107");
    private static final int DARK_AMBER   = Color.parseColor("#ffa000");

   //ORANGE
    public  static final int ORANGE      = Color.parseColor("#ff9800");
    private static final int DARK_ORANGE = Color.parseColor("#f57c00");

   //DEEP ORANGE
    public  static final int D_ORANGE      = Color.parseColor("#ff5722");
    private static final int DARK_D_ORANGE = Color.parseColor("#e64a19");

   //BROWN
    public  static final int BROWN        = Color.parseColor("#795548");
    private static final int DARK_BROWN   = Color.parseColor("#5d4037");

   //GREY
    public  static final int LIGHT_GREY = Color.parseColor("#F5F5F5");
    public  static final int GREY      = Color.parseColor("#9e9e9e");
    private static final int DARK_GREY = Color.parseColor("#616161");

   //BLUE GREY
    public  static final int BLUE_GREY        = Color.parseColor("#607d8b");
    private static final int DARK_BLUE_GREY   = Color.parseColor("#455a64");

  //BLACK AND WHITE
    public static final int WHITE = Color.parseColor("#ffffff");
    public static final int BLACK = Color.parseColor("#000000");

  //
    public static final int CHART_LINE = Color.parseColor("#616161");

    public static int getDarkColor(  int color ){

        if( color == RED ){
            return DARK_RED;
        }else if( color == PINK ){
            return DARK_PINK;
        }else if( color == PURPLE ){
            return DARK_PURPLE;
        }else if( color == D_PURPLE ){
            return DARK_D_PURPLE;
        }else if( color == INDIGO ){
            return DARK_INDIGO;
        }else if( color == BLUE){
            return DARK_BLUE;
        }else if ( color == CYAN ){
            return DARK_CYAN;
        }else if ( color == TEAL ){
            return DARK_TEAL;
        }else if ( color == GREEN ){
            return DARK_GREEN;
        }else if ( color == L_GREEN ){
            return DARK_L_GREEN;
        }else if ( color == LIME ){
            return DARK_LIME;
        }else if ( color == YELLOW ){
            return DARK_YELLOW;
        }else if ( color == AMBER ){
            return DARK_AMBER;
        }else if ( color == ORANGE ){
            return DARK_ORANGE;
        }else if ( color == D_ORANGE ){
            return DARK_D_ORANGE;
        }else if ( color == BROWN ){
            return DARK_BROWN;
        }else if ( color == GREY ){
            return DARK_GREY;
        }else if ( color == BLUE_GREY ){
            return DARK_BLUE_GREY;
        }else{
            return WHITE;
        }
    }

}
