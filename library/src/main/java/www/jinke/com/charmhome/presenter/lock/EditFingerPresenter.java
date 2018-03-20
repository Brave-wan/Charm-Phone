package www.jinke.com.charmhome.presenter.lock;

import www.jinke.com.charmhome.impl.EditFingerImpl;
import www.jinke.com.charmhome.listener.lock.IEditFingerListener;
import www.jinke.com.charmhome.view.lock.IEditFingerView;

/**
 * Created by root on 17-12-13.
 * 　指纹编辑控制器
 */

public class EditFingerPresenter implements IEditFingerListener {
    private IEditFingerView editFingerView;
    private EditFingerImpl editFinger;

    /**
     * 构造方法
     *
     * @param editFingerView 视图回调类
     */
    public EditFingerPresenter(IEditFingerView editFingerView) {
        this.editFingerView = editFingerView;
        editFinger = new EditFingerImpl();
    }

    /**
     * 删除门锁
     *
     * @param macAddress   设备mac地址
     * @param fingerId     　指纹id
     * @param fingerLockID 设备id
     * @param fingerType   指纹类型
     */
    public void getFingerDelete(String macAddress, String fingerId, String fingerLockID, String fingerType) {
        if (editFingerView != null) {
            editFingerView.showLoading("指纹删除中");
            editFinger.getFingerDelete(macAddress, fingerId, fingerLockID, fingerType, this);
        }
    }

    /**
     * 更新指纹名称
     *
     * @param fingerID      指纹id
     * @param fingerType    指纹类型
     * @param fingerNewName 　指纹名称
     */
    public void getUpdateFingerName(String fingerID, String fingerType, String fingerNewName) {
        if (editFingerView != null) {
            editFinger.getUpdateFingerName(fingerID, fingerType, fingerNewName, this);
            editFingerView.showLoading("加载中");
        }
    }

    @Override
    public void onUpdateSuccess(String s) {
        //更新设备名称成功方法回调
        if (editFingerView != null) {
            editFingerView.hideLoading();
            editFingerView.onUpdateSuccess(s);
        }
    }

    @Override
    public void showMsg(String s) {
        //所有错误信息方法回调显示
        if (editFingerView != null) {
            editFingerView.showMsg(s);
            editFingerView.hideLoading();
        }
    }

    @Override
    public void onDeleteSuccess(String s) {
        if (editFingerView != null) {
            editFingerView.hideLoading();
            editFingerView.onUpdateSuccess(s);
        }
    }
}
