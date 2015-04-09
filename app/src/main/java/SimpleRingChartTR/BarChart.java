package SimpleRingChartTR;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import SimpleRingChartTR.Models.RingChartValue;
import SimpleRingChartTR.Utils.ChartColors;

/**
 * Created by Ozan Tabakoğlu on 02.04.2015.
 * @author Ozan Tabakoğlu
 * @since  02.04.2015
 * @deprecated Simple Bar Chart
 */
public class BarChart extends View {

    private float chartWidth, chartHeight, gridWidth, gridHeight;
    private int thickness, space ;
    private float barWidth;

    private Paint paint, backgroundPaint, textPaint;

    private List<RingChartValue> valueList = new ArrayList<RingChartValue>();

    //Constructors
    public BarChart(Context context){ super(context); }
    public BarChart(Context context, AttributeSet paramAttributeSet)
    {         super(context, paramAttributeSet); }
    public BarChart(Context context, AttributeSet paramAttributeSet, int paramInt)
    {        super(context, paramAttributeSet, paramInt); }

    /**
     * Gerekli Ayarlamalar- Cizim sinifi, boyutlar, kenarliklar.
     */
    private void initPaint(){

        //new paint
        paint = new Paint();
        paint.setColor( ChartColors.RED );
        // Thickness
        paint.setStrokeWidth( thickness );

        //Paint Style Fill and Stroke
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        //new paint
        backgroundPaint = new Paint();
        //antialias - true
        backgroundPaint.setAntiAlias(true);
        //Color GREY
        backgroundPaint.setColor(ChartColors.LIGHT_GREY);
        //Style == FILL
        backgroundPaint.setStyle(Paint.Style.FILL);
    }
    //default text size 30f
    private float textSize = 30f;
    private void initText(){
        // Paint for All Text
        textPaint = new Paint();
        //AntiAlias -yes
        textPaint.setAntiAlias(true);
        //Text Color Grey
        textPaint.setColor(ChartColors.getDarkColor(ChartColors.GREY));
        //Text Size
        textPaint.setTextSize( textSize );
    }

    private void init(){

        //Genislik/Yukseklik Al. get Width and Height.
        chartWidth  = getMeasuredWidth();
        chartHeight = getMeasuredHeight();
        //Yazi ayarlarinin yapilmasi.
        initText();
        //Grafik ve Yazi Alanlarinin Hesaplanmasi
        CalculateGrid();
        //Renk ve Style degerlerinin ayari.
        initPaint();

    }

    /**
     *kalinliklarin bosluklarin ve grafiklerin hizalanmasi
     *icin gerekli hesaplamalar
     *sol bosluk icin gerekli kesme islemleri getValueTextWidth()
     */
    private void CalculateGrid(){
        int listSize = valueList.size();
        if(listSize < 3){
            listSize = 4;
        }
        gridWidth = (chartWidth - getValueTextWidth()*3/2  ) / listSize;
        //Bar lar arası bosluk.
        space = (int)gridWidth / 5;
        //barlarin bulundugu bolum icin yukseklik degeri
        //gridHeight = chartHeight - space / 3;
        //butun barlarin genisligi ayni
        barWidth = gridWidth - space*2;
        //bosluga gore kalinlik hesapla
        thickness = space / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //addChartValue("E", 200, ChartColors.RED);
       // addChartValue("A", 100, ChartColors.ORANGE     );
        //addChartValue( "Ş", 300, ChartColors.YELLOW  );
        //addChartValue( "K", 400, ChartColors.BLUE     );
        //addChartValue( "Kiraz", 170, ChartColors.AMBER     );
        //addChartValue( "Şeftali", 150, ChartColors.YELLOW  );
        /**
         * Ready?
         */
        init( );

        //setTextSize(50f);

        // Alt Bosluk
        float bottomSpace = (space / 3)*2 + getTextHeight();
        //sol bosluk
        float leftSpace = (space / 3)*2 + getValueTextWidth();

        //barlarin bulundugu bolum icin yukseklik degeri
        gridHeight = chartHeight - bottomSpace;
        //BG
        //draw background rect
        int backgroundX1 = (int) leftSpace;
        int backgroundY1 = 0;
        int backgroundX2 = (int)( chartWidth );
        int backgroundY2 = (int)( chartHeight - bottomSpace );
        Rect backgroundRect = new Rect( backgroundX1, backgroundY1, backgroundX2, backgroundY2 );
        //yes draw
        canvas.drawRect( backgroundRect, backgroundPaint );

        //toplam degerin alinmasi
        //bar yukseklikleri icin kullanilacak
        int totalValue = getTopValue();


        //Start coor.
        float barX1 = 0, barX2 = 0;

        //degerlerin teker teker cizilmesi
        for( int i=0; i<valueList.size(); i++){

            int barHeight = ( (int)gridHeight / totalValue ) * valueList.get(i).getValue();

            //get colors - Stroke and Fill
            int barColor = valueList.get(i).getColor();
            int barStrokeColor = ChartColors.getDarkColor( barColor );
            // set Paint Style and Color
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor( barStrokeColor );
            //start point
            barX1 = (int)gridWidth * i + space + getValueTextWidth();
            //end point
            barX2 = barX1 + (int)barWidth;


            //Draw Bottom Name Text
            String nameText = valueList.get( i ).getName();
            //get Text Bounds
            Rect nameTextBounds = new Rect();
            textPaint.getTextBounds( nameText, 0, nameText.length(), nameTextBounds );

            //get Bar Centers
            float nameTextX = ( barX2 - (barWidth/2) ) - (nameTextBounds.width()/2)  ;
            //text Y coor
            float nameTextY = chartHeight - ((space / 3) *2);

            //set Text Color == Bar Stroke Color
            int nameTextColor = valueList.get(i).getColor();
            // get dark color
            textPaint.setColor( ChartColors.getDarkColor( nameTextColor ));
            canvas.drawText( nameText, nameTextX, nameTextY, textPaint );

            String valueText = String.valueOf( valueList.get( i ).getValue() );
            canvas.drawText( valueText, space/3, gridHeight - barHeight + getTextHeight(), textPaint );


            // Kenarlik icin ilk bar i ciz.
            /**(Yukseklik)
             * @see gridHeight
             * tasmamasi icin (kenarlik)
             * @see thickness kadar  kesikmesi gerekiyor
             */
            RectF mRect = new RectF(
                    barX1,
                    gridHeight - barHeight + thickness,
                    barX2,
                    gridHeight - ( thickness/2 )  );
            canvas.drawRect( mRect, paint );

            //renk ve kenarlik ayarlarini yap
            paint.setStyle(Paint.Style.FILL);
            paint.setColor( barColor );
            //bar i ciz.
            canvas.drawRect( mRect, paint );

        }

        //Set Paint Color - Dark Grey and Stroke Width
        paint.setColor( linesColor );
        paint.setStrokeWidth( thickness / 2 );

        // 1. line
        float[] pointsLine1 = {
                leftSpace,
                0,
                leftSpace,
                chartHeight - bottomSpace
        };
        canvas.drawLines( pointsLine1, paint );
        // 2. line
        float[] pointsLine2 = {
                leftSpace,
                chartHeight - bottomSpace,
                chartWidth,
                chartHeight - bottomSpace,
        };
        canvas.drawLines( pointsLine2, paint );



        super.onDraw(canvas);
    }

    // Add New Value.
    public void addChartValue( String name, int value, int color){

        valueList.add( new RingChartValue( name, value, color ));
        invalidate();

    }

    /**
     *  Get Top Value  for Bar Height.
     *  @return topValue getting top value
     */
    private int getTopValue(){
        int topValue = 0;
        for(int i=0; i<valueList.size(); i++){
            int value = valueList.get(i).getValue();
            if( value > topValue ) {
                topValue = value;
            }
        }
        return topValue;
    }

    /**
     * Calculate NameText Height.
     * @return getting text height
     */
    private float getTextHeight(){
        Rect textBounds = new Rect();
        textPaint.getTextBounds( "A", 0, 1, textBounds );
        return textBounds.height();
    }

    /**
     * Calculate Value Text Width.
     * @return getting width in pixels
     */
    private float getValueTextWidth(){
        //yazinin en buyuk genisligi aliniyor.
        String text = String.valueOf( getTopValue() );
        //new text bounds
        Rect textBounds = new Rect();
        //get bounds
        textPaint.getTextBounds( text, 0, text.length(), textBounds );
       //Return text width
        return textBounds.width();
    }

    /**
     * Set background Color
     * @param color  Color Value
     */
    private int backgroundColor = ChartColors.LIGHT_GREY;
    public void setBackgroundColor(int color){
        backgroundColor = color;
        invalidate();
    }

    /**
     * Set Lines Color.
     * @param color  Color Value
     */
    private int linesColor = ChartColors.CHART_LINE;
    public void setLinesColor(int color){
            linesColor = color;
    }

    /**
     * Delete Value with "id".
     * @param index list item index
     */
    public void deleteChartValue(int index){
        if ((index < valueList.size()) && (index >= 0)) {
            valueList.remove(index);
            invalidate();
        }
    }

    /**
     * Delete value with "name".
     * @param name list item name
     */
    public void deleteChartValue(String name){
        for( int i=0; i<valueList.size() ;i++ ){
            if(valueList.get(i).getName().equals( name )){
                valueList.remove(i);
                invalidate();
                break;
            }
        }
    }

    /**
     * Delete all values.
     * Byebye The Mamas & The Papas-California dreamin
     */
    public void deleteAllValue(){
        valueList.clear();
    }

    /**
     * Update Chart Values.
     * @param index which item
     * @param name  new bar Name
     * @param value new bar value
     * @param color new bar color
     */
    public void updateChartValue(int index,  String name, int value, int color){
        if ((index < valueList.size()) && (index >= 0)){
            valueList.get(index).setName(name);
            valueList.get(index).setValue(value);
            valueList.get(index).setColor(color);
            invalidate();
        }
    }

    /**
     *  Update Chart Values with RingChartValue.
     *  @param index which item
     *  @param ringValue new RingChartValue
     */
    public void updateChartValue(int index, RingChartValue ringValue){
        if ((index < valueList.size()) && (index >= 0)){
            valueList.get(index).setName(ringValue.getName());
            valueList.get(index).setValue(ringValue.getValue());
            valueList.get(index).setColor(ringValue.getColor());
            invalidate();
        }
    }

    /**
     * Settings Text Size. Default Value 25f
     * @param size  Text Size
     */
    public void setTextSize(float size){
        if(size > 0) {
            textPaint.setTextSize(size);
            invalidate();
        }
    }
}
