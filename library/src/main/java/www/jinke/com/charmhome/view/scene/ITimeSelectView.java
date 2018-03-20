package www.jinke.com.charmhome.view.scene;

import java.util.List;

import www.jinke.com.charmhome.bean.TimingBean;
import www.jinke.com.charmhome.view.BaseView;

/**
 * Created by root on 18-3-19.
 */

public interface ITimeSelectView extends BaseView {
    void onBinaryString(int weak);

    void onAdapterRefresh(List<TimingBean> list);

    void onHourWheeled(int i);

    void onMinuteWheeled(int i);
}
