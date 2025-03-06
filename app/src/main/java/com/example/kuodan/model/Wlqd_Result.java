package com.example.kuodan.model;

import java.util.List;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.model
 * @date on 2020/4/15 15:10
 * @wechat 18813158027
 */
public class Wlqd_Result {

    /**
     * detail : [{"cinvStd":"075S075       ","ysendQty":120,"flag1":11,"cinvcode":"20347","sendQty":0},{"cinvStd":"073120","ysendQty":130,"flag1":11,"cinvcode":"20387","sendQty":0}]
     * main : {"dateTime":"2020-04-15","mainId":"98201807014","state":11,"custName":"舍弗勒"}
     */


    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    private List<DetailBean> list;


    public List<DetailBean> getList() {
        return list;
    }

    public void setList(List<DetailBean> list) {
        this.list = list;
    }

    public static class DetailBean {

        private String id;
        private String mainId;//令号主编号

        private String batch;//批次
        private String cinvCode;
        private String arrangeQtyn;
        private String youtQtyn;
        private String cinvName;
        private String cinvStd;
        private String cinvaddCode;
        private String unitName;
        private String custStd;

        private String xcl;

        private String ids;
        private String insid;
        private String ckrwbj;
        private String hzqty;
        private String outqty;
        private String sfwc;
        private String cunit;
        private String zxhj;
        private String vendStd;
        /*
        * group_concat(a.id) as "ids", group_concat(a.insid) as "insid", a.id, a.ckrwbj
        * , a.cinv_code as "cinvCode", sum(a.hzqty) as "hzqty", a.organ , a.sfwc
		, c.cinv_name as "cinvName" , c.cinv_std as "cinvStd" , c.cinvadd_code as "cinvaddCode" , c.c_com_unit_name as "cunit", c.zxhj , c.del_flag as "cdelFlag"
		, m.the_come as "theCome" , m.vendor , v.cinv_std as "vendStd"
		* */

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

        public String getYoutQtyn() {
            return youtQtyn;
        }

        public void setYoutQtyn(String youtQtyn) {
            this.youtQtyn = youtQtyn;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getInsid() {
            return insid;
        }

        public void setInsid(String insid) {
            this.insid = insid;
        }

        public String getCkrwbj() {
            return ckrwbj;
        }

        public void setCkrwbj(String ckrwbj) {
            this.ckrwbj = ckrwbj;
        }

        public String getHzqty() {
            return hzqty;
        }

        public void setHzqty(String hzqty) {
            this.hzqty = hzqty;
        }

        public String getSfwc() {
            return sfwc;
        }

        public void setSfwc(String sfwc) {
            this.sfwc = sfwc;
        }

        public String getCunit() {
            return cunit;
        }

        public void setCunit(String cunit) {
            this.cunit = cunit;
        }

        public String getZxhj() {
            return zxhj;
        }

        public void setZxhj(String zxhj) {
            this.zxhj = zxhj;
        }

        public String getVendStd() {
            return vendStd;
        }

        public void setVendStd(String vendStd) {
            this.vendStd = vendStd;
        }

        public String getOutqty() {
            return outqty;
        }

        public void setOutqty(String outqty) {
            this.outqty = outqty;
        }

        public String getXcl() {
            return xcl;
        }

        public void setXcl(String xcl) {
            this.xcl = xcl;
        }
    }
}
