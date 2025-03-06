package com.example.kuodan.tool;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Android_apple on 2017/12/7.
 */

public class MyRecyclerView extends RecyclerView {


    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
/*

Android提供了一个更强大的滚动控件——RecyclerView。 它可以说是一个增强版的ListView，
不仅可以轻松实现和ListView同样的效果， 还优化了ListView中存在的各种不足之处。
目前Android官方更加推荐使用RecyclerView， 未来也会有更多的程序逐渐从ListView转向RecyclerView。

*/
