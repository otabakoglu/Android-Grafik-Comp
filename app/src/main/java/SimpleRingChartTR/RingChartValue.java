package SimpleRingChartTR;

/**
 * Created by Cry on 21.03.2015.
 */
public class RingChartValue {

    private String name;
    private int value;
    private int color;

    public RingChartValue( String name, int value, int color ){

        this.name = name;
        this.color = color;
        this.value = value;

    }

    public void setName(String name) {
        this.name = name;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public String getName(){
        return name;
    }
    public int getValue(){
        return value;
    }
    public int getColor(){
        return color;
    }


}
