package wanka.com.edittextsamp.baseactivity;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fml on 2016/1/11 0011.
 */
public class WHelperUtil {
    public  static final int FRIST = -1;
    public  static final int TWO = -2;
    public static final int PAGER_SIZE = 8;
    /**  注册获取验证码的Type */
    public static final String Register_Type = "1";
    /** 找回密码所需Type */
    public static final String GetPass_Type = "2";
    static  Count count = null;
    /**
     * 判断是否是手机号
     */
    public final static String MATCH_MOBILE = "[1][34578]\\d{9}";
    public static boolean isMobileRight(Context context , String mobile) {
        // String reg = "^[1]([358][0-9]{1}|30|59|58|88|89)[0-9]{8}$";
        if(mobile.isEmpty()){
//            AhTost.toast(context, "手机号码不能为空");
            Log.i("LOA", "走C");
            return false;
        }
        Pattern p = Pattern.compile(MATCH_MOBILE);
        Matcher m = p.matcher(mobile);
        if(!m.matches()){
//            AhTost.toast(context , "手机号码格式不正确");
            return false;
        }
        return true;
    }
    /*
    * 判读邮箱的格式
    * */
    public static boolean isEmail(String email) {
        if (email.length() < 6) {
            return false;
        }
        return matches(
                "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
                email);
    }
    /**
     * 匹配正则
     */
    private static boolean matches(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    /** 获取当前程序的包名 */
    public static String getPackageName(Context context){
        PackageInfo info;
        String code = "";
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            // 当前应用的版本名称
            code = info.versionName;
            // 当前版本的版本号
            //            int versionCode = info.versionCode;
            // 当前版本的包名
            //            String packageNames = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
    /**
     * 截取String str = "a,s,d,f,f" 返回一个集合
     */
    public static List<String> getStrListAll(String str) {
        if(str.isEmpty()){
            return new ArrayList<String>();
        }
        List<String> list = new ArrayList<String>();
        String[] string = str.split(",");
        for(int i = 0;i<string.length;i++){
            list.add(string[i]);
        }

        return list;
    }
    /***
     * 倒计时
     * */
    public static class Count extends CountDownTimer {
        Button mBtnTime;
        public Count(long millisInFuture, long countDownInterval, Button btnTime) {
            super(millisInFuture, countDownInterval);
            this.mBtnTime = btnTime;
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mBtnTime.setText("重新发送" + "(" + getIntervalUpdateTime(millisUntilFinished) + ")");
        }
        @Override
        public void onFinish() {
            mBtnTime.setEnabled(true);
            mBtnTime.setText("获取验证码");
        }
    }
//    public static boolean isPassword(Context context, String str){
//        long l = str.length();
//        boolean b =false ;
//        if(l < 6){
//            AhTost.toast(context , "密码长度不能小于6位");
//            b= false;
//        }
//        if(l > 12){
//            AhTost.toast(context, "密码长度不能大于12位");
//            b= false;
//        }
//        if(l >= 6 && l <= 12){
//            b= true;
//        }
//        return b;
//    }
    public static String getIntervalUpdateTime(long intervalTime) {
        StringBuilder result = new StringBuilder();
        long interval = intervalTime / 1000;
        final long day = 24 * 60 * 60;
        final long hour = 60 * 60;
        final long minute = 60;
        int detailDay = 0;
        int detailHour = 0;
        int detailMinute = 0;
        int detailSecond = 0;
        if (interval >= day) {
            detailDay = (int) (interval / day);
            interval = interval - detailDay * day;
        }
        if (interval >= hour) {
            detailHour = (int) (interval / hour);
            interval = interval - hour * detailHour;
        }
        if (interval >= minute) {
            detailMinute = (int) (interval / minute);
            interval = interval - detailMinute * minute;
        }
        if (interval >=0) {
            detailSecond = (int) interval;
        }
        result.setLength(0);
        /*if (detailDay >= 0) {
            result.append(detailDay);
            result.append("天");
        }
        if (detailHour >= 0) {
            result.append(detailHour);
            result.append("小时");
        }
        if (detailMinute >= 0) {
            result.append(detailMinute);
            result.append("分");
        }*/
        if (detailSecond >= 0) {
            result.append(detailSecond);
            result.append("秒");
        }
        return result.toString();
    }
//    /**
//     * 请求验证码(请求验证码封装)
//     */
//    public static void initHttpYzm(final Activity activity,String phone,final Button btn,final ScrollView mLie) {
//        AsyHttpClicenUtils.getNewInstance(mLie).asyHttpClicenUtils(activity, LoginBean.class, mLie, new IUpdateUI<LoginBean>() {
//            @Override
//            public void update(LoginBean allData, LoadingAndRetryManager retryManager) {
//                if (!allData.isSuccess()) {
//                    AhTost.toast(activity,allData.getMsg());
//                    return;
//                }
//                if (allData.getData() != null) {
//                    AhTost.toast(activity,allData.getMsg());
//                    count = new Count(60000, 1000, btn);
//                    count.start();
//                    btn.setEnabled(false);
//                }
//            }
//            @Override
//            public void sendFail(ExceptionType s, LoadingAndRetryManager retryManager) {
//            }
//        }).post(W_Url.URL_SEND_CODE, W_RequestParams.sendCode(phone, "2"), true);
//    }
//
//    /**
//     * 请求验证码(请求验证码封装)
//     */
//    public static void initHttpYzm1(final Activity activity,String phone,final Button btn,final ScrollView mLie) {
//        AsyHttpClicenUtils.getNewInstance(mLie).asyHttpClicenUtils(activity, LoginBean.class, mLie, new IUpdateUI<LoginBean>() {
//            @Override
//            public void update(LoginBean allData, LoadingAndRetryManager retryManager) {
//                if (!allData.isSuccess()) {
//                    AhTost.toast(activity,allData.getMsg());
//                    return;
//                }
//                if (allData.getData() != null) {
//                    AhTost.toast(activity,allData.getMsg());
//                    count = new Count(60000, 1000, btn);
//                    count.start();
//                    btn.setEnabled(false);
//                }
//            }
//            @Override
//            public void sendFail(ExceptionType s, LoadingAndRetryManager retryManager) {
//            }
//        }).post(W_Url.URL_SEND_CODE, W_RequestParams.sendCode(phone, "1"), true);
//    }
    /*
   * 小红点消息（封装）
   * */
    public static void redBoots(TextView textView, int num){
        if(num > 0){
            textView.setVisibility(View.VISIBLE);
            textView.setText(num+"");
        }else {
            textView.setVisibility(View.GONE);
        }
    }
    /**
     * 时间戳 转换 年月日
     *
     * @param      * @return
     */
    public static String getStrTime(String cc_time) {

        if (null == cc_time || cc_time.equals("")) {

            return "";
        }
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
    /*
    * VIP等级判定
    * */
    public static void getVip(int num,TextView textView){
        if( num < 501){
            textView.setText("VIP1");
        }else if(num > 500 && num <2001){
            textView.setText("VIP2");
        }else if(num > 2000 && num <5001){
            textView.setText("VIP3");
        }else if(num > 5000 && num <20001){
            textView.setText("VIP4");
        }else if(num > 20001 && num <50001){
            textView.setText("VIP5");
        }else if(num > 50000 && num <100001){
            textView.setText("VIP6");
        }else if(num > 100000){
            textView.setText("VIP7");
        }

    }
    /*
    * 价格转换
    * */
    public static String getPrice(int price){
        if(price == 0){
            return "0.00";
        }else {
            java.text.DecimalFormat   df = new java.text.DecimalFormat("#.00");
            String zong = df.format(price);
            return zong;
        }

    }

//    /*
//    * prograss比例判定
//    * */
//    public static double getPingjun(double num,int growth){
//        double  mean = MathUtils.chuDouble(num,5.00);
//        double lastzhi = 0;
//        double sena;
//        if( growth < 5001){
//            sena = MathUtils.chuDouble(mean, 5000.00);
//            lastzhi = MathUtils.multiplicationDouble(growth,sena);
//        }else if(growth > 5000 && growth <40001){
//            sena = MathUtils.chuDouble(mean, 35000.00);
//            lastzhi = MathUtils.multiplicationDouble(growth,sena)+mean;
//
//        }else if(growth > 40000 && growth <60001){
//            sena = MathUtils.chuDouble(mean, 20000.00);
//            lastzhi = MathUtils.multiplicationDouble(growth,sena)+(mean*2);
//
//        }else if(growth > 60000 && growth <100001){
//            sena = MathUtils.chuDouble(mean, 40000.00);
//            Log.e("123","mean"+mean);
//
//            Log.e("123","sena"+sena);
//            lastzhi = MathUtils.multiplicationDouble(growth, sena)+(mean*3);
//
//        }else if(growth > 100001 && growth <200001){
//            sena = MathUtils.chuDouble(mean, 100000.00);
//            lastzhi = MathUtils.multiplicationDouble(growth,sena)+(mean*4);
//        }
//        return lastzhi;
//    }

    /**
     * 拨打电话
     * @param context 上下文
     * @param s  电话号码
     */
    public static void callphone(Context context,String s) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) //SIM卡没有就绪
        {
//            AhTost.toast(context, "请确认sim卡是否插入或者sim卡暂时不可用");

            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + s);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }


}
