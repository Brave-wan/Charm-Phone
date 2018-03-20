package www.jinke.com.charmhome.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.FamilyApi;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Family;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.event.family.QueryFamilyEvent;
import com.orvibo.homemate.model.family.FamilyManager;
import com.orvibo.homemate.sharedPreferences.UserCache;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.activity.family.FamilyManagerActivity;
import www.jinke.com.charmhome.ui.adapter.WindowSelectHouseAdapter;
import www.jinke.com.charmhome.ui.adapter.WindowSelectRoomAdapter;

/**
 * Created by root on 18-3-13.
 */

public class SelectHouseWindow extends PopupWindow {
    private Context mContext;
    private PopupWindow window;
    private LayoutInflater inflater;
    private ListView lv_house_list, lv_room_list;
    private TextView tv_family_manager;
    private WindowSelectHouseAdapter adapter;
    private WindowSelectRoomAdapter roomAdapter;
    private List<Room> rooms = new ArrayList<>();
    private List<Family> familyList = new ArrayList<>();

    private OnSelectHouseListener listener;

    public void setOnSelectHouseListener(OnSelectHouseListener listener) {
        this.listener = listener;
    }

    public SelectHouseWindow(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        rooms = LocalDataApi.getAllRooms(FamilyManager.getCurrentFamilyId());
        if (rooms.size() > 0) {
            rooms.get(0).setImgUrl("1");
        }
        queryFamily();
        initView();
    }

    private void queryFamily() {
        String userId = UserCache.getUserId(mContext, Config.userName);
        FamilyApi.queryFamilys(userId, new BaseResultListener.DataListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent, Object o) {
                if (baseEvent.isSuccess()) {
                    QueryFamilyEvent queryEvent = (QueryFamilyEvent) baseEvent;
                    if (queryEvent.getFamilyList() != null) {
                        familyList.addAll(queryEvent.getFamilyList());
                        familyList.get(0).setPic("1");
                        adapter.setDataList(familyList);
                    }
                }
            }
        });
    }

    private void initView() {
        View view = inflater.inflate(R.layout.window_select_house, null);
        window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        lv_house_list = view.findViewById(R.id.lv_house_list);
        lv_room_list = view.findViewById(R.id.lv_room_list);
        tv_family_manager = view.findViewById(R.id.tv_family_manager);

        adapter = new WindowSelectHouseAdapter(mContext, R.layout.item_window_select_house, familyList);
        lv_house_list.setAdapter(adapter);

        roomAdapter = new WindowSelectRoomAdapter(mContext, R.layout.window_select_room, rooms);
        lv_room_list.setAdapter(roomAdapter);
        initListener();
    }

    private void initListener() {
        lv_room_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<Room> rooms = roomAdapter.dataList;
                //选择房屋
                for (int k = 0; k < rooms.size(); k++) {
                    rooms.get(k).setImgUrl(null);
                }
                rooms.get(i).setImgUrl("1");
                roomAdapter.setDataList(rooms);
                if (listener != null) {
                    listener.onSelectResult(rooms.get(i));
                    dismiss();
                }
            }
        });


        lv_house_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String familyId = adapter.dataList.get(i).getFamilyId();
                switchFamily(familyId, i);
            }
        });
        tv_family_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, FamilyManagerActivity.class));
                dismiss();
            }
        });
    }

    //切换家庭
    public void switchFamily(String family, final int position) {
        FamilyApi.switchFamily(Config.userName, Config.userPwd, family, new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {
                if (baseEvent.isSuccess()) {
                    List<Family> familyList = adapter.dataList;
                    for (int j = 0; j < familyList.size(); j++) {
                        familyList.get(j).setPic(null);
                    }
                    familyList.get(position).setPic("1");
                    adapter.setDataList(familyList);
                    //刷新房间列表
                    rooms = LocalDataApi.getAllRooms(FamilyManager.getCurrentFamilyId());
                    if (rooms.size() > 0) {
                        rooms.get(0).setImgUrl("");
                    }
                    roomAdapter.setDataList(rooms);
                } else {
                    ToastUtils.showShort("主机不在线");
                }
            }
        });
    }

    public void show(View view) {
        if (window != null && window.isShowing()) {
            window.dismiss();
        }
        window.showAsDropDown(view);
    }

    public void dismiss() {
        if (window != null) {
            window.dismiss();
        }
    }

    public interface OnSelectHouseListener {
        void onSelectResult(Room room);
    }
}
