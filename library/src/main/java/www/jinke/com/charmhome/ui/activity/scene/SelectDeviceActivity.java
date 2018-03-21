package www.jinke.com.charmhome.ui.activity.scene;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.bo.SceneBind;
import com.orvibo.homemate.data.DeviceOrder;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
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
    Scene scene;

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
        addSceneBind(list.get(i), scene);
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

    public void addSceneBind(SceneTaskBean bean, Scene scene) {
        //添加情景绑定，即情景绑定相关设备和动作，每一个scenebind对应一个设备，此处测试为绑定一个普通灯设备
//        list.remove(new SceneTaskBean());
        //去除重复绑定的数据
        List<SceneBind> sceneBinds = new ArrayList<SceneBind>();
        SceneBind sceneBind = new SceneBind();
        //设置的延迟时间，比如灯延迟多久打开或者关闭，此处为1S，因为单位为100毫秒
        sceneBind.setDelayTime(10);
        //控制的order,灯关
        sceneBind.setCommand(DeviceOrder.OFF);
        //  sceneBind.setValue1(0); value字段根据order处提示进行填写，默认为0
        //根据列表顺序填唯一数字，绑定设备的结果里会返回此id，以确定哪些设备绑定成功
        sceneBind.setItemId(0);
        if (bean.getDevice() != null) {
            Device device = LocalDataApi.getDeviceById(bean.getDevice().getDeviceId());
            sceneBind.setUid(device.getUid());
            sceneBind.setDeviceId(bean.getDevice().getDeviceId());
            sceneBind.setSceneNo(scene.getSceneNo());
            sceneBinds.add(sceneBind);
        }

        SmartSceneApi.addSceneBind(Config.userName, scene.getSceneNo(), sceneBinds, new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {
                if (baseEvent.isSuccess()) {
                    //结果返回成功不代表设备绑定成功，说明和主机通信成功，真正绑定成功的结果要在情景绑定添加接口中进行接收
                    LogUtils.i("添加情景绑定成功" + baseEvent.isSuccess());
                } else {
                    ToastUtils.showShort("主机不在线");
                }
            }
        });
    }
}
