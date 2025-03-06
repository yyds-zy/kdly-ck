package com.example.kuodan.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kuodan.R;


/**
 * @author ..
 * 创建日期：2022/5/3
 * 文件名：MyUtils
 * 描述：
 */
public class MyUtils {

    public static String setSupplementFatalFrame(int num){
        String str="";
        if(num<10){
           return str="0"+num;
        }else{
            return str+num;
        }
    }

    public static void showDialogVersion2(Context context, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        Window window=dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
        LinearLayout layout = (LinearLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_alert, null);
        TextView textView=layout.findViewById(R.id.tv_content);
        textView.setText(Html.fromHtml(content));
        //  点击现在就去
        TextView btCommit = layout.findViewById(R.id.btn_reiger);

        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });




        int screenWidth = ScreenUtil.getScreenWidth(context);
        dialog.getWindow().setLayout((int) (screenWidth*3/4), WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(layout);
    }

}
