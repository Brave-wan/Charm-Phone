package www.jinke.com.charmhome.presenter.scene;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.bo.SceneBind;
import com.orvibo.homemate.bo.Timing;
import com.orvibo.homemate.data.DeviceOrder;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.bean.SceneTaskBean;
import www.jinke.com.charmhome.view.scene.IEditSceneView;

/**
 * Created by root on 18-3-16.
 */

public class EditScenePresent {
    private Context mContext;
    private IEditSceneView mView;

    public EditScenePresent(Context mContext, IEditSceneView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }


    public List<SceneTaskBean> getTaskDeviceList(Scene scene) {//获取该情景下面添加的设备
        List<SceneTaskBean> list = new ArrayList<>();
        List<SceneTaskBean> list1 = new ArrayList<>();
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

        List<SceneBind> sceneBinds = LocalDataApi.getSceneBindsByScene(scene.getSceneNo());
        for (int i = 0; i < sceneBinds.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getDevice().getDeviceId().equals(sceneBinds.get(i).getDeviceId())) {
                    list1.add(list.get(j));
                }
            }
        }
        return list1;
    }

    public List<SceneTaskBean> getListSorting(List<SceneTaskBean> list) {
        //判断list有没有添加默认添加功能
        if (list.size() > 0) {
            if (list.get(list.size() - 1).getDevice() != null) {
                list.add(new SceneTaskBean());
            }
        } else {
            list.add(new SceneTaskBean());
        }
        return list;
    }

    public void getSceneTimings(Scene scene) {
        List<Timing> list = LocalDataApi.getSceneTimings(FamilyManager.getCurrentFamilyId(), scene.getSceneNo());
    }




    /**
     * 添加情景绑定，即情景绑定相关设备和动作，每一个scenebind对应一个设备，此处测试为绑定一个普通灯设备
     *
     * @param list  设备列表
     * @param scene 　修改的情景
     */
    public void addSceneBind(List<SceneTaskBean> list, Scene scene) {
        if (mView != null) {
            mView.showLoading("");

            List<SceneBind> sceneBinds = new ArrayList<SceneBind>();
            for (int i = 0; i < list.size(); i++) {
                SceneTaskBean bean = list.get(i);
                SceneBind sceneBind = new SceneBind();
                //设置的延迟时间，比如灯延迟多久打开或者关闭，此处为1S，因为单位为100毫秒
                sceneBind.setDelayTime(10);
                //控制的order,灯关
                sceneBind.setCommand(DeviceOrder.OFF);
                //  sceneBind.setValue1(0); value字段根据order处提示进行填写，默认为0
                //根据列表顺序填唯一数字，绑定设备的结果里会返回此id，以确定哪些设备绑定成功
                sceneBind.setItemId(i);
                if (bean.getDevice() != null) {
                    Device device = LocalDataApi.getDeviceById(bean.getDevice().getDeviceId());
                    sceneBind.setUid(device.getUid());
                    sceneBind.setDeviceId(bean.getDevice().getDeviceId());
                    sceneBind.setSceneNo(scene.getSceneNo());
                    sceneBinds.add(sceneBind);
                }
            }

            SmartSceneApi.addSceneBind(Config.userName, scene.getSceneNo(), sceneBinds, new BaseResultListener() {
                @Override
                public void onResultReturn(BaseEvent baseEvent) {
                    if (baseEvent.isSuccess()) {
                        //结果返回成功不代表设备绑定成功，说明和主机通信成功，真正绑定成功的结果要在情景绑定添加接口中进行接收
                        LogUtils.i("添加情景绑定成功" + baseEvent.isSuccess());
                        if (baseEvent.isSuccess()) {
                            mView.onAlterScene();
                        } else {
                            ToastUtils.showShort("情景任务添加失败");
                        }
                    } else {
                        ToastUtils.showShort("操作失败");
                    }
                    mView.hideLoading();
                }
            });
            //回调结果
            addSceneBindReport();
        }
    }


    public static void addSceneBindReport() {
        SmartSceneApi.addSceneBindReport(new BaseResultListener.DataListListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent, Object[] datas) {
                if (baseEvent.isSuccess()) {
                    List<SceneBind> successList = (List<SceneBind>) datas[0];
                    List<SceneBind> failList = (List<SceneBind>) datas[1];
                    LogUtils.i("添加绑定返回结果成功");
                    if (successList != null) {
                        LogUtils.i("绑定成功设备个数=" + successList.size());
                    }
                    if (failList != null) {
                        LogUtils.i("绑定失败设备个数=" + failList.size());
                    }
                }
            }
        });
    }
}
