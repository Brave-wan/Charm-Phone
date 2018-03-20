package www.jinke.com.charmhome.ui.activity.family;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.adapter.RoomListAdapter;

/**
 * Created by root on 18-3-14.
 */

public class RoomManagerActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView lv_room_list;
    RoomListAdapter adapter;
    List<Room> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_room_manager;
    }

    @Override
    protected void initView() {
        setTitleText("房间管理", R.color.black);
        setLeftBackView(R.drawable.icon_charm_home_back);
        setRightVisibility("", R.drawable.icon_family_manager_add);
        setBaseTitleBack(R.color.white);
        lv_room_list.setOnItemClickListener(this);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }


    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        Intent intent = new Intent(this, AddRoomActivity.class);
//        intent.putExtra("room",)
        startActivity(intent);
    }

    @Override
    protected void findViewById() {
        lv_room_list = findViewById(R.id.lv_room_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = LocalDataApi.getAllRooms(FamilyManager.getCurrentFamilyId());
        adapter = new RoomListAdapter(this, R.layout.item_room_manager_list, list);
        lv_room_list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Room room = (Room) adapter.getItem(i);
        Intent intent = new Intent(this, EditRoomActivity.class);
        intent.putExtra("room", room);
        startActivity(intent);
    }


}
