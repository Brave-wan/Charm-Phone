package www.jinke.com.charmhome.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.ui.amin.BaseEffects;
import www.jinke.com.charmhome.ui.amin.Effectstype;

/**
 * Created by root on 17-12-13.
 */

public class LogoutDialog extends Dialog implements DialogInterface.OnShowListener, View.OnClickListener {
    TextView tx_logout_dialog_title;
    TextView tx_logout_dialog_content;
    TextView tx_logout_dialog_line;
    TextView tx_logout_dialog_sure;
    TextView tx_logout_dialog_cancel;
    LinearLayout ll_logout_dialog_bottom;
    onLogoutListener listener;
    RelativeLayout relativeLayout;

    private int mDuration = 500;
    private Effectstype type = null;

    public void setOnLogoutListener(onLogoutListener listener) {
        this.listener = listener;
    }

    Context context;

    public LogoutDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_logout);


        ll_logout_dialog_bottom = findViewById(R.id.ll_logout_dialog_bottom);
        tx_logout_dialog_sure = findViewById(R.id.tx_logout_dialog_sure);
        tx_logout_dialog_cancel = findViewById(R.id.tx_logout_dialog_cancel);
        tx_logout_dialog_line = findViewById(R.id.tx_logout_dialog_line);
        tx_logout_dialog_content = findViewById(R.id.tx_logout_dialog_content);
        tx_logout_dialog_title = findViewById(R.id.tx_logout_dialog_title);
        relativeLayout = findViewById(R.id.rl_root);
        tx_logout_dialog_cancel.setOnClickListener(this);
        tx_logout_dialog_sure.setOnClickListener(this);


        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        layoutParams.width = (int) (display.getWidth() * 0.8);
        layoutParams.y = -100;
        window.setAttributes(layoutParams);
        this.setOnShowListener(this);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        if (type == null) {
            type = Effectstype.Sidefill;
            start(type);
        }
    }

    public void setLogOutContent(String msg) {
        if (tx_logout_dialog_content != null) {
            tx_logout_dialog_content.setText(msg);
        }
    }


    public void setLeftText(String tx) {
        tx_logout_dialog_sure.setText(tx);
    }

    public void setLeftText(String tx, int color) {
        tx_logout_dialog_sure.setText(tx);
        tx_logout_dialog_sure.setTextColor(context.getResources().getColor(color));
    }

    public void setRightText(String tx) {
        tx_logout_dialog_cancel.setText(tx);
    }

    public void setRightText(String tx, int color) {
        tx_logout_dialog_cancel.setText(tx);
        tx_logout_dialog_cancel.setTextColor(context.getResources().getColor(color));
    }

    public void setLogOutTitle(boolean isShow) {
        tx_logout_dialog_title.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setTitleText(String tx) {
        tx_logout_dialog_title.setText(tx);
    }

    public void setTitleText(String tx, int color) {
        tx_logout_dialog_title.setText(tx);
        tx_logout_dialog_title.setTextColor(context.getResources().getColor(color));

    }

    public void setLogOutBottom(boolean isShow) {
        ll_logout_dialog_bottom.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeLayout);
    }

    public void setToastMode(String msg, int color) {
        tx_logout_dialog_content.setText(msg);
        tx_logout_dialog_content.setTextColor(context.getResources().getColor(color));
        tx_logout_dialog_title.setVisibility(View.GONE);
        ll_logout_dialog_bottom.setVisibility(View.GONE);
        tx_logout_dialog_line.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_logout_dialog_sure) {
            if (listener != null) {
                listener.onLeftClick();
                this.dismiss();
            }
        } else if (view.getId() == R.id.tx_logout_dialog_cancel) {
            if (listener != null) {
                listener.onRightClick();
            }
            this.dismiss();
        }
    }

    public interface onLogoutListener {
        void onLeftClick();

        void onRightClick();
    }
}
