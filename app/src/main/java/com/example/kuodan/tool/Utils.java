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

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.kuodan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.tool
 * @date on 2020/4/13 17:50
 * @wechat 18813158027
 */
public class Utils {
    private static Context context;
    private static final int COLOR = 0xFF323334;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();

    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }
    public static String getMonthTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 只带年
     *
     * @param tvDate
     */
    public static void initTimePickerYear2(Context context, TextView tvDate) {
        /**
         * @description
         *
         * 因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1950, 1, 1);
        Calendar endDate = Calendar.getInstance();

        TimePickerView timePicker = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvDate.setText(getMonthTime(date));



            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setContentTextSize(18).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .setLineSpacingMultiplier(1.7f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setSubmitText("确定").setCancelText("取消")
                .setSubmitColor(COLOR).setCancelColor(COLOR)
                .build();
        if (timePicker != null) {
            timePicker.show();
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
    public interface OnButtonEventListener2 {

        void onConfirm();
    }




}
