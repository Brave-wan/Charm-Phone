package www.jinke.com.charmhome.presenter.family;

import android.content.Context;

import com.orvibo.homemate.api.HouseApi;
import com.orvibo.homemate.api.listener.BaseResultListener;
import com.orvibo.homemate.bo.Room;
import com.orvibo.homemate.event.BaseEvent;

import www.jinke.com.charmhome.Base.Config;
import www.jinke.com.charmhome.view.family.IEditRoomView;

/**
 * Created by root on 18-3-14.
 */

public class EditRoomPresent {
    private Context mContext;
    IEditRoomView mView;

    public EditRoomPresent(Context mContext, IEditRoomView mView) {
        this.mContext = mContext;
        this.mView = mView;
    }


    public void getDeleteRoom(String roomId) {//删除房间
        if (mView != null) {
            mView.showLoading("");
            HouseApi.deleteRoom(Config.userName, roomId, new BaseResultListener() {
                @Override
                public void onResultReturn(BaseEvent baseEvent) {
                    if (baseEvent.isSuccess()) {
                        mView.onDeleteSuccess();
                    }
                    mView.hideLoading();
                }
            });
        }
    }

    public void modifyRoom(Room room) {//修改房间
        if (mView != null) {
            mView.showLoading("");
            HouseApi.modifyRoom(room, new BaseResultListener.DataListener() {
                @Override
                public void onResultReturn(BaseEvent baseEvent, Object o) {
                    if (baseEvent.isSuccess()) {
                        mView.onDeleteSuccess();
                    }
                    mView.hideLoading();
                }
            });
        }
    }
}
