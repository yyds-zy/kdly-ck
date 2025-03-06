package com.example.kuodan.model;

import java.util.List;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.model
 * @date on 2020/4/15 14:08
 * @wechat 18813158027
 */
public class Invoice_Reslult {



    private String createDate;
    private String loginCode;
    private List<ListBean> list;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    /**
     * dateTime : 2020-04-15
     * mainId : 98201807014
     * state : 11
     * custName : 舍弗勒
     */
    public static class ListBean {

//  "organ":"8","zzsfwc":0,"hebing":"y"
//  //,"insids":"20231205c458687f3d44881123p1,20231205c4586858895af81123p0"
//  //,"ckrwbj":"240618151657系统管理员23546099","wgsfwc":0}
        private String id;
        private String mainId;//令号主编号

        private String batch;//批次
        private String cinvCode;
        private String arrangeQtyn;
        private String cinvName;
        private String cinvStd;
        private String cinvaddCode;
        private String unitName;
        private String custStd;

        private String ckrwbj;

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

        public String getCinvCode() {
            return cinvCode;
        }

        public void setCinvCode(String cinvCode) {
            this.cinvCode = cinvCode;
        }

        public String getArrangeQtyn() {
            return arrangeQtyn;
        }

        public void setArrangeQtyn(String arrangeQtyn) {
            this.arrangeQtyn = arrangeQtyn;
        }

        public String getCinvName() {
            return cinvName;
        }

        public void setCinvName(String cinvName) {
            this.cinvName = cinvName;
        }

        public String getCinvStd() {
            return cinvStd;
        }

        public void setCinvStd(String cinvStd) {
            this.cinvStd = cinvStd;
        }

        public String getCinvaddCode() {
            return cinvaddCode;
        }

        public void setCinvaddCode(String cinvaddCode) {
            this.cinvaddCode = cinvaddCode;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getCustStd() {
            return custStd;
        }

        public void setCustStd(String custStd) {
            this.custStd = custStd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCkrwbj() {
            return ckrwbj;
        }

        public void setCkrwbj(String ckrwbj) {
            this.ckrwbj = ckrwbj;
        }
    }
}
