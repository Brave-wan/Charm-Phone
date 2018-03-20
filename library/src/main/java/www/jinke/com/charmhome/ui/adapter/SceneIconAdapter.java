package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.SceneIconBean;

/**
 * Created by root on 18-3-19.
 */

public class SceneIconAdapter extends CommonAdapter<SceneIconBean> {

    private Context mContext;

    public SceneIconAdapter(@NonNull Context context, int layoutResId, @NonNull List<SceneIconBean> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SceneIconBean bean, int position) {
        ImageView img_scene_mode_icon = (ImageView) baseViewHolder.getViewByViewId(R.id.img_scene_mode_icon);
        ImageView img_scene_mode_state = (ImageView) baseViewHolder.getViewByViewId(R.id.img_scene_mode_state);
        img_scene_mode_icon.setBackgroundResource(bean.getSrc());
        img_scene_mode_state.setVisibility(bean.isShow() ? View.VISIBLE : View.GONE);

    }
}
