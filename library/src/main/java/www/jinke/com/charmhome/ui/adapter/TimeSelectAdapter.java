package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.TimingBean;

/**
 * Created by root on 18-3-16.
 */

public class TimeSelectAdapter extends CommonAdapter<TimingBean> {

    private Context mContext;

    public TimeSelectAdapter(@NonNull Context context, int layoutResId, @NonNull List<TimingBean> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TimingBean bean, int position) {
        TextView tv_timing_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_timing_name);
        tv_timing_name.setText(bean.getName());
        tv_timing_name.setTextColor(mContext.getResources().getColor(bean.isState() ? R.color.white : R.color.charm_home_house_txt));
        tv_timing_name.setBackground(mContext.getResources().getDrawable(bean.isState() ? R.drawable.icon_timing_check : R.drawable.icon_timing_un_check));

    }
}
