package com.example.kuodan.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuodan.NetUtils;
import com.example.kuodan.R;
import com.example.kuodan.adapter.Outposition_Info_Adapter;
import com.example.kuodan.base.BaseActivity;
import com.example.kuodan.model.Outposition_Reslut;
import com.example.kuodan.model.Wlqd_Result;
import com.example.kuodan.model.Yzout_Reslut;
import com.example.kuodan.net_connection.Server_Response;
import com.example.kuodan.tool.MyUtils;
import com.example.kuodan.tool.TLog;

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
public class OutPosition_Info extends BaseActivity implements Server_Response.OnGetSeverLister {

    @BindView(R.id.finish_back)//返回
    ImageView finishBack;



    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    /** 扫码框 */
    EditText foucasResult;

    private TextView tv223cinvStd;

    private TextView tv3MainId;
    private TextView tv3batch;
    private String wlqdId;
    private TextView tv3cinvStd;
    private TextView tv3CustStd;
    private TextView tv3ArrangeQtyn;
    private TextView tv3youtQtyn;
    private TextView tv23yanzh;
    private String id;
    private String cinvCode;
    private String cinvStd;
    //private String timeTv;
    private Outposition_Info_Adapter adapter;
    private List<Outposition_Reslut.positionDetailBean> detailsList;

    private TextToSpeech textToSpeech;
    /**MediaPlayer类访问内置的媒体播放器的服务,如播放音频,视频等为了使用MediaPlayer,我们要调用这个类的静态create() 方法。此方法返回MediaPlayer类的一个实例。 */
    private static MediaPlayer mediaPlayer;

    private Context context;

    private boolean canScan = true;

    public String userName;

    @Override
    public int loadWindowLayout() {
        return R.layout.outposition_info;
    }

    /** initView();是一个自己定义的方法，他的作用是初始化一些控件 */
    @Override
    public void initViews() throws Exception {
        context = this;
        foucasResult = findViewById(R.id.foucas_result);//foucas_result
        foucasResult.addTextChangedListener(watcher);//监听edittext的输入文本的变化
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        userName = getIntent().getStringExtra("userName");
        cinvCode = getIntent().getStringExtra("cinvCode");
        cinvStd = getIntent().getStringExtra("cinvStd");
        TLog.e("-----id-----", id);
//        TLog.e("-----cinvCode-----", cinvCode);
//        TLog.e("--getIntent()---loginName-----", "----"+userName+"+++");
//        TLog.e("--getIntent()---userName-----", userName);
        tv223cinvStd=findViewById(R.id.cinvStd_tv223);
        tv223cinvStd.setText(cinvStd);

        tv3MainId=findViewById(R.id.main_id_tv23);
        tv3batch=findViewById(R.id.batch_tv23);
        tv3cinvStd=findViewById(R.id.cinvStd_tv23);
        tv3CustStd=findViewById(R.id.custStd_tv23);
        tv3ArrangeQtyn=findViewById(R.id.arrangeQtyn_tv23);
        tv3youtQtyn=findViewById(R.id.youtQtyn_tv23);

        tv23yanzh=findViewById(R.id.yanzh_tv23);

        initTTs();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Outposition_Info_Adapter(this);//adapter:详细信息

        requestDetils();//初始化页面，获取出库货位明细



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

            String qrCode =s.toString().trim();

            foucasResult.removeTextChangedListener(watcher);
            foucasResult.setText("");
            foucasResult.addTextChangedListener(watcher);
            //tv3cinvStd
            String tv23yanzhstr = tv23yanzh.getText().toString();

            if(tv23yanzhstr.equals("需验证")){
                String[] atrarr = qrCode.split("~");
                if(atrarr.length==9) {
                    String qrcinvStd = atrarr[0];
                    String wlcinvStd = tv3cinvStd.getText().toString();
                    int lengqr = qrcinvStd.length();
                    int leng = wlcinvStd.length();
                    boolean qrtf = true;
                    for(;;){
                        char charqr =qrcinvStd.charAt(--lengqr);
                        char chars = wlcinvStd.charAt(--leng);
                        if((charqr+"").equals(chars+"")){
                        }else{
                            qrtf = false;
                            break;
                        }
                        if(leng == 0){
                            break;
                        }
                    }

                    if (qrtf) {
                        tv23yanzh.setText("已验证");
                        tv23yanzh.setTextColor(context.getResources().getColor(R.color.blue));
                    } else {
                        MyUtils.showDialogVersion2(context, "验证失败！");
                    }
                }else{
                    MyUtils.showDialogVersion2(context, "二维码不符合规则！");
                }


            }else if(tv23yanzhstr.equals("已验证")){
                String hw = detailsList.get(0).getPositionCode();
                if(qrCode.equals(hw)){
                    outpositionSave(qrCode);//货位码
                }else{
                    MyUtils.showDialogVersion2(context, "请按照先进先出排序的货位顺序扫码出库！");
                }
            }
        }

    };

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


    /** 获取指令-物料清单-货位信息  */
    private void requestDetils() {
        showProgress();

//        TLog.e("==afterTextChangloginName223333==", userName);
//        TLog.e("==afterTextChangloginName223333==", id);
        NetUtils.getAselarrWlqdHwxx(this, userName, id, cinvCode);//getResponse
    }
    /** 扫描货位码出库  */
    private void outpositionSave(String posotionCode) {
        showProgress();
//        TLog.e("==afterTextChangloginName223333==", userName);
//        TLog.e("==afterTextChangloginName223333==", wlqdId);
//        TLog.e("==afterTextChangloginName223333==", cinvCode);
//        TLog.e("==afterTextChangloginName223333==", posotionCode);
//        String cinvStd = tv223cinvStd.getText().toString();
        System.out.println("---"+cinvStd);

        NetUtils.outpositionSave(this, userName, wlqdId, cinvCode, posotionCode, cinvStd);
    }




    @Override
    public void onSuccess(Object response) {//访问成功后执行的实现
        hideProgress();
        if (response instanceof Outposition_Reslut) {
            Outposition_Reslut result = (Outposition_Reslut) response;

            tv3MainId.setText( result.getMainId());
            tv3batch.setText( result.getBatch());
            tv3CustStd.setText( result.getCustStd());

            wlqdId = result.getWlqdId();
            tv3cinvStd.setText( result.getCinvStd());
            tv3ArrangeQtyn.setText( result.getArrangeQtyn());
            tv3youtQtyn.setText(result.getYoutQtyn());

//            System.out.println("----20230615-result.getOutyn()---"+result.getOutyn());

            if("nolist".equals(result.getOutyn())){//nolist
                tv23yanzh.setText("出库-物料需求异常");
            }
                detailsList = result.getList();
            if("n".equals(result.getOutyn())){//按指令出库完成，不需要再加载货位信息
                tv23yanzh.setText("已出库");
                tv3cinvStd.setTextColor(context.getResources().getColor(R.color.blue));
                tv3ArrangeQtyn.setTextColor(context.getResources().getColor(R.color.blue));
                tv3youtQtyn.setTextColor(context.getResources().getColor(R.color.blue));
            }else{
                if (detailsList.size() == 0) {
                    tv23yanzh.setText("未找到该物料的货位。");
                } else {
                    tv23yanzh.setText("需验证");
                }
            }

                TLog.e("==获取列表detailsList=="+detailsList);
                adapter.setDetailsList(detailsList);
                recyclerview.setAdapter(adapter);



        }else if (response instanceof Yzout_Reslut) {
            Yzout_Reslut result = (Yzout_Reslut) response;
//            System.out.println("----20230614---Yzout_Reslut-getTf---"+result.getTf());
//            System.out.println("----20230614---Yzout_Reslut-getMsg---"+result.getMsg());
//            System.out.println("----20230614---t == result.getTf()---"+("t" == result.getTf()));
            if("t".equals(result.getTf())){
                playVoice(this);//播放成功语音
                requestDetils();//初始化页面，获取出库货位明细
            }else{
                MyUtils.showDialogVersion2(context, "提示："+result.getMsg());
            }
        }else{
            System.out.println("==response instanceof Outposition_Reslut false==");
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

    @Override
    public void onFaire(String error) {
        hideProgress();
        tostShow(error);
        canScan = true;
    }


    @OnClick({R.id.finish_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_back://返回
                finish();

                break;


        }
    }

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


}
