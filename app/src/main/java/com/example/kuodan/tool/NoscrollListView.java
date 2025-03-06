package com.example.kuodan.tool;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.tool
 * @date on 2020/4/17 15:49
 * @wechat 18813158027
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoscrollListView extends ListView {

    public NoscrollListView(Context context) {
        super(context);
    }

    public NoscrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoscrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
