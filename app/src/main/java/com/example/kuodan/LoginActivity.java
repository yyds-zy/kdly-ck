package com.example.kuodan;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.Login_Result;
import com.example.kuodan.net_connection.Server_Response;
import com.example.kuodan.tool.Contants;
import com.example.kuodan.tool.SPUtils;
import com.example.kuodan.ui.Invoice_Order;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan
 * @date on 2020/4/13 11:44
 * @wechat 18813158027
 */
public class LoginActivity extends BaseActivity implements Server_Response.OnGetSeverLister {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    private String userName;


    @Override
    public int loadWindowLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initViews() throws Exception {
        ButterKnife.bind(this);
    }

    @Override
    public void onSuccess(Object response) {
        hideProgress();
        if (response instanceof Login_Result){
            Login_Result login_result= (Login_Result) response;
            if (login_result.isSuccess()){
                tostShow("登录成功！");
                SPUtils.put(this, Contants.USERNAME,userName);
                Intent intent = new Intent();
                intent.setClass(this,Invoice_Order.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);
    }
    private void login(){
        userName = username.getText().toString();
        String passWord = password.getText().toString();
        if (TextUtils.isEmpty(userName)){
            tostShow("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(passWord)){
            tostShow("请输入密码");
            return;
        }
        showProgress();
        NetUtils.Login(this, userName,passWord);
    }



    @OnClick(R.id.login)
    public void onViewClicked() {
        login();
    }
}
