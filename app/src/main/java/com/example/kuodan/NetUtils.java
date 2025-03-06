package com.example.kuodan;

import com.example.kuodan.model.Login_Result;
import com.example.kuodan.model.Outposition_Reslutrw;
import com.example.kuodan.model.Wlqd_Result;
import com.example.kuodan.model.Invoice_Reslult;
import com.example.kuodan.model.Outposition_Reslut;
import com.example.kuodan.model.Yzout_Reslut;
import com.example.kuodan.net_connection.ServerUtil;
import com.example.kuodan.net_connection.Server_Response;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan
 * @date on 2020/4/14 10:33
 * @wechat 18813158027
 */
public class NetUtils {
    public static void Login(Server_Response.OnGetSeverLister lister,String userName,String password){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().login(userName,password);
        Server_Response.getResponse(responseBodyCall,new Login_Result(),lister);

    }

    /** 获取出库指令 */
    public static void getOutinfoList(Server_Response.OnGetSeverLister lister,String cDate,String loginName , int o){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().seloutinfo(cDate,loginName , o);
        Server_Response.getResponse(responseBodyCall,new Invoice_Reslult(),lister);
    }

    /** 出库的指令-物料清单 */
    public static void getArrWlqd(Server_Response.OnGetSeverLister lister,String loginName,String ckrwbj,String status){

        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().selarr_wlqd(loginName,ckrwbj,status);
        Server_Response.getResponse(responseBodyCall,new Wlqd_Result(),lister);
    }

    /** 出库的指令-物料清单-货位信息 */
    public static void getAselarrWlqdHwxx(Server_Response.OnGetSeverLister lister,String loginName,String ckrwbj, String cinvCode){

        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().selarr_wlqd_hwxx(loginName,ckrwbj,cinvCode);
        Server_Response.getResponse(responseBodyCall,new Outposition_Reslut(),lister);
    }
    /** 出库的指令-物料清单-货位信息 */
    public static void getAselarrWlqdHwxxrw(Server_Response.OnGetSeverLister lister,String loginName,String ckrwbj, String cinvCode){

        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().selarr_wlqd_hwxxrw(loginName,ckrwbj,cinvCode);
        Server_Response.getResponse(responseBodyCall,new Outposition_Reslutrw(),lister);
    }

    /** 扫描货位码验证 */
    public static void outpositionSave(Server_Response.OnGetSeverLister lister,String userName,String ckrwbj,String cinvstd,String huowei , String qrckrwbj){
        Call<ResponseBody> responseBodyCall = ServerUtil.getInstance().getServer().outposition_save(userName,ckrwbj,cinvstd,huowei,qrckrwbj);
        Server_Response.getResponse(responseBodyCall,new Yzout_Reslut(),lister);

    }


}
