package www.jinke.com.charmhome.ui.activity.scene;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.bo.Timing;
import com.orvibo.homemate.model.family.FamilyManager;
import com.orvibo.homemate.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneTaskBean;
import www.jinke.com.charmhome.presenter.scene.EditScenePresent;
import www.jinke.com.charmhome.ui.adapter.SceneTaskListAdapter;
import www.jinke.com.charmhome.ui.adapter.SceneTaskTimeAdapter;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;
import www.jinke.com.charmhome.view.scene.IEditSceneView;

/**
 * Created by root on 18-3-15.
 */

public class EditSceneActivity extends BaseActivity implements View.OnClickListener,
        SceneTaskListAdapter.OnEditSceneListener, IEditSceneView {
    private MyListView lv_task_list, lv_task_time;
    private Scene scene;
    private SceneTaskListAdapter adapter;
    private SceneTaskTimeAdapter taskTimeAdapter;
    private TextView ed_edit_name;
    private EditScenePresent present;
    public static List<SceneTaskBean> list = new ArrayList<>();
    private List<Timing> timeList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_scene;
    }

    @Override
    protected void initView() {
        setTitleText("编辑情景", R.color.white);
        setBaseTitleBack(R.color.charm_home_default_red);
        setLeftBackView(R.drawable.icon_back_left);
        setRightVisibility(R.color.white, "保存");
        present = new EditScenePresent(this, this);
        list = present.getTaskDeviceList(scene);
        adapter = new SceneTaskListAdapter(this, R.layout.item_scene_edit_task, list);
        adapter.setOnEditSceneListener(this);

        taskTimeAdapter = new SceneTaskTimeAdapter(this, R.layout.item_scene_edit_time, timeList);
        lv_task_time.setAdapter(taskTimeAdapter);
        lv_task_list.setAdapter(adapter);
        list.add(new SceneTaskBean());
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        //修改情景绑定
        present.addSceneBind(list, scene);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.setDataList(present.getListSorting(list));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    protected void findViewById() {
        scene = (Scene) getIntent().getSerializableExtra("scene");
        lv_task_list = findViewById(R.id.lv_task_list);
        ed_edit_name = findViewById(R.id.ed_edit_name);
        lv_task_time = findViewById(R.id.lv_task_time);
        ed_edit_name.setOnClickListener(this);
        findViewById(R.id.img_edit_scene_delete).setOnClickListener(this);
        timeList = LocalDataApi.getSceneTimings(FamilyManager.getCurrentFamilyId(), scene.getSceneNo());
        timeList.add(new Timing());
        SharedPreferencesUtils.init(this)
                .put("scene_no1", scene.getSceneNo())
                .put("scene_uid", scene.getUid())
                .put("scene_id", scene.getSceneId())
                .put("scene_familyId", scene.getFamilyId());
        ed_edit_name.setText(scene.getSceneName());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_edit_scene_delete) {
            ed_edit_name.setText("");
        } else if (view.getId() == R.id.ed_edit_name) {
            Intent intent = new Intent(this, SceneIconActivity.class);
            intent.putExtra("scene", scene);
            startActivity(intent);
        }
    }

    @Override
    public void onResult() {
        Intent intent = new Intent(this, SelectDeviceActivity.class);
        intent.putExtra("scene", scene);
        startActivity(intent);
    }

    @Override
    public void onDeleteScene(SceneTaskBean bean) {
        if (list.contains(bean)) {
            list.remove(bean);
            adapter.setDataList(list);
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
    public void onAlterScene() {
        finish();
    }
}
