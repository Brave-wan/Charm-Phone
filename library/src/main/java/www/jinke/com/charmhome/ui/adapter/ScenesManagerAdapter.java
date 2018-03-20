package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orvibo.homemate.bo.Scene;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.activity.scene.EditSceneActivity;

/**
 * Created by root on 17-11-7.
 */

public class ScenesManagerAdapter extends CommonAdapter<Scene> {

    private Context mContext;

    public ScenesManagerAdapter(@NonNull Context context, int layoutResId, @NonNull List<Scene> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Scene scene, int position) {
        TextView img_scene_name = (TextView) baseViewHolder.getViewByViewId(R.id.img_scene_name);
        ImageView img_scene_setting = (ImageView) baseViewHolder.getViewByViewId(R.id.img_scene_setting);
        ImageView img_scene_icon = (ImageView) baseViewHolder.getViewByViewId(R.id.img_scene_icon);
        img_scene_name.setText(scene.getSceneName());
        setIcon(scene.getPic(), img_scene_icon);
        img_scene_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, EditSceneActivity.class);
                intent.putExtra("scene", scene);
                mContext.startActivity(intent);
            }
        });
    }

    public void setIcon(int type, ImageView img_scene_setting) {
        switch (type) {
            case 0:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_home));
                break;
            case 1:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_clock));
                break;
            case 2:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_leave));
                break;
            case 3:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_room));
                break;
            case 4:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_sofa));
                break;
            case 5:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_bedtwo));
                break;
            case 6:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_master));
                break;
            case 7:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_electric));
                break;
            case 8:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_cup));
                break;
            case 9:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_cabinet));
                break;
            case 10:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_chair));
                break;
            case 11:
                img_scene_setting.setBackground(mContext.getResources().getDrawable(R.drawable.icon_scene_manager_curtain));
                break;
        }
    }
}
