package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.dao.RoomDao;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.List;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-13.
 */

public class HouseDeviceListAdapter extends CommonAdapter<Device> {
    Context context;

    public HouseDeviceListAdapter(@NonNull Context context, int layoutResId, @NonNull List<Device> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Device device, int position) {
        TextView tv_device_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_device_name);
        ImageView img_device = (ImageView) baseViewHolder.getViewByViewId(R.id.img_device);
        TextView tv_room_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_room_name);
        ImageView img_device_open_state = (ImageView) baseViewHolder.getViewByViewId(R.id.img_device_open_state);
        RelativeLayout rl_device_add = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_device_add);
        RelativeLayout rl_device_details = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_device_details);
        rl_device_details.setVisibility(device.getDeviceName() != null ? View.VISIBLE : View.GONE);
        rl_device_add.setVisibility(device.getDeviceName() != null ? View.GONE : View.VISIBLE);

        if (device.getDeviceName() != null) {
            String[] roomName = RoomDao.getInstance().selFloorNameAndRoomName(device.getRoomId(), FamilyManager.getCurrentFamilyId());
            LogUtils.i("room" + roomName[1] + "device" + device.getDeviceName());
            tv_device_name.setText(device.getDeviceName());
            if (roomName != null && roomName.length > 1) {
                tv_room_name.setText(roomName[1] == null ? "默认房间" : roomName[1]);
            }
        }

    }
}
