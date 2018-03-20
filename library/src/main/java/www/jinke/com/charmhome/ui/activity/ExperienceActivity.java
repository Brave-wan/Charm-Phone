package www.jinke.com.charmhome.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.LockMainDeviceBean;
import www.jinke.com.charmhome.presenter.lock.ExperiencePresenter;
import www.jinke.com.charmhome.ui.adapter.ExperienceFragmentAdapter;
import www.jinke.com.charmhome.utils.GlideImageLoader;
import www.jinke.com.charmhome.view.lock.IExperienceView;
import www.jinke.com.charmhome.widget.WrapContentHeightViewPager;

/**
 * 体验页
 * Created by root on 17-11-28.
 */

public class ExperienceActivity extends BaseActivity implements IExperienceView, TabLayout.OnTabSelectedListener {
    ExperiencePresenter presenter;
    ExperienceFragmentAdapter adapter;
    TabLayout tb_experience;
    Banner banner;
    WrapContentHeightViewPager vp_content;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_experience_life;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        BingView();
        adapter = new ExperienceFragmentAdapter(getSupportFragmentManager(), this);
        presenter = new ExperiencePresenter(this, this);
        vp_content.setAdapter(adapter);
        tb_experience.setupWithViewPager(vp_content);
        tb_experience.getTabAt(0).setCustomView(presenter.getTabView(0));
        tb_experience.getTabAt(1).setCustomView(presenter.getTabView(1));
        tb_experience.getTabAt(2).setCustomView(presenter.getTabView(2));
        tb_experience.getTabAt(3).setCustomView(presenter.getTabView(3));
        tb_experience.getTabAt(4).setCustomView(presenter.getTabView(4));
        tb_experience.setTabMode(TabLayout.MODE_FIXED);
        tb_experience.addOnTabSelectedListener(this);

        list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        banner.setImages(list)
                .setDelayTime(3000)
                .setImageLoader(new GlideImageLoader())
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    private void BingView() {
        setTitleText(getString(R.string.charmHome_virtual_family), R.color.black);
        setRightVisibility(false);
        setLeftBackView(R.drawable.icon_base_title_back);
        setBaseTitleBack(getResources().getColor(R.color.white));
        tb_experience = findViewById(R.id.tb_experience);
        vp_content = findViewById(R.id.vp_content);
        banner = findViewById(R.id.banner);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void findViewById() {

    }


    @Override
    public void onMainDeviceList(List<LockMainDeviceBean.LockBean> lock) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        TextView tx_item_tb = tab.getCustomView().findViewById(R.id.tx_item_tb);
        tx_item_tb.setTextColor(getResources().getColor(R.color.black));
        presenter.getShowExperience(tx_item_tb);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TextView tx_item_tb = tab.getCustomView().findViewById(R.id.tx_item_tb);
        tx_item_tb.setTextColor(getResources().getColor(R.color.charm_home_welcome_gray_tx));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
