package www.jinke.com.charmhome.ui.activity.scene;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneTaskBean;
import www.jinke.com.charmhome.ui.adapter.SelectDeviceAdapter;

/**
 * Created by root on 18-3-15.
 */

public class SelectDeviceActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView lv_device_list;
    private SelectDeviceAdapter adapter;
    private TextView tv_device_select;
    private ImageView img_device_select_back;
    private List<SceneTaskBean> list = new ArrayList<>();
    private Scene scene;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_device;
    }

    @Override
    protected void initView() {
        setTitleVisibility(false);
    }

    @Override
    protected void findViewById() {
        scene = (Scene) getIntent().getSerializableExtra("scene");
        lv_device_list = findViewById(R.id.lv_device_list);
        tv_device_select = findViewById(R.id.tv_device_select);
        img_device_select_back = findViewById(R.id.img_device_select_back);
        list = getTaskDeviceList();
        adapter = new SelectDeviceAdapter(this, R.layout.item_device_select, list);
        lv_device_list.setAdapter(adapter);

        lv_device_list.setOnItemClickListener(this);
        img_device_select_back.setOnClickListener(this);
        tv_device_select.setOnClickListener(this);
    }


    public List<SceneTaskBean> getTaskDeviceList() {
        List<SceneTaskBean> list = new ArrayList<>();
        List<Room> rooms = LocalDataApi.getAllRooms(FamilyManager.getCurrentFamilyId());
        for (Room room : rooms) {
            List<Device> linkageDevices = LocalDataApi.getSupportSceneDevices(room.getRoomId());
            for (Device device : linkageDevices) {
                SceneTaskBean bean = new SceneTaskBean();
                bean.setRoom(room);
                bean.setDevice(device);
                list.add(bean);
            }
        }

        List<SceneTaskBean> edit = EditSceneActivity.list;
        if (edit.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                SceneTaskBean sceneTaskBean = list.get(i);
                for (int j = 0; j < edit.size(); j++) {
                    SceneTaskBean beans = edit.get(j);
                    if (beans.getDevice() != null && sceneTaskBean.getDevice().getDeviceId()
                            .equals(beans.getDevice().getDeviceId())) {
                        sceneTaskBean.setShow(true);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        list = adapter.dataList;
        boolean isSelect = list.get(i).isShow();
        list.get(i).setShow(isSelect ? false : true);
        adapter.setDataList(list);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_device_select) {
            boolean isSelect = tv_device_select.getText().toString().trim().equals("全选");
            adapter.setAll(isSelect);
            tv_device_select.setText(isSelect ? "取消全选" : "全选");
        } else if (R.id.img_device_select_back == view.getId()) {
            List<SceneTaskBean> taskBeans = new ArrayList<>();
            for (SceneTaskBean bean : adapter.dataList) {
                if (bean.isShow()) {
                    taskBeans.add(bean);
                }
            }
            EditSceneActivity.list = taskBeans;
            finish();
        }
    }
}
