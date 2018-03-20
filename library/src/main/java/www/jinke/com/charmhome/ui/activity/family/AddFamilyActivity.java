package www.jinke.com.charmhome.ui.activity.family;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.orvibo.homemate.api.FamilyApi;
import com.orvibo.homemate.api.HouseApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.sharedPreferences.UserCache;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.dialog.ToastDialog;

/**
 * Created by root on 18-3-14.
 */

public class AddFamilyActivity extends BaseActivity implements View.OnClickListener {
    EditText ed_edit_room_name;
    ImageView img_edit_room_delete;
    TextView tv_room_save;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_family;
    }

    @Override
    protected void initView() {
        setTitleText("创建房间", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        ed_edit_room_name = findViewById(R.id.ed_edit_room_name);
        img_edit_room_delete = findViewById(R.id.img_edit_room_delete);
        tv_room_save = findViewById(R.id.tv_room_save);
        tv_room_save.setOnClickListener(this);
        img_edit_room_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_room_save) {//添加房屋到默认房间
            if (StringUtils.isEmpty(ed_edit_room_name.getText().toString())) {
                ToastDialog.getInstance(this).showDialog().setLogOutContent("请输入家庭名称");
                return;
            }

            createFamily(ed_edit_room_name.getText().toString());
        } else if (view.getId() == R.id.img_edit_room_delete) {//删除房间
            ed_edit_room_name.setText("");
        }
    }

    public void createFamily(String familyName) {//创建家庭
        String userId = UserCache.getUserId(this, Config.userName);
        FamilyApi.createFamily(userId, familyName, new BaseResultListener.DataListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent, Object o) {
                if (baseEvent.isSuccess()) {
                    finish();
                }
            }
        });
    }
}
