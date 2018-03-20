package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orvibo.homemate.bo.Device;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneTaskBean;
import www.jinke.com.charmhome.ui.activity.scene.SceneActionActivity;
import www.jinke.com.charmhome.ui.activity.scene.SceneDelayActivity;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;

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

        RelativeLayout rl_scene_action = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_scene_action);
        RelativeLayout rl_scene_delay = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_scene_delay);
        rl_edit_scene_content.setVisibility(device != null ? View.VISIBLE : View.GONE);
        rl_task_add.setVisibility(device != null ? View.GONE : View.VISIBLE);
        img_add_line_red.setVisibility(device == null ? (position == 0 ? View.VISIBLE : View.GONE) : View.GONE);

        if (device != null) {
            tv_device_name.setText(device.getDeviceName());
            img_red_line.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            setIcon(device.getDeviceType(), tv_device_name);
            tv_device_room_name.setText(bean.getRoom().getRoomName());
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
                showDialog(bean);
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
                listener.onDeleteScene(bean);
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
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_curtain_red);
                break;
            case 5://空调
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_air_condition_red);
                break;
            case 6://电视
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_tv_red);
                break;
            case 34:
                drawable = context.getResources().getDrawable(R.drawable.icon_device_select_curtain_red);
                break;
        }
        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
        imageView.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
    }
}
