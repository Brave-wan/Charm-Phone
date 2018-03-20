package www.jinke.com.charmhome.ui.activity.scene;

import android.view.View;
import android.widget.RadioGroup;

import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.event.BaseEvent;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-19.
 */

public class SceneActionActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup rg_scene_state;
    Scene scene;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scene_action;
    }

    @Override
    protected void initView() {
        setTitleText("设置动作", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
        rg_scene_state.check(R.id.rb_scene_action_open);
        rg_scene_state.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        rg_scene_state = findViewById(R.id.rg_scene_state);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_scene_action_open) {


        } else if (i == R.id.rb_scene_action_close) {

        }

    }

    public void get(String linkageId) {
        SmartSceneApi.activateLinkageTask(Config.userName, linkageId, 1, new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {

            }
        });
    }
}
