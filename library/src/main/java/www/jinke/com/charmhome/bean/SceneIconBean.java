package www.jinke.com.charmhome.bean;

/**
 * Created by root on 18-3-19.
 */

public class SceneIconBean {
    private int src;
    private boolean isShow;
    private int type;

    public SceneIconBean(int src, boolean isShow, int type) {
        this.src = src;
        this.isShow = isShow;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public boolean isShow() {
        return isShow;
    }

    public int getSrc() {
        return src;
    }
}
