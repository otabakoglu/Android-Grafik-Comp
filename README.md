##Android Daire Grafik Kütüphanesi##
<p>Android için Basit Daire Grafik Kütüphanesi.
Bu Kütüphaneyi bir alışverip listesi programı ya da Hesap takip programı içinde kolaylıkla kullanabilirsiniz. 
Renk seçenekleri mevcuttur.</p>
=========
####Örnek Resimler:

<img src="https://raw.githubusercontent.com/otabakoglu/Android-Grafik-Comp/master/Images/noname.png" width="327px" height="338px" />
<img src="https://raw.githubusercontent.com/otabakoglu/Android-Grafik-Comp/master/Images/noname2.png" width="327px" height="326px" />

####Kullanımı:
___________
######XML:
```xml
<view
        android:id="@+id/chart"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        class="SimpleRingChartTR.RingChart"
       />
```
###### Java

```java
    RingChart mRingChart = (RingChart) findViewById( R.id.chart );
    
    
    mRingChart.addChartValue("Elma", 50, ChartColors.RED);
    mRingChart.addChartValue("Şeftali", 150, ChartColors.ORANGE);
    mRingChart.addChartValue("Armut", 80, ChartColors.GREEN);
    mRingChart.addChartValue("Kiraz", 10, ChartColors.GREY);
```

<br>

#####Yeni Değer Ekleme
```java
    mRingChart.addChartValue("Elma", 50, ChartColors.RED);
```

<br>

#####Liste Biçiminde Birden Fazla Değer Ekleme
```java
     List<RingChartValue> values = new ArrayList<RingChartValue>();
     values.add( new RingChartValue("Elma", 50, ChartColors.RED));
     values.add( new RingChartValue("Armut", 100, ChartColors.ORANGE));
     mRingChart.addChartValues(values);
```
<br>

#####Deger Silme - id ile
```java
  mRingChart.deleteChartValue( 0 );
```
<br>

#####Deger Silme - isim ile
 ```java
 mRingChart.deleteChartValue( "Elma" );
 ```
 
 <br>
 
#####Bütün Değerleri Siler
 ```java
mRingChart.deleteAllValue();
 ```
 <br>
#####index ile Değer Güncelleme
 ```java
  mRingChart.updateChartValue( 0,"Elma", 50, ChartColors.RED );
  mRingChart.updateChartValue( 0,new RingChartValue("Şeftali", 80, ChartColors.GREEN) );
```
 <br>
#####Yazıda Gösterilecek Değeri Seçme - index ile
```java
  mRingChart.selectValue(2);
```
<br>
#####Yaziyi Göster / Gizle
```java
   mRingChart.setTextShow( true );
```


##Güncellemeler

26.03.2015 14:16 - Yeni Rekler Eklendi, Material Design Colors (18).
