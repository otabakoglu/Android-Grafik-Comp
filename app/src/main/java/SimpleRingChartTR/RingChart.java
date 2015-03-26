package SimpleRingChartTR;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cry on 21.03.2015.
 *
 * @author Ozan Tabakoğlu
 * @since 21.03.2015
 * @deprecated Simple Ring Chart
 */
public class RingChart extends View {

    private Paint paint;
    private float thickness;
    private Paint paintText;
    private Paint paintNameText;
    private float chartWidth;
    private float chartHeight;
    private float size;
    private float totalValue;
    private List<RingChartValue> listValues = new ArrayList<RingChartValue>();
    private int select = 0;
    private boolean textShow = true;


    //Constructor.
    public RingChart(Context context){ super(context); }
    public RingChart(Context context, AttributeSet paramAttributeSet)
    {         super(context, paramAttributeSet); }
    public RingChart(Context context, AttributeSet paramAttributeSet, int paramInt)
    {        super(context, paramAttributeSet, paramInt); }

    //Gerekli Ayarlamalar- Cizim sinifi, boyutlar, kenarliklar.
    private void init(){

        //Genislik/Yukseklik Al. get Width and Height.
        chartWidth  = getMeasuredWidth();
        chartHeight = getMeasuredHeight();
        //En kucuk olani al. get Min(Width, Height);
        size = Math.min( chartWidth, chartHeight );
        //boyuta gore kalinlik hesapla
        //Calculate thickness
        thickness = size / 3;

        //Paint
        paint = new Paint();
        // Kenarlik Kalinlik - Thickness
        paint.setStrokeWidth( thickness );
        //Kenarlik stili
        paint.setStyle(Paint.Style.STROKE );
        paint.setAntiAlias( true );

        //for Percent Text - Ortadaki Yuzde Yazisi icin
        paintText = new Paint();
        paintText.setAntiAlias( true );
        paintText.setTextSize( size/5 );
        paintText.setFakeBoldText( true );

        //Yuzde yazisinin altindaki isim icin.
        //for Name Text
        paintNameText = new Paint();
        paintNameText.setAntiAlias(true);
        paintNameText.setTextSize( size / 10 );
    }

    //Draw on canvas.
    @Override
    protected void onDraw(Canvas canvas) {

        // Hesapla  (Genislik, Yukseklik, Kenarlik,  Boyut)
        // Calculate(Width,    Height,    Thickness, size);
        init();

      //  addChartValue("Elma", 50, ChartColors.BLUE);
      //  addChartValue("Şeftali", 150, ChartColors.ORANGE);
       // addChartValue("Armut", 80, ChartColors.GREEN);
       // addChartValue("Kiraz", 10, ChartColors.GREY);

        //Toplam deger.
        totalValue = 0.0F;

        //Kenarlik Bosluklari - Margin
        RectF sizeRectF = new RectF(size / 15, size / 15, size - size / 15, size - size / 15);

        //baslangic acisi - Start Angle
        float startAngle = -90;

        //toplam deger hesaplaniyor. calculate Total Value.
        for (int j = 0; j<listValues.size(); j++) {
            totalValue += listValues.get(j).getValue();
        }

        //arkaplandaki halka icin - for background
        thickness = size / 11;
        paint.setStrokeWidth( thickness );
        paint.setColor( Color.parseColor("#F8F8F8") );
        canvas.drawArc( sizeRectF, 0, 360, false, paint );

        for (int i = 0; i<listValues.size(); i++){

            //degerin karsiligi aci olarak hesaplaniyor.
            //calculate angle value.
            float angleValue = 360 *   listValues.get(i).getValue() / totalValue;

            //yuzde olarak hesaplama. Percent Value
            float percentValue = 100 * listValues.get(i).getValue() / totalValue;

            //aci 1 den kucukse 1 yap.
            if (angleValue < 1.0F) {
                angleValue = 1.0F;
            }
            //iki aci arasindaki bosluklar icin.
            // space  two angle between.
            if (listValues.size() >1)
            {
                startAngle += 5; // bastan bosluk - start space
                angleValue -= 5.0F; //sondan bosluk - end space
            }

            // Kenarlik Cizimi için Secilen renklerin Yerine Koyu Renkleri Ayarlaniyor.
            // Dark Color for draw thickness.
            paint.setColor( ChartColors.getDarkColor( listValues.get(i).getColor() ) );

            //ilk cizim grafik kenarligi icin.
            //first draw for thickness.
            thickness = size / 11;
            paint.setStrokeWidth( thickness );
            canvas.drawArc( sizeRectF, startAngle - 1, angleValue + 2.0F, false, paint );

            //ikinci cizim grafik ici icin.
            thickness = size / 13;
            paint.setStrokeWidth( thickness );
            //normal rengi aliniyor.- get Normal Color.
            paint.setColor(listValues.get(i).getColor());
            canvas.drawArc( sizeRectF, startAngle, angleValue, false, paint );

            //Yazi Renkler Ayarlaniyor. - Set Text Color.
            paintText.setColor(paint.getColor());
            paintNameText.setColor(paint.getColor());

            if( textShow && select == i){

                //Ortadaki yuzde yazisi icin ayarlamalar.
                //Text size Rect.
                Rect textSizeRect = new Rect();

                //
                String formatText = "%" + String.format("%.1f", percentValue);

                //yazinin uzunluguna gore boyutunu hesaplama
                //calculate Text Bounds by formatText Count
                paintText.getTextBounds(
                        formatText ,0,
                        formatText.length(),
                        textSizeRect);

                //Merkeze Hizalamak icin.
                float textMarginCenter = (size / 2) - (textSizeRect.width() / 2);

                //Yaziyi Ekrana Ciz. Draw Text.
                canvas.drawText( formatText ,
                        textMarginCenter,
                        size / 2 + textSizeRect.height()/2,
                        paintText );

                //Yuzdeninin altindaki Isim Icin Yazi ayarlamalari.
                //get Name Text.
                // Orn:             Exp:
                //    %5                %15
                //  Araba              Cars

                //yazi
                String nameText = listValues.get(i).getName();

                //karakter sayisini al. yaziyi merkeze yerlestirmek icin.
                // get  Character Count for align center
                int characterCount = nameText.length();

                //yazi boyutu
                Rect nameTextSizeRect = new Rect();

                paintNameText.getTextBounds( nameText,
                        0,
                        characterCount,
                        nameTextSizeRect );

                float nameTextMarginCenter = (size / 2) - (nameTextSizeRect.width()/2);

                canvas.drawText( nameText,
                        nameTextMarginCenter,
                        size / 2 + textSizeRect.height() + nameTextSizeRect.height()/3,
                        paintNameText
                );

            }

            startAngle += angleValue;
        }

        super.onDraw(canvas);
    }

    //Deger Ekleme - Add Item.
    public void addChartValue( String name, int value, int color){
        listValues.add( new RingChartValue( name, value, color ));
        invalidate();
    }

    //liste seklinde deger ekleme - add values list
    public void addChartValues(List<RingChartValue> values){
        listValues.addAll( values );
        invalidate();
    }

    //id ile Deger Silme - Delete Item by id.
    public void deleteChartValue( int index){
        if ((index < listValues.size()) && (index >= 0)){
            listValues.remove(index);
            invalidate();
        }
    }
    //isim ile Deger Silme - Delete Item by name.
    public void deleteChartValue( String name ){
        //find name
        for( int i=0; i<listValues.size() ;i++ ){
            if(listValues.get(i).getName().equals( name )){
                listValues.remove(i);
                invalidate();
                break;
            }
        }
    }
    //butun degerleri sil - delete All Values.
    public void deleteAllValue(){
        listValues.clear();
    }

    //degerleri degistir.
    public void updateChartValue(int id,  String name, int value, int color){
        if ((id < listValues.size()) && (id >= 0)){
            listValues.get(id).setName(name);
            listValues.get(id).setValue(value);
            listValues.get(id).setColor(color);
            invalidate();
        }
    }

    public void updateChartValue(int id, RingChartValue ringValue){
        if ((id < listValues.size()) && (id >= 0)){
            listValues.get(id).setName(ringValue.getName());
            listValues.get(id).setValue(ringValue.getValue());
            listValues.get(id).setColor(ringValue.getColor());
            invalidate();
        }
    }

    //ekranda gorulecek yaziyi secer. select center text.
    public void selectValue(int id){

        if ((id < listValues.size()) && (id >= 0)){
            select = id;
            invalidate();
        }

    }

    //istenilen degeri yuzde seklinde alma. -get percent value
    public float getPercent(int id){

        if ((id < listValues.size()) && (id >= 0)) {

            float value = listValues.get(id).getValue();
            float percent = 100 * value / totalValue;
            return percent;
        }
        return 0.0F;
    }

    //Yazi gizlenebilir olmali.- show or hidden Text
    public void setTextShow(boolean mShow){
        textShow = mShow;
        invalidate();
    }
}
