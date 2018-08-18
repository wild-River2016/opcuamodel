package com.mj.k4.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/2/24
 * Annotation: 订单生产数量，合格率，不合格数记录
 */
@Entity
@Table(name = "fata_order_num")
public class FataOrderNum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fonid;

    @Column(name = "fon_name")
    private String fonName;//订单编号

    @Column(name = "fon_go_num")
    private Integer fonGoNum = 0;//订单上线数量

    @Column(name = "fon_end_num")
    private Integer fonEndNum = 0;//订单下线数量

    @Column(name = "fon_num_ok")
    private String fonNumOk;//合格率

    @Column(name = "fon_nok_num")
    private Integer fonNokNum = 0;//不合格数

    @Column(name = "fon_year_num")
    private Integer fonYearNum = 0;//年产量

    @Column(name = "fon_month_num")
    private Integer fonMonthNum = 0;//月产量

    @Column(name = "fon_day_num")
    private Integer fonDayNum = 0;//日产量

    @Column(name = "fon_time")
    private Timestamp fonTime;//开始日期

    @Column(name = "fon_end_time")
    private Timestamp fonEndTime;//结束日期

    @Column(name = "delayed_indentify")
    private Integer delayedIndentify;

    @Size(max = 50)
    @Column(name = "create_user_id")
    private String createUserId;//创建人id
    @Column(name = "create_time")
    private Timestamp createTime;//创建时间
    @Size(max = 50)
    @Column(name = "update_user_id")
    private String updateUserId;//修改人id
    @Column(name = "update_time")
    private Timestamp updateTime;//修改时间

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private MjFataFata mj_fata_fata;

    public Integer getFonid() {
        return fonid;
    }

    public void setFonid(Integer fonid) {
        this.fonid = fonid;
    }

    public String getFonName() {
        return fonName;
    }

    public void setFonName(String fonName) {
        this.fonName = fonName;
    }

    public Integer getFonGoNum() {
        return fonGoNum;
    }

    public void setFonGoNum(Integer fonGoNum) {
        this.fonGoNum = fonGoNum;
    }

    public Integer getFonEndNum() {
        return fonEndNum;
    }

    public void setFonEndNum(Integer fonEndNum) {
        this.fonEndNum = fonEndNum;
    }

    public String getFonNumOk() {
        return fonNumOk;
    }

    public void setFonNumOk(String fonNumOk) {
        this.fonNumOk = fonNumOk;
    }

    public Integer getFonNokNum() {
        return fonNokNum;
    }

    public void setFonNokNum(Integer fonNokNum) {
        this.fonNokNum = fonNokNum;
    }

    public Integer getFonYearNum() {
        return fonYearNum;
    }

    public void setFonYearNum(Integer fonYearNum) {
        this.fonYearNum = fonYearNum;
    }

    public Integer getFonMonthNum() {
        return fonMonthNum;
    }

    public void setFonMonthNum(Integer fonMonthNum) {
        this.fonMonthNum = fonMonthNum;
    }

    public Integer getFonDayNum() {
        return fonDayNum;
    }

    public void setFonDayNum(Integer fonDayNum) {
        this.fonDayNum = fonDayNum;
    }

    public Timestamp getFonTime() {
        return fonTime;
    }

    public void setFonTime(Timestamp fonTime) {
        this.fonTime = fonTime;
    }

    public Timestamp getFonEndTime() {
        return fonEndTime;
    }

    public void setFonEndTime(Timestamp fonEndTime) {
        this.fonEndTime = fonEndTime;
    }

    public Integer getDelayedIndentify() {
        return delayedIndentify;
    }

    public void setDelayedIndentify(Integer delayedIndentify) {
        this.delayedIndentify = delayedIndentify;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public MjFataFata getMj_fata_fata() {
        return mj_fata_fata;
    }

    public void setMj_fata_fata(MjFataFata mj_fata_fata) {
        this.mj_fata_fata = mj_fata_fata;
    }
}
