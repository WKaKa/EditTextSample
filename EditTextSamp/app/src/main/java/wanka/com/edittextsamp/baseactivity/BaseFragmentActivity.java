package wanka.com.edittextsamp.baseactivity;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class BaseFragmentActivity extends Fragment {
    /**
     * 通知栏颜色
     * */
    public void setStatusBarTintResource(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);//通知栏所需颜色
        }
    }
    /** 设置状态 */
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
