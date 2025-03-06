package com.example.kuodan.net_connection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 孙贝贝
 * @packagename com.example.a93018.videodemo
 * @date on 2019/1/28 10:26
 * @wechat 18813158027
 */
public interface KaiyanHttpService {

    /**登录*/
    @POST("mainfunction/wms/cdlyoutwmsfromproins/login")
    Call<ResponseBody> login(@Query("userName") String userName, @Query("password") String passorwd);

    /**出库  任务 /mainfunction/wms/cdlyoutwmsfromproins/ckrwlblist   */
    @POST("mainfunction/wms/cdlyoutwmsfromproins/ckrwlblist")
    Call<ResponseBody> seloutinfo(@Query("cdate")String cDate, @Query("loginName")String loginName , @Query("ing") int o);

    /** 出库的指令-物料清单  任务需出库明细 */
    @POST("mainfunction/wms/cdlyoutwmsfromproins/ckrwlbjdetail")
    Call<ResponseBody> selarr_wlqd( @Query("loginName")String loginName, @Query("ckrwbj") String ckrwbj, @Query("status") String status);

    /** 出库的指令-物料清单-货位信息  返回最新的货位*/
    @POST("mainfunction/wms/cdlyoutwmsfromproins/selckhuowei")
    Call<ResponseBody> selarr_wlqd_hwxx( @Query("loginName")String loginName, @Query("ckrwbj") String id, @Query("cinvCode") String cinvCode );

    @POST("mainfunction/wms/cdlyoutwmsfromproins/selckhuoweirw")
    Call<ResponseBody> selarr_wlqd_hwxxrw( @Query("loginName")String loginName, @Query("ckrwbj") String id, @Query("cinvCode") String cinvCode );

    /** 出库-扫码验证出库 */
    @POST("mainfunction/wms/cdlyoutwmsfromproins/outpositionsave")
    Call<ResponseBody> outposition_save(@Query("userName")String userName,@Query("ckrwbj") String ckrwbj ,@Query("cinvstd") String cinvstd,@Query("huowei") String posotionCode ,@Query("qrckrwbj") String qrckrwbj);



}
