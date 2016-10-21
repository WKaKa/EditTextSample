package wanka.com.edittextsamp.baseactivity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class LoginUtils {
    private Context mContext;
    boolean bool1 = false;
    boolean bool2 = false;

    public LoginUtils(Context context){
        mContext = context;
    }
    public  void setPasswordListener(final EditText edit, final TextView hint,final View view, final ImageView look,final EditPhoneListener listener){
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String editText = edit.getText().toString().trim();
                if(editText.length()>= 6){
                    bool1= true;
                }else{
                    bool1= false;
                }
                if(hasFocus && editText.length() == 0){
                    view.setBackgroundColor(0xffffe313);
                    look.setVisibility(View.GONE);

                }else if(!hasFocus) {
                    if(editText.equals("")){
                        hint.setVisibility(View.INVISIBLE);
                        view.setBackgroundColor(0xffd3d3d3);
                        look.setVisibility(View.GONE);
                        return;
                    }
                    if(bool1){
                        look.setVisibility(View.VISIBLE);
                        listener.isPhoneRight(true);
                        hint.setText("正确");
                        hint.setTextColor(0xff3CD21E);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xff3CD21E);
                    }else if(editText.length()<6){
                        look.setVisibility(View.VISIBLE);
                        listener.isPhoneRight(false);
                        hint.setText( "密码长度不够");
                        hint.setTextColor(0xffe40b12);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xffe40b12);

                    }
                }
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String editText = s.toString().trim();
                if(editText.length()>= 6){
                    bool1= true;
                }else{
                    bool1= false;
                }
                if(editText.equals("")){
                    hint.setVisibility(View.INVISIBLE);
                    look.setVisibility(View.GONE);
                    view.setBackgroundColor(0xffd3d3d3);
                    return;
                }
                if(bool1){
                    look.setVisibility(View.VISIBLE);
                    listener.isPhoneRight(true);
                    hint.setText("正确");
                    hint.setTextColor(0xff3CD21E);
                    hint.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(0xff3CD21E);
                }else if(editText.length()>20){
                    look.setVisibility(View.VISIBLE);
                    Log.e("123", "长度++" + editText.length());
                    listener.isPhoneRight(false);
                    hint.setText( "超出字符限制");
                    hint.setTextColor(0xffe40b12);
                    hint.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(0xffe40b12);

                }else if( editText.length()<6){
                    listener.isPhoneRight(false);
                    hint.setVisibility(View.INVISIBLE);
                    view.setBackgroundColor(0xffffe313);
                }

            }
        });
    }
    public void setPhoneListener(final EditText edit, final TextView hint , final ImageView phoneCancle,final View view, final EditPasswordListener listener){
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String editText = edit.getText().toString().trim();
                if(hasFocus && editText.length() == 0){
                    view.setBackgroundColor(0xffffe313);
                }else if(!hasFocus) {
                    if(editText.equals("")){
                        hint.setVisibility(View.INVISIBLE);
                        view.setBackgroundColor(0xffd3d3d3);
                        return;
                    }
                    if (editText.equals("")) {
                        phoneCancle.setVisibility(View.GONE);
                    } else {
                        phoneCancle.setVisibility(View.VISIBLE);
                    }

                    if (WHelperUtil.isMobileRight(mContext, editText)) {
                        bool2 = true;
                    } else {
                        bool2 = false;
                    }
                    if (bool2) {
                        listener.isPasswordRight(true);
                        hint.setText("正确");
                        hint.setTextColor(0xff3CD21E);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xff3CD21E);
                    } else if(!bool2 && editText.length()<11){
                        listener.isPasswordRight(false);
                        hint.setText("请输入11位手机号码");
                        hint.setTextColor(0xffe40b12);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xffe40b12);

                    }
                }
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editText = s.toString().trim();
                if (editText.equals("")) {
                    phoneCancle.setVisibility(View.GONE);
                } else {
                    phoneCancle.setVisibility(View.VISIBLE);
                }

                if (WHelperUtil.isMobileRight(mContext, editText)) {
                    bool2 = true;
                } else {
                    bool2 = false;
                }
                if (editText.equals("")) {
                    hint.setVisibility(View.INVISIBLE);
                    view.setBackgroundColor(0xffd3d3d3);
                    return;
                }
                if (bool2) {
                    listener.isPasswordRight(true);
                    hint.setText("正确");
                    hint.setTextColor(0xff3CD21E);
                    hint.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(0xff3CD21E);
                } else if(!bool2 && editText.length()>11){
                    listener.isPasswordRight(false);
                    hint.setText("请输入11位手机号码");
                    hint.setTextColor(0xffe40b12);
                    hint.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(0xffe40b12);

                }else if(!bool2 && editText.length()<11){
                    listener.isPasswordRight(false);
                    hint.setVisibility(View.INVISIBLE);
                    view.setBackgroundColor(0xffffe313);
                }
            }
        });
    }
    public void setAuthCodeListener(final EditText edit, final TextView hint,final View view,final ImageView codeCanle, final EditAuthCodeListener listener ){
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String editText = edit.getText().toString().trim();
                if(hasFocus && editText.length() == 0){
                    view.setBackgroundColor(0xffffe313);
                }else if(!hasFocus) {
                    if (editText.equals("")) {
                        codeCanle.setVisibility(View.GONE);
                    } else {
                        codeCanle.setVisibility(View.VISIBLE);
                    }
                    if(editText.equals("")){
                        hint.setVisibility(View.INVISIBLE);
                        view.setBackgroundColor(0xffd3d3d3);
                        return;
                    }


                    if(editText.length() == 6){
                        listener.isAuthCodeRight(true);
                        hint.setText("正确");
                        hint.setTextColor(0xff3CD21E);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xff3CD21E);
                    }else if (editText.length()< 6 || editText.length()>6){
                        listener.isAuthCodeRight(false);
                        hint.setText("请输入6位验证码");
                        hint.setTextColor(0xffe40b12);
                        hint.setVisibility(View.VISIBLE);
                        view.setBackgroundColor(0xffe40b12);
                    }
                }
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String editText = s.toString().trim();
                boolean bool = false;
                if(editText.length() == 6){
                    bool= true;
                }else{
                    bool= false;
                }
                if (editText.equals("")) {
                    codeCanle.setVisibility(View.GONE);
                } else {
                    codeCanle.setVisibility(View.VISIBLE);
                }
                if(editText.equals("")){
                    hint.setVisibility(View.INVISIBLE);
                    view.setBackgroundColor(0xffffe313);
                    return;
                }

                if(bool){
                    listener.isAuthCodeRight(true);
                    hint.setText("正确");
                    hint.setTextColor(0xff3CD21E);
                    hint.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(0xff3CD21E);
                }else{
                    listener.isAuthCodeRight(false);
                    hint.setVisibility(View.INVISIBLE);
                    view.setBackgroundColor(0xffffe313);

                }

            }
        });
    }
    public interface EditPhoneListener{
        void isPhoneRight(boolean bool);
    }
    public interface EditPasswordListener{
        void isPasswordRight(boolean bool);
    }
    public interface EditAuthCodeListener{
        void isAuthCodeRight(boolean bool);
    }
}
