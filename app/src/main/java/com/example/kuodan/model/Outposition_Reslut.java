package com.example.kuodan.model;

import java.util.List;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.model
 * @date on 2020/4/16 17:36
 * @wechat 18813158027
 */
public class Outposition_Reslut {

    /**
     * msg : dengyu
     */

    private String tf;//t 执行正常播放音乐 f 执行异常
//
    private String msg;//提示信息

    private String huowei;//提示信息

    private String wlqdId;//提示信息
    private String mainId;//提示信息
    private String batch;//提示信息
    private String cinvStd;//提示信息
    private String custStd;//提示信息
    private String arrangeQtyn;//提示信息
    private String youtQtyn;//提示信息
    private String outyn;//提示信息 是否需要继续出库，是否需要罗列出库存信息

    public String getHuowei() {
        return huowei;
    }

    public void setHuowei(String huowei) {
        this.huowei = huowei;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCinvStd() {
        return cinvStd;
    }

    public void setCinvStd(String cinvStd) {
        this.cinvStd = cinvStd;
    }

    public String getCustStd() {
        return custStd;
    }

    public void setCustStd(String custStd) {
        this.custStd = custStd;
    }

    public String getArrangeQtyn() {
        return arrangeQtyn;
    }

    public void setArrangeQtyn(String arrangeQtyn) {
        this.arrangeQtyn = arrangeQtyn;
    }

    public String getWlqdId() {
        return wlqdId;
    }

    public void setWlqdId(String wlqdId) {
        this.wlqdId = wlqdId;
    }

    public String getYoutQtyn() {
        return youtQtyn;
    }

    public void setYoutQtyn(String youtQtyn) {
        this.youtQtyn = youtQtyn;
    }

    public String getOutyn() {
        return outyn;
    }

    public void setOutyn(String outyn) {
        this.outyn = outyn;
    }
//    private String loginName;
//
//    public String getLoginName() {
//        return loginName;
//    }
//
//    public void setLoginName(String loginName) {
//        this.loginName = loginName;
//    }


//    private String mainId;
//    private String batch;
//    private String cinvStd;
    private List<positionDetailBean> list;

//    public String getMainId() {
//        return mainId;
//    }
//
//    public void setMainId(String mainId) {
//        this.mainId = mainId;
//    }
//
//    public String getBatch() {
//        return batch;
//    }
//
//    public void setBatch(String batch) {
//        this.batch = batch;
//    }
//
//    public String getCinvStd() {
//        return cinvStd;
//    }
//
//    public void setCinvStd(String cinvStd) {
//        this.cinvStd = cinvStd;
//    }

    public List<positionDetailBean> getList() {
        return list;
    }

    public void setList(List<positionDetailBean> list) {
        this.list = list;
    }

    public static class positionDetailBean {
        private String id;
        private String positionCode;
        private String cinvCode;
        private String cinvStd;
        private String batch;
        private String baseQtyn;
        private String proDate;
        private String billDate;
//        id, position_code as "positionCode", cinvcode as "cinvCode",
//		 batch, base_qtyn as "baseQtyn", in_date as "proDate", bill_date as "billDate"


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPositionCode() {
            return positionCode;
        }

        public void setPositionCode(String positionCode) {
            this.positionCode = positionCode;
        }

        public String getCinvCode() {
            return cinvCode;
        }

        public void setCinvCode(String cinvCode) {
            this.cinvCode = cinvCode;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getBaseQtyn() {
            return baseQtyn;
        }

        public void setBaseQtyn(String baseQtyn) {
            this.baseQtyn = baseQtyn;
        }

        public String getProDate() {
            return proDate;
        }

        public void setProDate(String proDate) {
            this.proDate = proDate;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getCinvStd() {
            return cinvStd;
        }

        public void setCinvStd(String cinvStd) {
            this.cinvStd = cinvStd;
        }

    }
}
