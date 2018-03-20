package www.jinke.com.charmhome.presenter.scene;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.TimerApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Timing;
import com.orvibo.homemate.event.BaseEvent;

import java.util.Calendar;
import java.util.List;

import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.TimingBean;
import www.jinke.com.charmhome.view.scene.ITimeSelectView;

/**
 * Created by root on 18-3-19.
 */

public class TimeSelectPresent implements TimePicker.OnWheelListener {
    private Activity mContext;
    private ITimeSelectView mView;

    public TimeSelectPresent(Activity mContext, ITimeSelectView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }


    /**
     * 初始化时间控件实体
     *
     * @param bean
     * @param viewGroup
     */
    public void initTimePicker(Timing bean, LinearLayout viewGroup) {
        TimePicker picker = new TimePicker(mContext, TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setTextPadding(ConvertUtils.toPx(mContext, 15));
        picker.setLabelTextColor(mContext.getResources().getColor(R.color.charm_home_house_txt));
        picker.setTextColor(mContext.getResources().getColor(R.color.charm_home_house_txt));
        picker.setDividerVisible(false);
        picker.setTextSize(30);
        picker.setOnWheelListener(this);
        if (bean.getMinute() != 0) {
            picker.setSelectedItem(bean.getHour(), bean.getMinute());
            mView.onBinaryString(bean.getWeek());
        }

        //得到选择器视图，可内嵌到其他视图容器，不需要调用show方法
        View pickerContentView = picker.getContentView();
        viewGroup.addView(pickerContentView);
    }

    public void getBinaryString(List<TimingBean> list, int weak, TextView tv_time_repeat_number) {
        String value = Integer.toBinaryString(weak);
        LogUtils.i("value:" + value);
        char[] time = value.toCharArray();
        tv_time_repeat_number.setText(String.valueOf(time[0]).equals("1") ? "单次" : "每天");
        for (int i = 1; i < time.length; i++) {
            switch (i) {
                case 1:
                    list.add(new TimingBean("日", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 2:
                    list.add(new TimingBean("六", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 3:
                    list.add(new TimingBean("五", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 4:
                    list.add(new TimingBean("四", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 5:
                    list.add(new TimingBean("三", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 6:
                    list.add(new TimingBean("二", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
                case 7:
                    list.add(new TimingBean("一", String.valueOf(time[i]).equals("1") ? true : false));
                    break;
            }
        }
        mView.onAdapterRefresh(list);
    }

    public void getModifyTimingTask(Timing bean) {
        if (mView != null) {
            mView.showLoading("");
            TimerApi.modifyTimingTask(Config.userName, bean.getUid(), bean, new BaseResultListener() {
                @Override
                public void onResultReturn(BaseEvent baseEvent) {
                    LogUtils.i("baseEvent" + baseEvent.getResult());
                    if (baseEvent.isSuccess()) {
                        ToastUtils.showShort("修改成功");
                    }
                    mView.hideLoading();
                }
            });
        }

    }


    @Override
    public void onHourWheeled(int i, String s) {
        mView.onHourWheeled(i);
    }

    @Override
    public void onMinuteWheeled(int i, String s) {
        mView.onMinuteWheeled(i);
    }
}
