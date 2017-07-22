package com.example.sixtong.competition;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //保存密码 信息持久化

    private EditText accountEdit;
    private EditText passwdEdit;
    //获得输入账号输入密码的文本框

    private String inputAccount;
    private String inputPasswd;
    //输入的账号密码

    boolean isAgree;
    private CheckBox agreeP;
    //获得是否同意用户协议

    private Button login;
    //获得登陆按钮

    private Button forgetPasswd;
    //获得忘记密码的按钮

    private Button register;
    //注册的按钮

    private  Button agreement;
    //获得用户协议的按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = (EditText)findViewById(R.id.account);
        passwdEdit = (EditText)findViewById(R.id.passwd);
        //获得账号密码输入框的实例

        //建立监听器对象
        LoginListener loginListener = new LoginListener();

        agreeP = (CheckBox)findViewById(R.id.read);//是否同意agreement

        login = (Button)findViewById(R.id.login);//登录按钮
        login.setOnClickListener(loginListener);

        //忘记密码按钮
        forgetPasswd = (Button)findViewById(R.id.forgetPasswd);
        forgetPasswd.setOnClickListener(loginListener);

        //注册
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(loginListener);

        //获取agreement
        agreement = (Button)findViewById(R.id.agreement);
        agreement.setOnClickListener(loginListener);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean agree = pref.getBoolean("agreep",false);
        //获取用户上次是否同意协议
        Log.d("boolean", String.valueOf(agree));
        if(agree){
            accountEdit.setText(pref.getString("account",""));
            passwdEdit.setText(pref.getString("passwd",""));
            agreeP.setChecked(true);
        }

    }
    //登录按钮的监听器
    public class LoginListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.login:
                    isAgree = agreeP.isChecked();
                    if(isAgree) {
                        inputAccount = accountEdit.getText().toString();
                        inputPasswd = passwdEdit.getText().toString();

                        editor = pref.edit();
                        editor.putBoolean("agreep",true);

                        boolean canLogin = true;
                        //如果密码匹配
                        if(canLogin){
                            editor.putString("account",inputAccount);
                            editor.putString("passwd",inputPasswd);
                        }else{
                            Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                        }
                        editor.apply();
                    }else{
                        Toast.makeText(LoginActivity.this,"请先同意用户使用条款",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.forgetPasswd:

                    break;
                case R.id.register:

                    break;
                case R.id.agreement:

                    break;

                default: break;

            }

        }
    }

}
