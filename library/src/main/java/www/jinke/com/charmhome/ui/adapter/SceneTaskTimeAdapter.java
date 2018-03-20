package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.orvibo.homemate.bo.Timing;
import com.orvibo.homemate.util.DateUtil;

import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.TimingBean;
import www.jinke.com.charmhome.ui.activity.scene.TimeSelectActivity;

/**
 * Created by root on 18-3-16.
 */

public class SceneTaskTimeAdapter extends CommonAdapter<Timing> {
    private Context mContext;

    public SceneTaskTimeAdapter(@NonNull Context context, int layoutResId, @NonNull List<Timing> dataList) {
        super(context, layoutResId, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Timing bean, int position) {
        TextView tv_timing_time = (TextView) baseViewHolder.getViewByViewId(R.id.tv_timing_time);
        TextView tv_timing_date = (TextView) baseViewHolder.getViewByViewId(R.id.tv_timing_date);
        ImageView img_timing_state = (ImageView) baseViewHolder.getViewByViewId(R.id.img_timing_state);
        ImageView img_edit_scene_red_line = (ImageView) baseViewHolder.getViewByViewId(R.id.img_edit_scene_red_line);
        RelativeLayout rl_task_add = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_task_add);
        ImageView img_add_line_red = (ImageView) baseViewHolder.getViewByViewId(R.id.img_add_line_red);
        RelativeLayout rl_edit_scene_content = (RelativeLayout) baseViewHolder.getViewByViewId(R.id.rl_edit_scene_content);
        boolean isSelect = StringUtils.isEmpty(bean.getTimingId());
        img_edit_scene_red_line.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        rl_edit_scene_content.setVisibility(isSelect ? View.GONE : View.VISIBLE);
        rl_task_add.setVisibility(isSelect ? View.VISIBLE : View.GONE);
        img_add_line_red.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        tv_timing_date.setText(getBinaryString(bean.getWeek()));
        img_timing_state.setBackground(mContext.getResources().getDrawable(bean.getIsPause() == 0 ?
                R.drawable.icon_timing_switch_close :
                R.drawable.icon_timing_switch_openn));
        if (!isSelect) {
            img_add_line_red.setVisibility(View.GONE);
            tv_timing_time.setText(bean.getHour() + ":" + bean.getMinute());
        }

        rl_edit_scene_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TimeSelectActivity.class);
                intent.putExtra("bean", bean);
                mContext.startActivity(intent);
            }
        });

        rl_task_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TimeSelectActivity.class);
                intent.putExtra("bean", bean);
                mContext.startActivity(intent);
            }
        });
    }


    public String getBinaryString(int weak) {
        String date = " ";
        String value = Integer.toBinaryString(weak);
        LogUtils.i("value:" + value);
        char[] time = value.toCharArray();
        for (int i = 1; i < time.length; i++) {
            if (String.valueOf(time[i]).equals("1")) {
                switch (i) {
                    case 1:
                        date += String.valueOf(time[i]).equals("1") ? " 周日" : "";
                        break;
                    case 2:
                        date += String.valueOf(time[i]).equals("1") ? " 周六" : "";
                        break;
                    case 3:
                        date += String.valueOf(time[i]).equals("1") ? " 周五" : "";
                        break;
                    case 4:
                        date += String.valueOf(time[i]).equals("1") ? " 周四" : "";
                        break;
                    case 5:
                        date += String.valueOf(time[i]).equals("1") ? " 周三" : "";
                        break;
                    case 6:
                        date += String.valueOf(time[i]).equals("1") ? " 周二" : "";
                        break;
                    case 7:
                        date += String.valueOf(time[i]).equals("1") ? " 周一" : "";
                        break;
                }
            }
        }
        return date;
    }
}
