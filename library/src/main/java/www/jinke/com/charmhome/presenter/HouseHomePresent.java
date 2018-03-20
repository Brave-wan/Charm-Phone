package www.jinke.com.charmhome.presenter;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.orvibo.homemate.api.LocalDataApi;
import com.orvibo.homemate.core.load.LoadTarget;
import com.orvibo.homemate.core.load.loadserver.LoadServer;
import com.orvibo.homemate.core.load.loadserver.OnLoadServerListener;
import com.orvibo.homemate.core.load.loadtable.LoadTable;
import com.orvibo.homemate.model.family.FamilyManager;

import java.util.List;

/**
 * Created by root on 18-3-13.
 */

public class HouseHomePresent implements OnLoadServerListener, LoadTable.OnLoadTableListener {
    private Context mContext;

    public HouseHomePresent(Context mContext) {
        this.mContext = mContext;
        LoadServer.getInstance(mContext).addOnLoadServerListener(this);
        LoadTable.getInstance(mContext).addOnLoadTableListener(this);
    }


    public void getDevicesByRoom(String familyId, String roomId) {

    }

    @Override
    public void onLoadServerFinish(List<String> tableNames, int result) {
        //读取服务器数据(这里的数据包括绝大部分表)后回调的接口，result成功时tableNames有效
        LogUtils.i("tableNames:" + tableNames + ",result:" + FamilyManager.getCurrentFamilyId());
    }

    @Override
    public void onLoadTableFinish(LoadTarget loadTarget, boolean hasData, int result) {
        //读取某个表回调结果
        LogUtils.i("loadTarget:" + loadTarget + ",hasData:" + hasData + ",result:" + result);
    }
}
