package www.jinke.com.charmhome.ui.activity.scene;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orvibo.homemate.bo.Timing;
import com.orvibo.homemate.data.DeviceOrder;
import com.orvibo.homemate.data.TimingType;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.TimingBean;
import www.jinke.com.charmhome.presenter.scene.TimeSelectPresent;
import www.jinke.com.charmhome.ui.adapter.TimeSelectAdapter;
import www.jinke.com.charmhome.utils.SharedPreferencesUtils;
import www.jinke.com.charmhome.view.scene.ITimeSelectView;

/**
 * Created by root on 18-3-16.
 */

public class TimeSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, ITimeSelectView {
    private GridView gv_time_list;
    private TimeSelectAdapter adapter;
    private  TextView tv_time_repeat_number;
    private List<TimingBean> list = new ArrayList<>();
    private Timing bean;
    LinearLayout viewGroup;
    TimeSelectPresent present;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_time_select;
    }

    @Override
    protected void initView() {
        setTitleText("添加定时", R.color.black);
        setBaseTitleBack(R.color.white);
        setLeftBackView(R.drawable.icon_charm_home_back);
        setRightVisibility(R.color.charm_home_default_red, "保存");
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {
        present = new TimeSelectPresent(this, this);
        bean = (Timing) getIntent().getSerializableExtra("bean");
        gv_time_list = findViewById(R.id.gv_time_list);
        tv_time_repeat_number = findViewById(R.id.tv_time_repeat_number);
        viewGroup = findViewById(R.id.wheel_view_container);
        adapter = new TimeSelectAdapter(this, R.layout.item_select_timing, list);
        gv_time_list.setAdapter(adapter);
        gv_time_list.setOnItemClickListener(this);
        present.initTimePicker(bean, viewGroup);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        List<TimingBean> list = adapter.dataList;
        boolean isSelect = list.get(i).isState();
        list.get(i).setState(isSelect ? false : true);
        adapter.setDataList(list);
    }


    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        //初始化时间实体
        if (bean != null) {
            bean.setWeek(193);
            present.getModifyTimingTask(bean);
        }

    }

    @Override
    public void showLoading(String msg) {
        showDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void onBinaryString(int weak) {
        present.getBinaryString(list, weak, tv_time_repeat_number);
    }

    @Override
    public void onAdapterRefresh(List<TimingBean> list) {
        adapter.setDataList(list);
    }

    @Override
    public void onHourWheeled(int i) {
        if (bean != null) {
            bean.setHour(i);
        }
    }

    @Override
    public void onMinuteWheeled(int i) {
        if (bean != null) {
            bean.setMinute(i);
        }
    }
}
