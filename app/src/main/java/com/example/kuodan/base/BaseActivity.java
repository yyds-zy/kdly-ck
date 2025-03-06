package com.example.kuodan.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kuodan.tool.Contants;
import com.example.kuodan.tool.SPUtils;


public abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        //  Utils.setCJ(this);
        //加载一个布局
        //  invadeStatusBar();

        userName = (String) SPUtils.get(this, Contants.USERNAME, "");

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.GONE);
        progressBar.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        View rootView = View.inflate(this, this.loadWindowLayout(), null);
        linearLayout.addView(progressBar);
        frameLayout.addView(rootView);
        frameLayout.addView(linearLayout);
        setContentView(frameLayout);


        try {
            initViews();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getUserName(){
        return userName;

    }

    /**
     * setVisibility():
     * 有三个参数：Parameters:visibility One of VISIBLE, INVISIBLE, or GONE，想对应的三个常量值：0、4、8
     * VISIBLE:0  意思是可见的
     * INVISIBILITY:4 意思是不可见的，但还占着原来的空间
     * GONE:8  意思是不可见的，不占用原来的布局空间
     */
    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
    }








    //该抽象方法用于为Activit设置xml
    public abstract int loadWindowLayout();

    public abstract void initViews() throws Exception;


    public void startActivity(Class clazz) {

        startActivity(new Intent(this, clazz));

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public void tostShow(String content) {//显示提示
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }


}
