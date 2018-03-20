package www.jinke.com.charmhome.bean;

import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;

/**
 * Created by root on 18-3-7.
 */

public class SceneTaskBean {

    private Room room;
    private Device device;
    private boolean isShow;

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
