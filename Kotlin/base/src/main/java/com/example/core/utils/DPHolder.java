package com.example.core.utils;

import android.content.Context;
import android.util.Log;

import com.bytedance.applog.AppLog;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.UriConstants;
import com.bytedance.sdk.dp.DPSdk;
import com.bytedance.sdk.dp.DPSdkConfig;
import com.bytedance.sdk.dp.DPWidgetDrawParams;
import com.bytedance.sdk.dp.DPWidgetGridParams;
import com.bytedance.sdk.dp.DPWidgetNewsParams;
import com.bytedance.sdk.dp.DPWidgetVideoCardParams;
import com.bytedance.sdk.dp.DPWidgetVideoSingleCardParams;
import com.bytedance.sdk.dp.IDPWidget;
import com.bytedance.sdk.dp.IDPWidgetFactory;

/**
 * Create by hanweiwei on 2020-03-26.
 */
public final class DPHolder {
    private static volatile DPHolder sInstance;

    public static DPHolder getInstance() {
        if (sInstance == null) {
            synchronized (DPHolder.class) {
                if (sInstance == null) {
                    sInstance = new DPHolder();
                }
            }
        }
        return sInstance;
    }

    private DPHolder() {
    }

    public void init(Context context) {
        //先初始化applog，一定要在DPSdk之前进行初始化
        final InitConfig appLogConfig = new InitConfig("181250", "dpdemo");
        appLogConfig.setUriConfig(UriConstants.DEFAULT);
        appLogConfig.setAbEnable(false);
        appLogConfig.setAutoStart(true);
        AppLog.init(context, appLogConfig);


        //1. 初始化，最好放到application.onCreate()执行
        //2. partner和secureKey请务必替换成自己的数据
        //3. 【重要】如果needInitAppLog=false，请确保AppLog初始化一定要在合作sdk初始化前
        final DPSdkConfig config = new DPSdkConfig.Builder()
                .debug(true)
                .needInitAppLog(false)
                .partner("sdk_test")
                .secureKey("9a7b664ab17db138f720e38f65a41757")
                .appId("181250")
                .initListener(new DPSdkConfig.InitListener() {
                    @Override
                    public void onInitComplete(boolean isSuccess) {
                        //注意：1如果您的初始化没有放到application，请确保使用时初始化已经成功
                        //     2如果您的初始化在application，可以忽略该初始化接口
                        //isSuccess=true表示初始化成功
                        //初始化失败，可以再次调用初始化接口（建议最多不要超过3次)

                        Log.e("DPHolder", "init result=" + isSuccess);
                    }
                })
                .build();

        DPSdk.init(context, config);
    }

    public IDPWidget buildDrawWidget(DPWidgetDrawParams params) {
        //创建draw视频流组件
        return getFactory().createDraw(params);
    }

    public IDPWidget buildGridWidget(DPWidgetGridParams params) {
        //创建宫格组件
        return getFactory().createGrid(params);
    }

    public IDPWidget buildNewsTabsWidget(DPWidgetNewsParams params) {
        //创建多频道新闻组件
        return getFactory().createNewsTabs(params);
    }

    public void loadVideoCard(DPWidgetVideoCardParams params, IDPWidgetFactory.Callback callback) {
        getFactory().loadVideoCard(params, callback);
    }

    public void loadVideoSingleCard(DPWidgetVideoSingleCardParams params, IDPWidgetFactory.Callback callback) {
        getFactory().loadVideoSingleCard(params, callback);
    }

    private IDPWidgetFactory getFactory() {
        //一定要初始化后才能调用，否则会发生异常问题
        return DPSdk.factory();
    }

}
