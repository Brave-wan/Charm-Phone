package www.jinke.com.charmhome.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.ExperienceBean;
import www.jinke.com.charmhome.ui.adapter.ExperienceListAdapter;
import www.jinke.com.charmhome.widget.MyGridView;

/**
 * Created by root on 18-3-12.
 */

public class ExperienceFragment extends Fragment implements AdapterView.OnItemClickListener {
    MyGridView gv_charm_home_type;
    ExperienceListAdapter adapter;
    List<ExperienceBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experence_welcome, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gv_charm_home_type = view.findViewById(R.id.gv_charm_home_type);
        list.clear();
        list.add(new ExperienceBean("空调", R.drawable.icon_welcome_aircondition0, R.drawable.icon_welcome_aircondition1, true));
        list.add(new ExperienceBean("吊灯", R.drawable.icon_welcome_lamp0, R.drawable.icon_welcome_lamp1, false));
        list.add(new ExperienceBean("空气净化器", R.drawable.icon_welcome_air_clean0, R.drawable.icon_welcome_air_clean1, false));
        list.add(new ExperienceBean("门窗传感器", R.drawable.icon_welcome_door0, R.drawable.icon_welcome_door1, false));
        list.add(new ExperienceBean("摄像头", R.drawable.icon_welcome_camera0, R.drawable.icon_welcome_camera1, false));
        list.add(new ExperienceBean("电视机", R.drawable.icon_welcome_tv0, R.drawable.icon_welcome_tv1, false));
        list.add(new ExperienceBean("主卧灯", R.drawable.icon_welcome_light0, R.drawable.icon_welcome_light1, false));
        list.add(new ExperienceBean("窗帘", R.drawable.icon_welcome_curtain0, R.drawable.icon_welcome_curtain1, false));
        adapter = new ExperienceListAdapter(getActivity(), R.layout.item_welcome_charm_type, list);
        gv_charm_home_type.setAdapter(adapter);
        gv_charm_home_type.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        List<ExperienceBean> list = adapter.dataList;
        for (int j = 0; j < list.size(); j++) {
            list.get(j).setSelect(false);
        }
        list.get(i).setSelect(true);
        adapter.setDataList(list);

    }
}
