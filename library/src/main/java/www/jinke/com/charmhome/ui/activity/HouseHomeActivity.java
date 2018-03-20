package www.jinke.com.charmhome.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.dao.RoomDao;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.model.family.FamilyManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.presenter.HouseHomePresent;
import www.jinke.com.charmhome.ui.adapter.HouseDeviceListAdapter;
import www.jinke.com.charmhome.ui.dialog.SelectHouseWindow;
import www.jinke.com.charmhome.widget.MyGridView;

/**
 * Created by root on 18-3-13.
 */

public class HouseHomeActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener,
        SelectHouseWindow.OnSelectHouseListener, AdapterView.OnItemClickListener {
    ImageView img_house_home_back, img_family_more;
    MyGridView gv_house_device_list;
    SmartRefreshLayout smart_layout;
    TextView tv_house_name, tv_home_scene_more;
    RelativeLayout rl_house_title;
    HouseDeviceListAdapter deviceListAdapter;
    private List<Device> devices = new ArrayList<>();
    HouseHomePresent present;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_house_home;
    }

    @Override
    protected void initView() {
        setTitleVisibility(false);
        smart_layout.setOnRefreshListener(this);
        deviceListAdapter = new HouseDeviceListAdapter(this, R.layout.item_house_device_list, devices);
        gv_house_device_list.setAdapter(deviceListAdapter);
    }

    @Override
    protected void findViewById() {
        present = new HouseHomePresent(this);
        img_house_home_back = findViewById(R.id.img_house_home_back);
        gv_house_device_list = findViewById(R.id.gv_house_device_list);
        smart_layout = findViewById(R.id.smart_layout);
        tv_house_name = findViewById(R.id.tv_house_name);
        rl_house_title = findViewById(R.id.rl_house_title);
        tv_home_scene_more = findViewById(R.id.tv_home_scene_more);
        tv_house_name.setOnClickListener(this);
        tv_home_scene_more.setOnClickListener(this);
        gv_house_device_list.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        //获取当前家庭中的默认房屋
        Room room = RoomDao.getInstance().selFamilyDefaultRoom(FamilyManager.getCurrentFamilyId());
        devices = LocalDataApi.getDevicesByRoom(FamilyManager.getCurrentFamilyId(), room.getRoomId());
        devices.add(new Device());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_house_home_back) {
            finish();
        } else if (view.getId() == R.id.tv_house_name) {
            SelectHouseWindow window = new SelectHouseWindow(this);
            window.setOnSelectHouseListener(this);
            window.show(rl_house_title);
        } else if (view.getId() == R.id.tv_home_scene_more) {
            startActivity(new Intent(this, ScenesManagerActivity.class));
        } else if (view.getId() == R.id.img_family_more) {

        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        smart_layout.finishRefresh(2000);
    }

    @Override
    public void onSelectResult(Room room) {
        //刷新设备列表
        devices = LocalDataApi.getDevicesByRoom(FamilyManager.getCurrentFamilyId(), room.getRoomId());
        devices.add(new Device());
        deviceListAdapter.setDataList(devices);
        tv_house_name.setText(FamilyManager.getCurrentFamilyName());
        //刷新场景列表
        //刷新未关灯量及能耗数据
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Scene item = (Scene) deviceListAdapter.getItem(i);
//        SmartSceneApi.controlScene(Config.userName, item.getSceneNo(), new BaseResultListener() {
//            @Override
//            public void onResultReturn(BaseEvent baseEvent) {
//                LogUtils.i(baseEvent.getResult());
//                ToastUtils.showShort(baseEvent.isSuccess() ? "操作成功" : "主机不在线");
//            }
//        });

    }
}
