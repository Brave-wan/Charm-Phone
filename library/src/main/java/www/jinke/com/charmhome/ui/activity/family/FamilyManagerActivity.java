package www.jinke.com.charmhome.ui.activity.family;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orvibo.homemate.api.FamilyApi;
import com.orvibo.homemate.bo.Family;
import com.orvibo.homemate.dao.FamilyDao;
import com.orvibo.homemate.model.family.FamilyManager;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-14.
 */

public class FamilyManagerActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout rl_family_name, rl_room_manager;
    TextView tv_family_manager_name;

    public static FamilyManagerActivity instance;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_family_manager;
    }

    @Override
    protected void initView() {
        instance = this;
        setTitleText("家庭管理", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
        setRightVisibility("", R.drawable.icon_family_manager_add);
        Family family = FamilyDao.getInstance().getFamily(FamilyManager.getCurrentFamilyId());
        tv_family_manager_name.setText(family.getFamilyName());
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        startActivity(new Intent(this, AddFamilyActivity.class));
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void findViewById() {
        rl_family_name = findViewById(R.id.rl_family_name);
        tv_family_manager_name = findViewById(R.id.tv_family_manager_name);
        rl_room_manager = findViewById(R.id.rl_room_manager);
        rl_room_manager.setOnClickListener(this);
        rl_family_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_family_name) {
            startActivity(new Intent(this, FamilyNameActivity.class));
        } else if (view.getId() == R.id.rl_room_manager) {
            startActivity(new Intent(this, RoomManagerActivity.class));
        }
    }

}
