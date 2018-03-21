package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Device;
import com.orvibo.homemate.bo.SceneBind;
import com.orvibo.homemate.data.DeviceOrder;
import com.orvibo.homemate.event.BaseEvent;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneTaskBean;
import www.jinke.com.charmhome.ui.activity.scene.SceneActionActivity;
import www.jinke.com.charmhome.ui.activity.scene.SceneDelayActivity;
import www.jinke.com.charmhome.ui.activity.scene.SecurityDelayActivity;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-3-15.
 */

public class SceneTaskListAdapter extends CommonAdapter<SceneTaskBean> {

    private Context context;

    public SceneTaskListAdapter(@NonNull Context context, int layoutResId, @NonNull List<SceneTaskBean> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final SceneTaskBean bean, int position) {
        Device device = bean.getDevice();
        ImageView img_red_line = (ImageView) baseViewHolder.getViewByViewId(R.id.img_edit_scene_red_line);
        TextView tv_device_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_device_name);
        TextView tv_device_room_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_device_room_name);
        RelativeLayout rl_edit_scene_content = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_edit_scene_content);
        RelativeLayout rl_task_add = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_task_add);
        ImageView img_add_line_red = (ImageView) baseViewHolder.getViewByViewId(R.id.img_add_line_red);
        ImageView img_scene_delete = (ImageView) baseViewHolder.getViewByViewId(R.id.img_scene_delete);
        TextView tv_scene_time = (TextView) baseViewHolder.getViewByViewId(R.id.tv_scene_time);
        TextView tv_scene_action_type = (TextView) baseViewHolder.getViewByViewId(R.id.tv_scene_action_type);

        RelativeLayout rl_scene_action = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_scene_action);
        RelativeLayout rl_scene_delay = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_scene_delay);
        rl_edit_scene_content.setVisibility(device != null ? View.VISIBLE : View.GONE);
        rl_task_add.setVisibility(device != null ? View.GONE : View.VISIBLE);
        img_add_line_red.setVisibility(device == null ? (position == 0 ? View.VISIBLE : View.GONE) : View.GONE);

        if (device != null) {
            //设置情景设备名称
            tv_device_name.setText(device.getDeviceName());
            img_red_line.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            setIcon(device.getDeviceType(), tv_device_name);
            tv_device_room_name.setText(bean.getRoom().getRoomName());
        }
        //设置情景执行时间和执行动作
        if (bean.getSceneBind() != null) {
            tv_scene_time.setText(bean.getSceneBind().getDelayTime() + "秒后");
            tv_scene_action_type.setText(bean.getSceneBind().getActionType() + "");
        }

        rl_task_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onResult();
            }
        });

        img_scene_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBinding(bean);
            }
        });

        rl_scene_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SceneActionActivity.class));

            }
        });
        rl_scene_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SceneDelayActivity.class));
            }
        });
    }

    public void showDialog(final SceneTaskBean bean) {
        //删除设备提示对话框
        LogoutDialog dialog = new LogoutDialog(context);
        dialog.show();
        dialog.setLogOutContent("是否要删除该设备");
        dialog.setTitleText("提示");
        dialog.setLeftText("取消", R.color.charm_home_welcome_gray_tx);
        dialog.setRightText("删除", R.color.charm_home_default_red);
        dialog.setOnLogoutListener(new LogoutDialog.onLogoutListener() {
            @Override
            public void onLeftClick() {
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    OnEditSceneListener listener;

    public void setOnEditSceneListener(OnEditSceneListener listener) {
        this.listener = listener;
    }


    public interface OnEditSceneListener {
        void onResult();

        void onDeleteScene(SceneTaskBean bean);
    }


    public void setIcon(int type, TextView imageView) {
        Drawable drawable = null;
        switch (type) {
            case 0://调光灯
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_lamp_red);
                break;

            case 1://普通灯光
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_lamp_red);
                break;

            case 2://插座
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_socket_red);
                break;
            case 3://幕布
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_lamp_red);
                break;
            case 4://百叶窗
                drawable = context.getResources().getDrawable(R.drawable.icon_edit_scene_curtain);
                break;
            case 5://空调
                drawable = context.getResources().getDrawable(R.drawable.icon_edit_scene_aircondition);
                break;
            case 6://电视
                drawable = context.getResources().getDrawable(R.drawable.icon_edit_scene_tv);
                break;
            case 34:
                drawable = context.getResources().getDrawable(R.drawable.icon_edit_scene_curtain);
                break;
        }
        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
        imageView.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
    }

    public void deleteBinding(final SceneTaskBean bean) {
        List<SceneBind> sceneBinds = new ArrayList<SceneBind>();
        SceneBind sceneBind = new SceneBind();
        //设置的延迟时间，比如灯延迟多久打开或者关闭，此处为1S，因为单位为100毫秒
        sceneBind.setDelayTime(10);
        //控制的order,灯关
        sceneBind.setCommand(DeviceOrder.OFF);
        //  sceneBind.setValue1(0); value字段根据order处提示进行填写，默认为0
        //根据列表顺序填唯一数字，绑定设备的结果里会返回此id，以确定哪些设备绑定成功
        String sceneNo = SharedPreferencesUtils.init(context).getString("scene_no1");
        List<SceneBind> scenes = LocalDataApi.getSceneBindsByScene(sceneNo);
        for (int i = 0; i < scenes.size(); i++) {
            if (scenes.get(i).getDeviceId().equals(bean.getDevice().getDeviceId())) {
                sceneBinds.add(scenes.get(i));
            }
        }
        SmartSceneApi.deleteSceneBind(Config.userName, sceneNo, sceneBinds, new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {
                if (baseEvent.isSuccess()) {
                    LogUtils.i("删除情景绑定");
                    listener.onDeleteScene(bean);
                }
            }
        });
    }
}
