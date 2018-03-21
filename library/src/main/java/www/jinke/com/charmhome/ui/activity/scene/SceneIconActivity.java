package www.jinke.com.charmhome.ui.activity.scene;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.bo.Scene;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneIconBean;
import www.jinke.com.charmhome.presenter.scene.SceneIconPresent;
import www.jinke.com.charmhome.ui.adapter.SceneIconAdapter;
import www.jinke.com.charmhome.view.scene.ISceneIconView;

/**
 * Created by root on 18-3-19.
 */

public class SceneIconActivity extends BaseActivity implements AdapterView.OnItemClickListener, ISceneIconView {
    private ImageView img_scene_mode_close;
    private EditText tv_scene_mode_name;
    private GridView gv_scene_icon;
    private SceneIconAdapter adapter;
    private List<SceneIconBean> list = new ArrayList<>();
    private Scene scene;
    SceneIconPresent present;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scene_icon;
    }

    @Override
    protected void initView() {
        setTitleText("编辑情景", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
        tv_scene_mode_name.setText(scene.getSceneName());
        setRightVisibility(R.color.charm_home_default_red, "保存");
        adapter = new SceneIconAdapter(this, R.layout.item_scene_icon_list, list);
        gv_scene_icon.setAdapter(adapter);
        present.setIcon(scene.getPic(), tv_scene_mode_name);
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        if (StringUtils.isEmpty(tv_scene_mode_name.getText().toString().trim())) {
            ToastUtils.showShort("请输入情景名称!");
            return;
        }
        present.getSceneName(scene, tv_scene_mode_name.getText().toString().trim());
    }

    @Override
    protected void findViewById() {
        present = new SceneIconPresent(this, this);
        scene = (Scene) getIntent().getSerializableExtra("scene");
        img_scene_mode_close = findViewById(R.id.img_scene_mode_close);
        tv_scene_mode_name = findViewById(R.id.tv_scene_mode_name);
        gv_scene_icon = findViewById(R.id.gv_scene_icon);
        list = present.getIconList();
        gv_scene_icon.setOnItemClickListener(this);

        img_scene_mode_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_scene_mode_name.setText("");
            }
        });
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        list = adapter.dataList;
        for (SceneIconBean bean : list) {
            bean.setShow(false);
        }
        list.get(i).setShow(true);
        adapter.setDataList(list);

        Drawable drawable = getResources().getDrawable(list.get(i).getSrc());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_scene_mode_name.setCompoundDrawables(drawable, null, null, null);
        present.setSceneIcon(list.get(i).getType(), scene);
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
    public void onSuccess() {
        finish();
    }

    @Override
    public void onPicSuccess(int pic) {
        scene.setPic(pic);
    }
}
