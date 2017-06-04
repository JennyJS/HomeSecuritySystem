package sensor;

/**
 * Created by manhongren on 6/3/17.
 */
public class Sensor {
    private String id;
    private boolean isOn;
    private Type type;

    public enum Type{
        FIRE,
        BREAKIN;
    }
    public Sensor(String id, boolean isOn, Type type){
        this.id = id;
        this.isOn = isOn;
        this.type = type;
    }
    public void setSensorOn(boolean isOn){
        this.isOn = isOn;
    }
}
