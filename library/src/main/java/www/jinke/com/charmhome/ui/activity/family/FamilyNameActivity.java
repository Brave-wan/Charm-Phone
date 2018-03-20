package www.jinke.com.charmhome.ui.activity.family;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orvibo.homemate.api.FamilyApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.model.family.FamilyManager;
import com.orvibo.homemate.sharedPreferences.UserCache;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;

/**
 * Created by root on 18-3-14.
 */

public class FamilyNameActivity extends BaseActivity implements View.OnClickListener, LogoutDialog.onLogoutListener {
    ImageView img_family_name_close;
    EditText tv_family_name_edit;
    TextView tv_family_name_delete;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_family_name;
    }

    @Override
    protected void initView() {
        setTitleText("家庭名称", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
        setRightVisibility(R.color.charm_home_house_home_energy, "保存");
    }


    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }


    @Override
    protected void onRightView(View view) {
        super.onRightView(view);

    }

    @Override
    protected void findViewById() {
        img_family_name_close = findViewById(R.id.img_family_name_close);
        tv_family_name_edit = findViewById(R.id.tv_family_name_edit);
        tv_family_name_delete = findViewById(R.id.tv_family_name_delete);
        tv_family_name_delete.setOnClickListener(this);
        img_family_name_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_family_name_delete) {
            LogoutDialog dialog = new LogoutDialog(this);
            dialog.show();
            dialog.setLogOutContent("是否删除该家庭?");
            dialog.setLeftText("取消", R.color.charm_home_welcome_gray_tx);
            dialog.setRightText("删除", R.color.charm_home_house_home_energy);
            dialog.setTitleText("删除提示");
            dialog.setOnLogoutListener(this);
        } else if (view.getId() == R.id.img_family_name_close) {
            tv_family_name_edit.setText("");
        }
    }

    public void familyDelete() {
        String userId = UserCache.getUserId(this, Config.userName);
        FamilyApi.deleteFamily(userId, FamilyManager.getCurrentFamilyId(), new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {
                if (baseEvent.isSuccess()) {
                    FamilyManagerActivity.instance.finish();
                    finish();
                }

            }
        });
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        familyDelete();
    }
}
