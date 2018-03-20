package www.jinke.com.charmhome.Base;

import android.app.Application;
import android.text.TextUtils;

import com.orvibo.homemate.api.OrviboApi;
import com.orvibo.homemate.api.UserApi;
import com.orvibo.homemate.util.AppTool;

/**
 * Created by root on 18-3-13.
 */

public class Config {
    public static final String userName = "15155120227";
    public static final String userPwd = "jkwy8888";

    public static void initOvb(Application application) {
        if (isMainProcess(application)) {
            //初始化HomeMate SDK。如果app继承了VihomeApplication则不需要再初始化sdk；如果没有继承则需要先初始化。
            OrviboApi.initHomeMateSDK(application);
            //TODO SDK source初始化，需要替换为真实的source。source获取需要商务谈判，签了合同后欧瑞博会分配相应的source。可以理解为app key。
            UserApi.initSource("HomeMateTestApi");
            UserApi.setDebugMode(true, false);
        }
    }

    /**
     * @return true 主进程启动
     */
    private static boolean isMainProcess(Application application) {
        String processName = AppTool.getProcessName();
        return !TextUtils.isEmpty(processName) && processName.equals(application.getPackageName());
    }
}
