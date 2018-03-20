package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.orvibo.homemate.bo.Scene;

import java.util.List;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-13.
 */

public class HomeSceneListAdapter extends CommonAdapter<Scene> {
    private Context mContext;

    public HomeSceneListAdapter(@NonNull Context context, int layoutResId, @NonNull List<Scene> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Scene scene, int position) {

    }
}
