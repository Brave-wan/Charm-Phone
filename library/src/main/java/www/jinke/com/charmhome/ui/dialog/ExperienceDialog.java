package www.jinke.com.charmhome.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import www.jinke.com.charmhome.R;

/**
 * Created by root on 18-3-12.
 */

public class ExperienceDialog extends Dialog implements View.OnClickListener {
    ImageView img_dialog;
    TextView tx_cancel;
    Context mContext;
    private int res;

    public ExperienceDialog(@NonNull Context context, int res) {
        super(context);
        this.mContext = context;
        this.res = res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_experience);
        img_dialog = findViewById(R.id.img_dialog);
        tx_cancel = findViewById(R.id.tx_cancel);
        tx_cancel.setOnClickListener(this);
        img_dialog.setBackground(mContext.getResources().getDrawable(res));

        Window window = this.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        layoutParams.width = (int) (display.getWidth() * 0.9);
        layoutParams.y = -100;
        window.setAttributes(layoutParams);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_cancel) {
            this.dismiss();
        }
    }

    public void setContentImage(int res) {
        img_dialog.setBackground(mContext.getResources().getDrawable(res));
    }
}
