package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orvibo.homemate.bo.Room;

import java.util.List;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-14.
 */

public class RoomListAdapter extends CommonAdapter<Room> {
    public RoomListAdapter(@NonNull Context context, int layoutResId, @NonNull List<Room> dataList) {
        super(context, layoutResId, dataList);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Room room, int position) {
        TextView tv_room_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_room_name);
        ImageView img_room_arrow = (ImageView) baseViewHolder.getViewByViewId(R.id.img_room_arrow);
        tv_room_name.setText(room.getRoomName());
        img_room_arrow.setVisibility(room.getRoomName().equals("默认房间") ? View.GONE : View.VISIBLE);

    }
}
