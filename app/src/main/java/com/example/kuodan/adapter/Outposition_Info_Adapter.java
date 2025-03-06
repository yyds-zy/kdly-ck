package com.example.kuodan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuodan.R;
import com.example.kuodan.model.Outposition_Reslut;
import com.example.kuodan.model.Wlqd_Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.adapter
 * @date on 2020/4/14 9:54
 * @wechat 18813158027
 */
public class Outposition_Info_Adapter extends RecyclerView.Adapter<Outposition_Detail_ViewHolder> {

    private Context context;
    private List<Outposition_Reslut.positionDetailBean> detailsList;

    public Outposition_Info_Adapter(Context context) {
        this.context = context;
    }

    public List<Outposition_Reslut.positionDetailBean> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Outposition_Reslut.positionDetailBean> detailsList) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public Outposition_Detail_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.outposition_detail, viewGroup, false);
        return new Outposition_Detail_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Outposition_Detail_ViewHolder holder, int i) {
        Outposition_Reslut.positionDetailBean dlist = detailsList.get(i);

        holder.positionCodetv3.setText(dlist.getPositionCode());
        holder.proDatetv3.setText(dlist.getProDate());
        holder.baseQtyntv3.setText(dlist.getBaseQtyn()+"");
        holder.billDatetv3.setText(dlist.getBillDate());
        holder.batchtv3.setText(dlist.getBatch());
        holder.cinvStdtv3.setText(dlist.getCinvStd());


    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }



}

/**
 * 第三个页面显示 出库货位和数量 的类
 */
class Outposition_Detail_ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.position_code_tv3)
    TextView positionCodetv3;
    @BindView(R.id.cinv_std_tv3)
    TextView cinvStdtv3;
    @BindView(R.id.batch_tv3)
    TextView batchtv3;
    @BindView(R.id.base_qtyn_tv3)
    TextView baseQtyntv3;
    @BindView(R.id.pro_date_tv3)
    TextView proDatetv3;
    @BindView(R.id.bill_date_tv3)
    TextView billDatetv3;


    public Outposition_Detail_ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

