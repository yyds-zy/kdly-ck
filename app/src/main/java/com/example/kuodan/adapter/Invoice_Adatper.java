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
import com.example.kuodan.model.Invoice_Reslult;
import com.example.kuodan.ui.Invoice_Order;
import com.example.kuodan.ui.Wlqd_Info;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.adapter
 * @date on 2020/4/13 17:56
 * @wechat 18813158027
 */
public class Invoice_Adatper extends RecyclerView.Adapter<Invoice_ViewHolder> {

    private Context context;
    private List<Invoice_Reslult.ListBean> list;

    public Invoice_Adatper(Context context) {
        this.context = context;
    }
    public void setList(List<Invoice_Reslult.ListBean> list){
        this.list=list;

    }

    @NonNull
    @Override
    public Invoice_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_item, viewGroup, false);
        return new Invoice_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Invoice_ViewHolder holder, int i) {

        System.out.println("--"+context);
        String loginName = ((Invoice_Order) context).userName;
        System.out.println("------loginName-111----"+loginName);
        Invoice_Reslult.ListBean listBean = list.get(i);

        holder.ckrwbj.setText(listBean.getCkrwbj());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ckrwbj", listBean.getCkrwbj());
                intent.putExtra("userName", loginName);
                intent.setClass(context, Wlqd_Info.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

// positionCode     baseQtyn            chukudanhao     cinvCode     name     cinvName            cinvStd
class Invoice_ViewHolder extends RecyclerView.ViewHolder {

//    @BindView(R.id.tv_main_id)
//    TextView mainId;
//
//    @BindView(R.id.tv_batch)
//    TextView batch;
//
//    @BindView(R.id.tv_cinv_code)
//    TextView cinvCode;
//
//    @BindView(R.id.tv_arrange_qtyn)
//    TextView arrangeQtyn;
//
//    @BindView(R.id.tv_cinv_name)
//    TextView cinvName;
//
//    @BindView(R.id.tv_cinv_std)
//    TextView cinvStd;
//
//    @BindView(R.id.tv_cinvadd_code)
//    TextView cinvaddCode;
//
//    @BindView(R.id.tv_unit_name)
//    TextView unitName;
//
//    @BindView(R.id.tv_cust_std)
//    TextView custStd;

    @BindView(R.id.tv_ckrwbj)
    TextView ckrwbj;


    public Invoice_ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
