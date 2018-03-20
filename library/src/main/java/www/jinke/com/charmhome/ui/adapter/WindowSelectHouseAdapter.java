package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orvibo.homemate.bo.Family;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;

/**
 * Created by root on 18-3-13.
 */

public class WindowSelectHouseAdapter extends CommonAdapter<Family> {
    private Context mContext;

    public WindowSelectHouseAdapter(@NonNull Context context, int layoutResId, @NonNull List<Family> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Family family, int position) {
        TextView tv_select_house = (TextView) baseViewHolder.getViewByViewId(R.id.tv_select_house);
        RelativeLayout rl_house = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_house);
        ImageView img_select_house_left = (ImageView) baseViewHolder.getViewByViewId(R.id.img_select_house_left);
        img_select_house_left.setVisibility(family.getPic() != null ? View.VISIBLE : View.INVISIBLE);
        rl_house.setBackgroundColor(mContext.getResources().getColor(family.getPic() != null ? R.color.white : R.color.charm_home_default));
        tv_select_house.setText(family.getFamilyName());
        tv_select_house.setTextColor(mContext.getResources().getColor(family.getPic() != null ? R.color.black : R.color.charm_home_house_window));
        if (family.getPic() != null) {
            SharedPreferencesUtils.init(mContext).put("family_name", family.getFamilyName());
        }
    }
}
