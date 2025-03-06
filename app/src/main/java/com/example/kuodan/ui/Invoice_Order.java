package com.example.kuodan.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuodan.NetUtils;
import com.example.kuodan.R;
import com.example.kuodan.adapter.Invoice_Adatper;
import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.Invoice_Reslult;
import com.example.kuodan.net_connection.Server_Response;
import com.example.kuodan.tool.TLog;
import com.example.kuodan.tool.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 出库待扫码验证20221209
 */
public class Invoice_Order extends BaseActivity implements Server_Response.OnGetSeverLister {

    public String userName;

    @BindView(R.id.finish_back)//返回
    ImageView finishBack;

    @BindView(R.id.textView1)//出库待扫码验证清单（文字）
    TextView textView1;

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

//    EditText foucasResulttwo;

    @BindView(R.id.time_tv)
    TextView timeTv;

    @BindView(R.id.search_btn)
    Button searchBtn;

    @BindView(R.id.search_btn2)
    Button searchBtn2;


    private Context context;

    private Invoice_Adatper adatper;
    private List<Invoice_Reslult.ListBean> list;
//    String loginName ;

    @Override
    public int loadWindowLayout() {
        return R.layout.invoice_activity;
    }

    @Override
    public void initViews() throws Exception {
        context = this;
//        foucasResulttwo = findViewById(R.id.foucas_result1);

        userName = getIntent().getStringExtra("userName");
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        adatper = new Invoice_Adatper(this);
        timeTv.setText(getCurrentTime());
        getOrderList(0);


    }
    private String getCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);

    }


    /** 获取列表 获取出库材料信息20221219 */
    private void  getOrderList(int o){
        TLog.e("==获取列表==");
        NetUtils.getOutinfoList(this,timeTv.getText().toString(), userName , o);
    }
    //                返回             日期           查询
    @OnClick({R.id.finish_back, R.id.time_tv, R.id.search_btn, R.id.search_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_back://返回
                finish();
                break;
            case R.id.time_tv://时间选择
                Utils.initTimePickerYear2(this,timeTv);
                break;
            case R.id.search_btn://查询
                getOrderList(0);
                break;
            case R.id.search_btn2://查询
                getOrderList(1);
                break;
        }
    }

    @Override
    public void onSuccess(Object response) {
        hideProgress();
        if (response instanceof Invoice_Reslult){
            Invoice_Reslult reslult = (Invoice_Reslult) response;
            list = reslult.getList();
            TLog.e("==获取列表list=="+list);
            adatper.setList(list);
            recycleview.setAdapter(adatper);
        }
    }

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);
    }
}
