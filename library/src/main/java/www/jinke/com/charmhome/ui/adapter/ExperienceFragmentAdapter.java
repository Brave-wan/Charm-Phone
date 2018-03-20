package www.jinke.com.charmhome.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.activity.ExperienceFragment;

/**
 * Created by root on 18-3-12.
 */

public class ExperienceFragmentAdapter extends FragmentPagerAdapter {
    private List<String> title = new ArrayList<>();
    private Context mContext;


    public ExperienceFragmentAdapter(FragmentManager fragmentManager, Context mContext) {
        super(fragmentManager);
        this.mContext=mContext;
        title.add("回家");
        title.add("休息");
        title.add("离家");
        title.add("睡眠");
        title.add("起床");
    }

    @Override
    public Fragment getItem(int position) {
        return new ExperienceFragment();
    }


    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable =mContext.getResources().getDrawable(R.drawable.icon_base_title_back);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        SpannableString spannableString = new SpannableString(" " +title.get(position));
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
