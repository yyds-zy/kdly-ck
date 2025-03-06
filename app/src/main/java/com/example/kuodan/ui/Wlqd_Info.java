package com.example.kuodan.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuodan.NetUtils;
import com.example.kuodan.R;
import com.example.kuodan.adapter.Outposition_Info_Adapter;
import com.example.kuodan.adapter.Wlqd_Info_Adapter;
import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.Outposition_Reslut;
import com.example.kuodan.model.Outposition_Reslutrw;
import com.example.kuodan.model.Wlqd_Result;
import com.example.kuodan.model.Yzout_Reslut;
import com.example.kuodan.net_connection.Server_Response;
import com.example.kuodan.tool.MyUtils;
import com.example.kuodan.tool.TLog;
import com.example.kuodan.tool.onFinishListener;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.ui
 * @date on 2020/4/13 18:56
 * @wechat 18813158027
 * 订单号详情
 */
public class Wlqd_Info extends BaseActivity implements Server_Response.OnGetSeverLister {
    @BindView(R.id.finish_back)//返回
    ImageView finishBack;



    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    /** 扫码框 */
    EditText foucasResult;



    private TextView tvckhuowei;

    private TextView tvckrwhuowei;
    private TextView tvckrwhuoweiyzh;
    private TextView ckrwbjyzh;

    @BindView(R.id.search_btn)
    Button searchBtn;

    @BindView(R.id.search_btnwc)
    Button searchBtnwc;

    @BindView(R.id.tv_qrckrwstd)
    TextView qrckrwstd;
    @BindView(R.id.tv_qrckrwqty)
    TextView qrckrwqty;

    public String qrcodeckrw;

    public String ckrwbj;
    public String huowei;
    public String rwhuowei;
    private String mTuHao = "";


    private TextView tv2ckrwbj;
    private TextView tv2ckrwbjyzh;

    private Wlqd_Info_Adapter adapter;
    private List<Wlqd_Result.DetailBean> detailsList;

    private TextToSpeech textToSpeech;
    /**MediaPlayer类访问内置的媒体播放器的服务,如播放音频,视频等为了使用MediaPlayer,我们要调用这个类的静态create() 方法。此方法返回MediaPlayer类的一个实例。 */
    private static MediaPlayer mediaPlayer;

    private Context context;

    private boolean canScan = true;

    public String userName;

    @Override
    public int loadWindowLayout() {
        return R.layout.wlqd_info;
    }

    /** initView();是一个自己定义的方法，他的作用是初始化一些控件 */
    @Override
    public void initViews() throws Exception {
        context = this;
        foucasResult = findViewById(R.id.foucas_result);//foucas_result
        foucasResult.addTextChangedListener(watcher);//监听edittext的输入文本的变化

        ButterKnife.bind(this);

        userName = getIntent().getStringExtra("userName");

        ckrwbj = getIntent().getStringExtra("ckrwbj");

        tvckhuowei=findViewById(R.id.tv_ckhuowei);

        tvckrwhuowei=findViewById(R.id.tv_ckrwhuowei);
        tvckrwhuoweiyzh=findViewById(R.id.tv_ckrwhuoweiyzh);

        tv2ckrwbj=findViewById(R.id.tv2_ckrwbj);
        tv2ckrwbj.setText(ckrwbj);
        tv2ckrwbjyzh=findViewById(R.id.tv2_ckrwbjyzh);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Wlqd_Info_Adapter(this);//adapter:详细信息

        requestDetils("");//初始化页面，获取出库货位明细

        adapter.setOnFinishListener(new onFinishListener() {
            @Override
            public void onFinish(String cinvcode) {
                huoweixx(cinvcode) ;
            }
        });
        initTTs();
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null!=mediaPlayer){
            mediaPlayer.release();
        }
    }


    /** 获取指令-物料清单  */
    private void huoweixx(String cinvcode) {
        Log.d("afei", "huoweixx----------------------------");
        showProgress();
        NetUtils.getAselarrWlqdHwxx(this, userName, ckrwbj, cinvcode );//getResponse
    }
    private void huoweixxrw(String cinvcode) {
        Log.d("afei", "huoweixxrw----------------------------");
        showProgress();
        NetUtils.getAselarrWlqdHwxxrw(this, userName, ckrwbj, cinvcode );//getResponse
    }

    private void rwchuku(String cinvstd) {
        Log.d("afei", "rwchuku----------------------------");
        TLog.e("==qrcodeckrw---8--==", qrcodeckrw);
        showProgress();
        String a0 = qrcodeckrw.split("~")[0];
        String a1 = qrcodeckrw.split("~")[1];
        String a2 = qrcodeckrw.split("~")[2];
        String a3 = qrcodeckrw.split("~")[3];
        String a4 = qrcodeckrw.split("~")[4];
        String a5 = qrcodeckrw.split("~")[5];
        String a6 = qrcodeckrw.split("~")[6];
        String a7 = qrcodeckrw.split("~")[7];

        TLog.e("==qrckrwbj-----huoweixxrw-==", qrcodeckrw);
        TLog.e("==qrckrwbj-----a0+a1+a2+a3+\"a\"+a4+a5+a6+a7-==", a0+"~"+a1+"~"+a2+"~"+a3+"a~"+a4+"~"+a5+"~"+a6+"~"+a7);
        NetUtils.outpositionSave(this, userName, ckrwbj, cinvstd , rwhuowei , a0+"~"+a1+"~"+a2+"~"+a3+"a~"+a4+"~"+a5+"~"+a6+"~"+a7);//getResponse
    }
    /** 获取指令-物料清单  */
    private void requestDetils(String status) {
        Log.d("afei", "requestDetils----------------------------");
        showProgress();
        NetUtils.getArrWlqd(this, userName, ckrwbj, status);//getResponse
    }

    @Override
    public void onSuccess(Object response) {//访问成功后执行的实现
        hideProgress();
        if (response instanceof Wlqd_Result) {
            Wlqd_Result result = (Wlqd_Result) response;

            detailsList = result.getList();
//            TLog.e("==获取列表detailsList=="+detailsList);
            adapter.setDetailsList(detailsList);
            recyclerview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else
        if (response instanceof Outposition_Reslut) {
//            TLog.e("==cinvcode123321123123==");
            Outposition_Reslut result = (Outposition_Reslut) response;
            String tf = result.getTf();
            if("t".equals(tf)){
                huowei = result.getHuowei();
                tvckhuowei.setText(huowei);
            }else{
                MyUtils.showDialogVersion2(context, "获取货位信息失败。"+result.getMsg());
            }
        }
        else
        if (response instanceof Outposition_Reslutrw) {
//            TLog.e("==cinvcode123321123123==");
            Outposition_Reslutrw result = (Outposition_Reslutrw) response;

            String tf = result.getTf();
            if ("t".equals(tf)) {
                rwhuowei = result.getHuowei();
                tvckrwhuowei.setText(rwhuowei);
                tvckrwhuoweiyzh.setText("待验证");
                tvckrwhuoweiyzh.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                MyUtils.showDialogVersion2(context, "获取货位信息失败。" + result.getMsg());
            }
        }
        else
        if (response instanceof Yzout_Reslut) {
            Yzout_Reslut result = (Yzout_Reslut) response;

            String tf = result.getTf();
            if("t".equals(tf)){
                playVoice(this);//播放成功语音
                tv2ckrwbjyzh.setText("待验证");
                tv2ckrwbjyzh.setTextColor(context.getResources().getColor(R.color.red));

                tvckrwhuowei.setText("");
                tvckrwhuoweiyzh.setText("");
                qrckrwstd.setText("");
                qrckrwqty.setText("");

                MyUtils.showDialogVersion2(context, "图号："+result.getCinvstd()
                                                +"<br>"+result.getXqqty()
                                                +"<br>"+result.getXqsyqty()
                                                +"<br>"+result.getHwqty()
                                                +"<br>"+result.getHwsyqty()
                    );
            }else{
                MyUtils.showDialogVersion2(context, "失败。"+result.getMsg());
            }
        }



    }

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);
        canScan = true;
    }


    @OnClick({R.id.finish_back , R.id.search_btn , R.id.search_btnwc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_back://返回
                finish();
                break;
            case R.id.search_btn://查询
                requestDetils("ing");//初始化页面，获取出库货位明细
                break;
            case R.id.search_btnwc://查询
                requestDetils("yiwanch");//初始化页面，获取出库货位明细
                break;
        }
    }


    private TextWatcher watcher = new TextWatcher() { // Android TextWatcher监控EditText中的输入内容
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {//扫描结果

            // TODO 这个就是接收到的二维码内容
            String qrCode = s.toString().trim();

            foucasResult.removeTextChangedListener(watcher);
            foucasResult.setText("");
            foucasResult.addTextChangedListener(watcher);

//            System.out.println("==afterTextChanged扫描结果11112222==" + qrCode + "---");

            if (null != qrCode || !qrCode.equals("")) {
                Log.d("afei", "qrCode：" + qrCode + " qrcode.len = " + qrCode.length() + " ckrwbjyz: " + tv2ckrwbjyzh.getText().toString());
                Log.d("afei","qrCode split len" + qrCode.split("~").length);
                Log.d("afei","ckrwbj:= " + ckrwbj);
                if(qrCode.length() == 10) {
                    String qrhuowei = qrCode;
//                    System.out.println("---qrhuowei:" + qrhuowei +"---tvckrwhuowei:"+tvckrwhuowei.getText().toString()+"---");
                    // 二维码是否通过验证，没有通过验证，提示先扫描出库二维码标签
                    String ckrwbjyz = tv2ckrwbjyzh.getText().toString();
//                    System.out.println("--qrhuowei--"+qrhuowei+"--ckrwbjyz--"+ckrwbjyz );

                    if("已验证".equals(ckrwbjyz)){

                    } else {
                        tv2ckrwbjyzh.setText("待验证");
                        tv2ckrwbjyzh.setTextColor(context.getResources().getColor(R.color.red));
                        MyUtils.showDialogVersion2(context, "出库任务二维码还未通过验证。");
                        return;
                    }
                    if(qrhuowei.equals(tvckrwhuowei.getText().toString())){
                        tvckrwhuoweiyzh.setText("已验证");
                        if ("已验证".equals(ckrwbjyz) && !"".equals(mTuHao)) {
                            Log.d("afei", "图号：" + mTuHao);
                            rwchuku(mTuHao);
                        }
                    }else{
                        tvckrwhuoweiyzh.setText("待验证");
                        tvckrwhuoweiyzh.setTextColor(context.getResources().getColor(R.color.red));
                        MyUtils.showDialogVersion2(context, "扫描的货位，不是该出库任务的。");
                    }
                } else if(qrCode.split("~").length == 8) {
                    Log.d("afei", "split.len = 8");
                    String qrckrwbjbmly = qrCode.split("~")[3];
                    Log.d("afei", "qrckrwbjbmly = " + qrckrwbjbmly);
                    String qrckrwbj = qrckrwbjbmly.split("-")[0];
                    Log.d("afei", "qrckrwbj = " + qrckrwbj);
                    String qrckrwbjstd = qrCode.split("~")[5];
                    Log.d("afei", "qrckrwbjstd = " + qrckrwbjstd);
                    String qrckrwbjqty = qrCode.split("~")[6];
                    Log.d("afei", "qrckrwbjqty = " + qrckrwbjqty);

//                    System.out.println("---qrckrwbj:" + qrckrwbj + "---ckrwbj:"+ckrwbj);
                    if(ckrwbj.equals(qrckrwbj)) {
                        qrcodeckrw = qrCode;
                        System.out.println("--这个二维码和该页出库任务能匹配--qrckrwbj:" + qrckrwbj + "---ckrwbj:"+ckrwbj);
                        // 通过验证
                        tv2ckrwbjyzh.setText("已验证");
//                        tv2ckrwbjyzh.setTextColor();
                        // 更新货位 验证货位
                        huoweixxrw(qrCode.split("~")[4]) ;
                        mTuHao = qrckrwbjstd;
                        qrckrwstd.setText("图号："+qrckrwbjstd);
                        qrckrwqty.setText("，数量："+qrckrwbjqty);
                    } else {
                        tv2ckrwbjyzh.setText("待验证");
                        tv2ckrwbjyzh.setTextColor(context.getResources().getColor(R.color.red));
                        tvckrwhuowei.setText("");
                        tvckrwhuoweiyzh.setText("");
                        qrckrwstd.setText("");
                        qrckrwqty.setText("");

                        MyUtils.showDialogVersion2(context, "扫描的二维码，不是该出库任务的。");
                    }
                } else if(qrCode.split("~").length == 9) { // ST002-01~20~件~A0417~0318~8342~12~34~HB0120230418000334
                    Log.d("afei", "split.len = 9");
                    String ckrwbjyz = tv2ckrwbjyzh.getText().toString();
                    String hwyz = tvckrwhuoweiyzh.getText().toString();
                    if("已验证".equals(ckrwbjyz)) {
//                        System.out.println("--出库任务二维码标签未扫码验证--已验证--- ");
                    } else {
//                        System.out.println("--出库任务二维码标签未扫码验证--");
                        tv2ckrwbjyzh.setText("待验证");
                        tv2ckrwbjyzh.setTextColor(context.getResources().getColor(R.color.blue));
                        MyUtils.showDialogVersion2(context, "出库任务二维码还未通过验证。");
                        return;
                    }

                    if("已验证".equals(hwyz)){
//                        System.out.println("--货位未扫码验证--已验证--- ");
                    }else{
//                        System.out.println("--货位未扫码验证--");
                        tvckrwhuoweiyzh.setText("待验证");
                        tvckrwhuoweiyzh.setTextColor(context.getResources().getColor(R.color.blue));
                        MyUtils.showDialogVersion2(context, "扫描的货位还未通过验证。");
                        return;
                    }
//                    System.out.println("--hwyz--"+hwyz+"--ckrwbjyz--"+ckrwbjyz );
                    // 二维码是否通过验证，没有通过验证，提示先扫描出库二维码标签
                    // 该货位是否为空
                    if("已验证".equals(ckrwbjyz) && "已验证".equals(hwyz)){
                        String qrstd = qrCode.split("~")[0];
                        rwchuku(qrstd);
                    }

                }
            }
        }
    };
    public static void playVoice(Context context){
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.sussful);
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    mediaPlayer.stop();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTTs(){//示例话语音对象
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    textToSpeech.setPitch(1.0f);
                    textToSpeech.setSpeechRate(1.0f);
                    int result1=textToSpeech.setLanguage(Locale.US);
                    int rersult2=textToSpeech.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    boolean a = (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED);
                    boolean b = (rersult2 == TextToSpeech.LANG_MISSING_DATA || rersult2 == TextToSpeech.LANG_NOT_SUPPORTED);

                }
            }
        });

    }
}
