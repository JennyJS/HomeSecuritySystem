package sensor;

/**
 * Created by manhongren on 6/3/17.
 */
public class Sensor {
    private String id;
    private boolean isOn;
    public Sensor(String id, boolean isOn){
        this.id = id;
        this.isOn = isOn;
    }
    public void setSensorOn(boolean isOn){
        this.isOn = isOn;
    }
}
