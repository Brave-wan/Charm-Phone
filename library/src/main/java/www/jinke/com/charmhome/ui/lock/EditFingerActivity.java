package www.jinke.com.charmhome.ui.lock;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import www.jinke.com.charmhome.Base.BaseActivity;
import www.jinke.com.charmhome.R;
import www.jinke.com.charmhome.bean.FingerListBean;
import www.jinke.com.charmhome.presenter.lock.EditFingerPresenter;
import www.jinke.com.charmhome.ui.dialog.LockBluetoothDialog;
import www.jinke.com.charmhome.ui.dialog.LogoutDialog;
import www.jinke.com.charmhome.view.lock.IEditFingerView;

/**
 * Created by root on 17-12-13.
 * 编辑指纹
 */

public class EditFingerActivity extends BaseActivity implements View.OnClickListener,
        IEditFingerView, LogoutDialog.onLogoutListener, LockBluetoothDialog.onIKnowListener {
    private FingerListBean listBean;//指纹实体类
    private EditText tx_item_finger_name;//指纹名称输
    private TextView tx_item_lock_finger_delete;//删除指纹按钮
    private EditFingerPresenter presenter;

    @Override
    protected int getContentViewId() {//初始化视图布局
        return R.layout.activity_edit_finger;
    }

    @Override
    protected void initView() {//初始化title
        setTitleText(getString(R.string.charmHome_lock_device_delete_finger_title));
        setRightVisibility(getString(R.string.charmHome_lock_add_finish_btn));
        //初始化中间层
        presenter = new EditFingerPresenter(this);
    }

    @Override
    protected void onBackView(View view) {
        super.onBackView(view);
        finish();
    }

    @Override
    protected void onRightView(View view) {
        super.onRightView(view);
        //更新指纹名称
        if (!StringUtils.isEmpty(tx_item_finger_name.getText().toString().trim()) && listBean != null) {
            presenter.getUpdateFingerName(listBean.getFingerId() + "",
                    listBean.getFingerType() + "", tx_item_finger_name.getText().toString().trim());
        } else {
            showToast("请输入指纹名称!");
        }
    }

    @Override
    protected void findViewById() {//绑定控件并初始化
        listBean = (FingerListBean) getIntent().getSerializableExtra("listBean");
        tx_item_finger_name = findViewById(R.id.tx_item_finger_name);
        tx_item_lock_finger_delete = findViewById(R.id.tx_item_lock_finger_delete);
        tx_item_lock_finger_delete.setOnClickListener(this);
        if (listBean != null) {
            tx_item_finger_name.setText(listBean.getFingerName());
            tx_item_finger_name.setSelection(listBean.getFingerName().length());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tx_item_lock_finger_delete) {
            LogoutDialog dialog = new LogoutDialog(this);
            dialog.setOnLogoutListener(this);
            dialog.show();
            dialog.setLogOutContent(getString(R.string.charmHome_lock_device_edit_finger_toast));
        }
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg);
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void onUpdateSuccess(String s) {
        finish();
    }

    @Override
    public void showMsg(String s) {
        showToast(s);
    }

    @Override
    public void onLeftClick() {
        LockBluetoothDialog dialog = new LockBluetoothDialog(this);
        dialog.setOnIKnowListener(this);
        dialog.show();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void IKowBack() {
        if (listBean != null) {
            presenter.getFingerDelete(listBean.getLockSeq(), listBean.getFingerId() + "", listBean.getFingerLockId(), listBean.getFingerType() + "");
        }

    }
}
