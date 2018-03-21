package www.jinke.com.charmhome.ui.activity.scene;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.Locale;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-20.
 */

public class SceneDelayActivity extends BaseActivity {
    RelativeLayout ll_time_content;
    TextView tv_scene_time;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scene_delay;
    }

    @Override
    protected void initView() {
        setTitleText("选择执行时间", R.color.white);
        setBaseTitleBack(R.color.charm_home_default_red);
        setLeftBackView(R.drawable.icon_back_left);
        getOptionPicker();
    }

    @Override
    protected void findViewById() {
        ll_time_content = findViewById(R.id.ll_time_content);
        tv_scene_time = findViewById(R.id.tv_scene_time);
        tv_scene_time.setText("hello word");
    }

    public void getOptionPicker() {
        String[] data = new String[66];
        for (int i = 0; i < 66; i++) {
            data[i] = i + "" ;
        }
        OptionPicker picker = new OptionPicker(this, data);
        picker.setCycleDisable(true);//禁用循环
        picker.setTextColor(getResources().getColor(R.color.white)
                , getResources().getColor(R.color.white));//中间滚动项文字颜色
        picker.setGravity(Gravity.CENTER);
        picker.setTextSize(35);
        picker.setLabelTextColor(getResources().getColor(R.color.white));
        picker.setDividerVisible(false);
        picker.setBackgroundColor(getResources().getColor(R.color.transparent));
        picker.setSelectedIndex(10);//默认选中项
        picker.setOnWheelListener(new OptionPicker.OnWheelListener() {
            @Override
            public void onWheeled(int i, String s) {
                tv_scene_time.setText(s + "秒后执行");
            }
        });

        View pickerContentView = picker.getContentView();
        ll_time_content.addView(pickerContentView);
    }

}
