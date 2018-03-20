package www.jinke.com.charmhome.bean;

/**
 * Created by root on 18-3-16.
 */

public class TimingBean {
    private String name;
    private boolean state;
    private int value;

    public TimingBean(String name, boolean state, int value) {
        this.name = name;
        this.value = value;
        this.state = state;
    }

    public TimingBean(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public boolean isState() {
        return state;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
