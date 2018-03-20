package www.jinke.com.charmhome.presenter.lock;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.impl.ExperienceImpl;
import www.jinke.com.charmhome.ui.dialog.ExperienceDialog;
import www.jinke.com.charmhome.utils.ACache;
import www.jinke.com.charmhome.view.lock.IExperienceView;

/**
 * Created by root on 17-12-11.
 */

public class ExperiencePresenter {
    private Context mContext;
    private IExperienceView experienceView;
    private ExperienceImpl experience;
    ACache aCache;

    public ExperiencePresenter(Context mContext, IExperienceView experienceView) {
        this.mContext = mContext;
        this.experienceView = experienceView;
        experience = new ExperienceImpl(mContext);
        aCache = ACache.get(mContext);
    }

    public void getLogin() {

    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_welcome_tab, null);
        TextView tx_title = view.findViewById(R.id.tx_item_tb);
        Drawable drawable = null;
        String title = null;
        switch (position) {
            case 0:
                title = "回家";
                drawable = mContext.getResources().getDrawable(R.drawable.icon_charm_welcome_home);
                tx_title.setTextColor(mContext.getResources().getColor(R.color.black));
                break;
            case 1:
                title = "休息";
                drawable = mContext.getResources().getDrawable(R.drawable.icon_charm_welcome_rest);

                break;
            case 2:
                title = "离家";
                drawable = mContext.getResources().getDrawable(R.drawable.icon_charm_welcome_leave);
                break;
            case 3:
                title = "睡眠";
                drawable = mContext.getResources().getDrawable(R.drawable.icon_charm_welcome_sleep);
                break;
            case 4:
                title = "起床";
                drawable = mContext.getResources().getDrawable(R.drawable.icon_charm_welcome_weak_up);
                break;
        }

        // 找到资源图片

        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
        tx_title.setCompoundDrawables(null, drawable, null, null);// 设置到控件中
        tx_title.setText(title);
        return view;
    }

    public void getShowExperience(TextView tx_item_tb) {
        ExperienceDialog dialog;
        switch (tx_item_tb.getText().toString().trim()) {
            case "回家":
                dialog = new ExperienceDialog(mContext, R.drawable.icon_welcome_dialog_home);
                dialog.show();
                break;
            case "休息":
                dialog = new ExperienceDialog(mContext, R.drawable.icon_welcome_dialog_rest);
                dialog.show();
                break;
            case "离家":
                dialog = new ExperienceDialog(mContext, R.drawable.icon_welcome_home_leave);
                dialog.show();
                break;
            case "睡眠":
                dialog = new ExperienceDialog(mContext, R.drawable.icon_welcome_dialog_sleep);
                dialog.show();
                break;
            case "起床":
                dialog = new ExperienceDialog(mContext, R.drawable.icon_welcome_dialog_weak_up);
                dialog.show();
                break;
        }
    }
}
