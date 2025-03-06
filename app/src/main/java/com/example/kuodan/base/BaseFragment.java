package com.example.kuodan.base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;



public abstract class BaseFragment extends Fragment {

    private ProgressBar progressBar;
    private Context context;



    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //加载一个布局
        View rootView = inflater.inflate(this.loadWindowLayout(), container, false);
        context=getContext();

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        progressBar = new ProgressBar(getContext());
        progressBar.setVisibility(View.GONE);
        progressBar.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(progressBar);
        frameLayout.addView(rootView);
        frameLayout.addView(linearLayout);
        initViews(frameLayout);

        return frameLayout;
    }



    //该抽象方法用于为Activit设置xml
    public abstract int loadWindowLayout();

    public abstract void initViews(View rootView);




}
