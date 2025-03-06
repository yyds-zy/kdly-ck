package com.example.kuodan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuodan.R;
import com.example.kuodan.model.Wlqd_Result;
import com.example.kuodan.tool.onFinishListener;
import com.example.kuodan.ui.Wlqd_Info;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.adapter
 * @date on 2020/4/14 9:54
 * @wechat 18813158027
 */
public class Wlqd_Info_Adapter extends RecyclerView.Adapter<Wlqd_Detail_ViewHolder> {

    private Context context;

    private List<Wlqd_Result.DetailBean> detailsList;

    private com.example.kuodan.tool.onFinishListener onFinishListener;


    public Wlqd_Info_Adapter(Context context) {
        this.context = context;
    }

    public List<Wlqd_Result.DetailBean> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Wlqd_Result.DetailBean> detailsList) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public Wlqd_Detail_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.wlqd_detail, viewGroup, false);
        return new Wlqd_Detail_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Wlqd_Detail_ViewHolder holder, int i) {
        System.out.println("------222-----"+context);
        String userName = ((Wlqd_Info) context).userName;
        System.out.println("------userName-222----"+userName);

        Wlqd_Result.DetailBean detailBean = detailsList.get(i);

        holder.cinvName.setText(detailBean.getCinvName());
        holder.cinvStd.setText(detailBean.getCinvStd());
        holder.hzqty.setText(detailBean.getHzqty());
        holder.ckqty.setText(detailBean.getOutqty());
        holder.insid.setText(detailBean.getInsid());
        holder.ckrwbj.setText(detailBean.getCkrwbj());
        holder.cinvcode.setText(detailBean.getCinvCode());
        holder.cunit.setText(detailBean.getCunit());
        holder.xcl.setText(detailBean.getXcl());

        holder.sfwc.setText(detailBean.getSfwc());
        holder.zxhj.setText(detailBean.getZxhj());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishListener.onFinish(detailBean.getCinvCode());
            }
        });

    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public void setOnFinishListener(onFinishListener onFinishListener){
        this.onFinishListener = onFinishListener;
    }

}
/**
 * 第三个页面显示 出库货位和数量 的类
 */
class Wlqd_Detail_ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cinv_name_tv)
    TextView cinvName;

    @BindView(R.id.cinv_std_tv)
    TextView cinvStd;

    @BindView(R.id.cinv_hzqty_tv)
    TextView hzqty;

    @BindView(R.id.cinv_ckqty_tv)
    TextView ckqty;

    @BindView(R.id.cinv_insid_tv)
    TextView insid;

    @BindView(R.id.cinv_ckrwbj_tv)
    TextView ckrwbj;

    @BindView(R.id.cinv_code_tv)
    TextView cinvcode;
    @BindView(R.id.cinv_cunit_tv)
    TextView cunit;
    @BindView(R.id.cinv_sfwc_tv)
    TextView sfwc;
    @BindView(R.id.cinv_zxhj_tv)
    TextView zxhj;
    @BindView(R.id.cinv_xcl_tv)
    TextView xcl;



    public Wlqd_Detail_ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

