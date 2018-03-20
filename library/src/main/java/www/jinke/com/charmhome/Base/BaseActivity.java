package www.jinke.com.charmhome.Base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.widget.MyToast;
import www.jinke.com.charmhome.widget.SpotsDialog;

/**
 * Created by root on 17-11-1.
 */

public abstract class BaseActivity extends FragmentActivity {
    private FrameLayout mFrameLayout;
    private RelativeLayout rl_base_title_view;
    private TextView tx_title_back;
    private TextView tx_title_content;
    private TextView tx_title_right;

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected SpotsDialog spotsDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initTitle();
        findViewById();
        initView();
    }

    private void initTitle() {
        mFrameLayout = findViewById(R.id.base_fragment_layout);
        tx_title_back = findViewById(R.id.tx_title_back);
        tx_title_content = findViewById(R.id.tx_title_content);
        tx_title_right = findViewById(R.id.tx_title_right);
        rl_base_title_view = findViewById(R.id.rl_base_title_view);

        setFrameContent(getContentViewId());
        tx_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackView(view);
            }
        });

        tx_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightView(view);
            }
        });
        tx_title_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCenterView(rl_base_title_view);
            }
        });
    }

    protected abstract void findViewById();

    public void setBackVisibility(boolean visibility) {
        tx_title_back.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public void setLeftBackView(int res) {
        // 找到资源图片
        Drawable drawable = getResources().getDrawable(res);
        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
        tx_title_back.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
    }

    public void setRightVisibility(boolean visibility) {
        tx_title_right.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public void setRightVisibility(String txt) {
        tx_title_right.setVisibility(View.VISIBLE);
        tx_title_right.setText(txt);
    }

    public void setRightVisibility(int color, String txt) {
        tx_title_right.setVisibility(View.VISIBLE);
        tx_title_right.setText(txt);
        tx_title_right.setTextColor(getResources().getColor(color));
    }

    public void setRightVisibility(String txt, int res) {
        tx_title_right.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(res);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tx_title_right.setCompoundDrawables(drawable, null, null, null);//设置TextView的
        tx_title_right.setCompoundDrawablePadding(10);//设置图片和text之间的间距
        tx_title_right.setText(txt);
    }

    public void setTitleText(String title) {
        tx_title_content.setText(title);
    }

    public void setTitleText(String title, int color) {
        tx_title_content.setText(title);
        tx_title_content.setTextColor(getResources().getColor(color));
    }

    public void setBaseTitleBack(int res) {
        rl_base_title_view.setBackgroundColor(getResources().getColor(res));
    }

    public void setTitleVisibility(boolean res) {
        rl_base_title_view.setVisibility(res ? View.VISIBLE : View.GONE);
    }

    protected void onCenterView(View view) {

    }

    protected void onRightView(View view) {

    }

    protected void onBackView(View view) {

    }

    //替换frame中的视图
    public void setFrameContent(int layoutResID) {
        if (mFrameLayout != null) {
            mFrameLayout.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(this);
            mFrameLayout.addView(inflater.inflate(layoutResID, null));
        }
    }

    public void showDialog(String msg) {
        if (spotsDialog != null && spotsDialog.isShowing()) {
            spotsDialog.dismiss();
        }
        spotsDialog = new SpotsDialog(this);
        spotsDialog.show();
        spotsDialog.setContent(msg);
    }
    public void showDialog() {
        if (spotsDialog != null && spotsDialog.isShowing()) {
            spotsDialog.dismiss();
        }
        spotsDialog = new SpotsDialog(this);
        spotsDialog.show();
        spotsDialog.setContent("努力加载中...");
    }
    public void showDialog(String msg, boolean out) {
        if (spotsDialog != null && spotsDialog.isShowing()) {
            spotsDialog.dismiss();
        }
        spotsDialog = new SpotsDialog(this);
        spotsDialog.show();
        spotsDialog.setCanceledOnTouchOutside(out);
        spotsDialog.setContent(msg);
    }

    public void hideDialog() {
        if (spotsDialog != null) {
            spotsDialog.dismiss();
        }
    }

    public void showToast(String msg) {
        MyToast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
