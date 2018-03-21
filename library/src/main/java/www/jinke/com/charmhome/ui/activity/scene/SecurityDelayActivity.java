package www.jinke.com.charmhome.ui.activity.scene;

import android.view.View;
import android.widget.RadioGroup;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-19.
 */

public class SecurityDelayActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup rg_scene_state;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_security_delay;
    }

    @Override
    protected void initView() {
        setTitleText("选择执行时间", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
    }


    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }


    @Override
    protected void findViewById() {
        rg_scene_state = findViewById(R.id.rg_scene_state);
        rg_scene_state.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_scene_delay_customize) {
            onYearMonthDayPicker();
        }

    }


    public void onYearMonthDayPicker() {
        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(24, 60, 60);
        picker.setRangeStart(20, 8, 29);
        picker.setSelectedItem(2050, 10, 14);
        picker.setResetWhileWheel(false);
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int i, String s) {
                showToast("onYearWheeled:" + s);
            }

            @Override
            public void onMonthWheeled(int i, String s) {
                showToast("onMonthWheeled:" + s);
            }

            @Override
            public void onDayWheeled(int i, String s) {
                showToast("onDayWheeled:" + s);
            }
        });
        picker.show();
    }
}
