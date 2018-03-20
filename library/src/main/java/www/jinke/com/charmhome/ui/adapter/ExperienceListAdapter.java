package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.ExperienceBean;

/**
 * Created by root on 18-3-12.
 */

public class ExperienceListAdapter extends CommonAdapter<ExperienceBean> {
    private Context context;

    public ExperienceListAdapter(@NonNull Context context, int layoutResId, @NonNull List<ExperienceBean> dataList) {
        super(context, layoutResId, dataList);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ExperienceBean bean, int position) {
        TextView tv_experience_name = (TextView) baseViewHolder.getViewByViewId(R.id.tv_experience_name);
        tv_experience_name.setTextColor(context.getResources().getColor(bean.isSelect() ? R.color.white : R.color.charm_home_welcome_gray_tx));
        tv_experience_name.setBackground(context.getResources().getDrawable(bean.isSelect() ? R.drawable.icon_welcome_experence_no : R.drawable.icon_welcome_experence_off));
        getDrawable(bean.isSelect() ? bean.getPic_yes() : bean.getPic_no(), tv_experience_name);
        tv_experience_name.setText(bean.getName());
    }

    public void getDrawable(int res, TextView textView) {
        // 找到资源图片
        Drawable drawable = context.getResources().getDrawable(res);
        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
        textView.setCompoundDrawables(null, drawable, null, null);// 设置到控件中
    }
}
