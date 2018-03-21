package www.jinke.com.charmhome.presenter.scene;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orvibo.homemate.api.SmartSceneApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Scene;
import com.orvibo.homemate.event.BaseEvent;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneIconBean;
import www.jinke.com.charmhome.view.scene.ISceneIconView;

/**
 * Created by root on 18-3-19.
 */

public class SceneIconPresent {

    private Context mContext;
    private ISceneIconView mView;

    public SceneIconPresent(Context mContext, ISceneIconView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    public List<SceneIconBean> getIconList() {
        List<SceneIconBean> list = new ArrayList<>();
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_home, true, 0));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_clock, false, 1));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_leave, false, 2));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_bed, false, 3));
        list.add(new SceneIconBean(R.drawable.iocn_scene_icon_sofa, false, 4));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_rest, false, 5));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_master, false, 6));
        list.add(new SceneIconBean(R.drawable.iocn_scene_icon_electric, false, 7));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_cup, false, 8));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_furnitur, false, 9));
        list.add(new SceneIconBean(R.drawable.icon_scene_icon_chair, false, 10));
        list.add(new SceneIconBean(R.drawable.icon_device_select_curtain_red, false, 11));


        return list;
    }


    public void getSceneName(Scene scene, String sceneName) {//修改情景名称
        if (mView != null) {
            mView.showLoading("");
            SmartSceneApi.modifyScene(Config.userName, scene.getSceneNo(), sceneName,
                    scene.getPic(), Integer.valueOf(String.valueOf(scene.getUpdateTime() / 1000)), new BaseResultListener() {
                        @Override
                        public void onResultReturn(BaseEvent baseEvent) {
                            if (baseEvent.isSuccess()) {
                                mView.onSuccess();
                            }
                            mView.hideLoading();
                        }
                    });
        }
    }

    public void setSceneIcon(final int pic, Scene scene) {
        SmartSceneApi.modifyScene(Config.userName, scene.getSceneNo(), scene.getSceneName(),
                pic, Integer.valueOf(String.valueOf(scene.getUpdateTime() / 1000)), new BaseResultListener() {
                    @Override
                    public void onResultReturn(BaseEvent baseEvent) {
                        if (baseEvent.isSuccess()) {
                            mView.onPicSuccess(pic);
                        }
                    }
                });
    }

    public void setIcon(int type, TextView tv_scene_mode_name) {
        Drawable drawable = null;
        switch (type) {
            case 0:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_home);
                break;
            case 1:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_clock);
                break;
            case 2:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_leave);
                break;
            case 3:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_bed);
                break;
            case 4:
                drawable = mContext.getResources().getDrawable(R.drawable.iocn_scene_icon_sofa);
                break;
            case 5:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_rest);
                break;
            case 6:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_master);
                break;
            case 7:
                drawable = mContext.getResources().getDrawable(R.drawable.iocn_scene_icon_electric);
                break;
            case 8:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_cup);
                break;
            case 9:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_furnitur);
                break;
            case 10:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_scene_icon_chair);
                break;
            case 11:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_device_select_curtain_red);
                break;
            default:
                drawable = mContext.getResources().getDrawable(R.drawable.icon_device_select_curtain_red);
                break;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_scene_mode_name.setCompoundDrawables(drawable, null, null, null);
    }

}
