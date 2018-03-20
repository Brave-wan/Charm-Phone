package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.orvibo.homemate.bo.Device;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneTaskBean;

/**
 * Created by root on 18-3-15.
 */

public class SelectDeviceAdapter extends CommonAdapter<SceneTaskBean> {
    Context context;

    public SelectDeviceAdapter(@NonNull Context context, int layoutResId, @NonNull List<SceneTaskBean> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SceneTaskBean bean, int position) {
        Device device = bean.getDevice();
        TextView tv_device_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_device_name);
        ImageView img_select_device_icon = (ImageView) baseViewHolder.getViewByViewId(R.id.img_select_device_icon);
        ImageView img_select_state = (ImageView) baseViewHolder.getViewByViewId(R.id.img_select_state);
        TextView tv_room_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_room_name);
        tv_device_name.setText(device.getDeviceName() + device.getDeviceType());
        tv_room_name.setText(bean.getRoom().getRoomName());
        img_select_state.setBackground(context.getResources().getDrawable(bean.isShow() ? R.drawable.icon_device_select_rbt1_ : R.drawable.icon_device_select_rbt0));
        setIcon(device.getDeviceType(), img_select_device_icon);
    }

    public void setIcon(int type, ImageView imageView) {
        /**
         * 设备类型 0：调光灯、 1：普通灯光、 2：插座、 3：幕布、 4：百叶窗、5：空调、
         * 6：电视； 7：音箱； 8：对开窗帘 10：开关型继电器；11：红外转发器；
         * 14：摄像头； 15：情景面板；16：遥控器；17：中继器；18：亮度传感器;
         * 19：RGB灯；21:门锁;22:温度传感器；23：湿度传感器;24:空气质量传感器;
         * 25:可燃气体传感器;26:红外人体传感器;27:烟雾传感器;28:报警设备；
         * 29：S20；30：Allone；32：机顶盒；33：自定义红外；34：对开窗帘（支持按照百分比控制）；
         * 35：卷帘（支持按照百分比控制）；36：空调面板；37：推窗器；38：色温灯；39：卷闸门；
         * 41：推窗器(已不用)；42：卷帘（无百分比）；43：单控排插；44：vicenter300主机；
         * 45：miniHub；46：门磁；47：窗磁；48：抽屉磁；49：其他类型的门窗磁；
         * 50：情景面板（5键）；51：情景面板（7键）；52：晾衣架；54：水浸传探测器；
         * 55：一氧化碳报警器；56：紧急按钮
         */
        switch (type) {
            case 0://调光灯
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_lamp));
                break;

            case 1://普通灯光
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_lamp));
                break;

            case 2://插座
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_sockt));
                break;
            case 3://幕布
                break;
            case 4://百叶窗
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_curtain));
                break;
            case 5://空调
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_air_condition));
                break;
            case 6://电视
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_tv));
                break;
            case 34:
                imageView.setBackground(context.getResources().getDrawable(R.drawable.icon_device_select_curtain));
                break;
        }

    }

    public void setAll(boolean isSelect) {
        for (SceneTaskBean bean : dataList) {
            bean.setShow(isSelect);
        }
        notifyDataSetChanged();
    }
}
