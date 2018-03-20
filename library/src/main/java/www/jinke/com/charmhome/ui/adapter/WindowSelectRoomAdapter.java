package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.orvibo.homemate.bo.Room;

import java.util.List;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-13.
 */

public class WindowSelectRoomAdapter extends CommonAdapter<Room> {
    Context context;

    public WindowSelectRoomAdapter(@NonNull Context context, int layoutResId, @NonNull List<Room> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Room room, int position) {
        TextView tv_window_select_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_window_select_name);
        ImageView img_window_select_room = (ImageView) baseViewHolder.getViewByViewId(R.id.img_window_select_room);
        img_window_select_room.setBackground(context.getResources().getDrawable(room.getImgUrl() == null ? R.drawable.iocn_window_select_room1 : R.drawable.icon_window_select_room0));
        tv_window_select_name.setText(room.getRoomName());
        tv_window_select_name.setTextColor(context.getResources().getColor(room.getImgUrl() == null ? R.color.charm_home_house_window : R.color.charm_home_house_home_energy));

    }
}
