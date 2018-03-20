package www.jinke.com.charmhome.ui.activity.family;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.HouseApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.event.BaseEvent;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.presenter.family.EditRoomPresent;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;
import www.jinke.com.charmhome.ui.dialog.ToastDialog;
import www.jinke.com.charmhome.view.family.IEditRoomView;

/**
 * Created by root on 18-3-14.
 */

public class EditRoomActivity extends BaseActivity implements View.OnClickListener, IEditRoomView, LogoutDialog.onLogoutListener {
    TextView tv_room_delete;
    EditText ed_edit_room_name;
    ImageView img_edit_room_delete;
    EditRoomPresent present;
    Room room;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_room;
    }

    @Override
    protected void initView() {
        setTitleText("编辑房间", R.color.black);
        setLeftBackView(R.drawable.icon_charm_home_back);
        setRightVisibility(R.color.charm_home_house_home_energy, "保存");
        setBaseTitleBack(R.color.white);
        room = (Room) getIntent().getSerializableExtra("room");
        ed_edit_room_name.setText(room.getRoomName());
        present = new EditRoomPresent(this, this);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        //修改房屋
        if (StringUtils.isEmpty(ed_edit_room_name.getText().toString().trim())) {
            ToastDialog.getInstance(this).showDialog().setToastMode("房间名称不能为空", R.color.charm_home_house_txt);
            return;
        }
        showDialog();
        room.setRoomName(ed_edit_room_name.getText().toString().trim());
        present.modifyRoom(room);
    }

    @Override
    protected void findViewById() {
        tv_room_delete = findViewById(R.id.tv_room_delete);
        ed_edit_room_name = findViewById(R.id.ed_edit_room_name);
        img_edit_room_delete = findViewById(R.id.img_edit_room_delete);
        tv_room_delete.setOnClickListener(this);
        img_edit_room_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_edit_room_delete) {
            ed_edit_room_name.setText("");
        } else if (view.getId() == R.id.tv_room_delete) {
            LogoutDialog dialog = new LogoutDialog(this);
            dialog.show();
            dialog.setLogOutContent("是否删除该房间?");
            dialog.setLeftText("取消", R.color.charm_home_welcome_gray_tx);
            dialog.setRightText("删除", R.color.charm_home_house_home_energy);
            dialog.setTitleText("删除提示");
            dialog.setOnLogoutListener(this);
        }
    }


    @Override
    public void showLoading(String msg) {
        showDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void onDeleteSuccess() {
        finish();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        present.getDeleteRoom(room.getRoomId());
    }
}
