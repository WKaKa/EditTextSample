package wanka.com.edittextsamp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import wanka.com.edittextsamp.baseactivity.AhView;
import wanka.com.edittextsamp.baseactivity.BaseActivity;
import wanka.com.edittextsamp.baseactivity.LoginUtils;

@AhView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.m_title_return)
    ImageView mTitleReturn;
    @InjectView(R.id.m_title)
    TextView mTitle;
    @InjectView(R.id.m_right)
    TextView mRight;
    @InjectView(R.id.m_phone_edt)
    EditText mPhoneEdt;
    @InjectView(R.id.m_cancle)
    ImageView mCancle;
    @InjectView(R.id.m_phone_edt_bg)
    View mPhoneEdtBg;
    @InjectView(R.id.m_phone_hint)
    TextView mPhoneHint;
    @InjectView(R.id.m_code_edt)
    EditText mCodeEdt;
    @InjectView(R.id.m_code_cancle)
    ImageView mCodeCancle;
    @InjectView(R.id.m_get_code)
    Button mGetCode;
    @InjectView(R.id.m_code_edt_bg)
    View mCodeEdtBg;
    @InjectView(R.id.m_code_hint)
    TextView mCodeHint;
    @InjectView(R.id.m_pass_edt)
    EditText mPassEdt;
    @InjectView(R.id.m_look)
    ImageView mLook;
    @InjectView(R.id.m_pass_edt_bg)
    View mPassEdtBg;
    @InjectView(R.id.m_pass_hint)
    TextView mPassHint;
    @InjectView(R.id.m_user_agreement)
    TextView mUserAgreement;
    @InjectView(R.id.m_go_login)
    Button mGoLogin;
    private LoginUtils mLoginUtils;
    private boolean mIsPhoneRight = false;
    private boolean mIsPasswordRight = false;
    private boolean mIsPasswordLook = false;
    private boolean mIsCodeLook = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("注册");
        initView();
    }
    private void initView() {
        editListener();

    }
    private void editListener() {
        mLoginUtils = new LoginUtils(MainActivity.this);
        //手机号
        mLoginUtils.setPhoneListener(mPhoneEdt, mPhoneHint, mCancle, mPhoneEdtBg, new LoginUtils.EditPasswordListener() {
            @Override
            public void isPasswordRight(boolean bool) {
                mIsPhoneRight = bool;
                isLogin();
//                if(!mEditPhone.getText().toString().equals("")){
//                    mBuySoyListener.dispatch(mEditPhone.getText().toString().trim());
//
//                }
            }
        });
        //密码
        mLoginUtils.setPasswordListener(mPassEdt, mPassHint, mPassEdtBg, mLook, new LoginUtils.EditPhoneListener() {
            @Override
            public void isPhoneRight(boolean bool) {
                mIsPasswordRight = bool;
                isLogin();
            }
        });
        //验证码
        mLoginUtils.setAuthCodeListener(mCodeEdt, mCodeHint, mCodeEdtBg, mCodeCancle, new LoginUtils.EditAuthCodeListener() {
            @Override
            public void isAuthCodeRight(boolean bool) {
                mIsCodeLook = bool;
                isLogin();
            }
        });


    }
    private void isLogin() {
        if (mIsPhoneRight && mIsPasswordRight && mIsCodeLook) {
            mGoLogin.setEnabled(true);
        } else {
            mGoLogin.setEnabled(false);
        }
    }

    @OnClick({R.id.m_title_return, R.id.m_cancle, R.id.m_code_cancle, R.id.m_get_code, R.id.m_look, R.id.m_go_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_title_return:
                break;
            case R.id.m_cancle:
                mPhoneEdt.setText("");
                break;
            case R.id.m_code_cancle:
                mCodeEdt.setText("");
                break;
            case R.id.m_get_code:
                toast("获取验证码");

                break;
            case R.id.m_look:
                if (mIsPasswordLook) {
                    mPassEdt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mLook.setImageResource(R.mipmap.login_open);
                } else {
                    mPassEdt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mLook.setImageResource(R.mipmap.login_off);
                }
                mPassEdt.setSelection(mPassEdt.length());
                mIsPasswordLook = !mIsPasswordLook;
                break;
            case R.id.m_go_login:
                break;
        }
    }
}
