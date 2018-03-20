package www.jinke.com.charmhome.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.event.BaseEvent;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.adapter.ScenesManagerAdapter;

/**
 * Created by root on 17-11-7.
 */

public class ScenesManagerActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView gv_scene_list;
    private ScenesManagerAdapter adapter;
    private List<Scene> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scenes_manager;
    }

    @Override
    protected void initView() {
        setTitleText("场景管理", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);

        list = LocalDataApi.getAllScenes(FamilyManager.getCurrentFamilyId());
        adapter = new ScenesManagerAdapter(this, R.layout.item_home_scene_list, list);
        gv_scene_list.setAdapter(adapter);
        gv_scene_list.setOnItemClickListener(this);
    }


    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        gv_scene_list = findViewById(R.id.gv_scene_list);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Scene item = (Scene) adapter.getItem(i);
        SmartSceneApi.controlScene(Config.userName, item.getSceneNo(), new BaseResultListener() {
            @Override
            public void onResultReturn(BaseEvent baseEvent) {
                LogUtils.i(baseEvent.getResult());
                ToastUtils.showShort(baseEvent.isSuccess() ? "操作成功" : "主机不在线");
            }
        });
    }
}
