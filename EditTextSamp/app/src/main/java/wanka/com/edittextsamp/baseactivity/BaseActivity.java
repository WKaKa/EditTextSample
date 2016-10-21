package wanka.com.edittextsamp.baseactivity;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import wanka.com.edittextsamp.R;


public abstract class BaseActivity extends AutoLayoutActivity {
    private static Activity context;
    private static long toastNextTime;
    @Override
    protected void onCreate(Bundle arg0) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(arg0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this; //??
        try {
            setContentView(InjectHelper.injectObject(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.inject(BaseActivity.this);
        setStatusBarTintResource(R.color.color_title);
//        ZgqActivityUtils.getInstance().addActivity(this);
    }
    /**
    * 通知栏颜色
    * */
    public void setStatusBarTintResource(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);//通知栏所需颜色
        }
    }
    /** 设置状态 */
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }
    /** 显示Toast */
    public static void toast(String toast) {
//        AhTost.toast(context, toast);
    }
    /** 显示Snackbar Toast */
    public static void snackBarToast(View layout,String toast) {
//        AhTost.snackBar(layout, toast);
    }
    /** 显示Log */
    public static void log(String toast) {
        Log.i("TAG", toast);
    }
    /**
    * 解决页面第一次请求会闪问题
    * */
    public static void requestNullData(View view) {
        requestNullData(context, view);
    }
    /**
     * 解决页面第一次请求会闪问题
     * */
    public static void requestNullData(Activity activity, View view) {
//        AsyHttpClicenUtils.getNewInstance(view).asyHttpClicenUtils(activity, QiNiuBean.class, view, new IUpdateUI<QiNiuBean>() {
//            @Override
//            public void update(QiNiuBean allData, LoadingAndRetryManager viewManage) {
//                viewManage.showContent();
//            }
//
//            @Override
//            public void sendFail(ExceptionType s, LoadingAndRetryManager viewManage) {
//                viewManage.showContent();
//            }
//        }).post(Z_Url.URL_QINIU, new RequestParams(), false);
    }
}
