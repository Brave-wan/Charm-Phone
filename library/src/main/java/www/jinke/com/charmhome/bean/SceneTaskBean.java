package www.jinke.com.charmhome.bean;

import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.bo.SceneBind;

/**
 * Created by root on 18-3-7.
 */

public class SceneTaskBean {

    private Room room;
    private Device device;
    private boolean isShow;
    private SceneBind sceneBind;


    public void setSceneBind(SceneBind sceneBind) {
        this.sceneBind = sceneBind;
    }

    public SceneBind getSceneBind() {
        return sceneBind;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
