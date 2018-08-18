package com.mj.k4.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/2/8
 * Annotation:订单详情表
 */
@Entity
@Table(name = "mj_fata_fata")
public class MjFataFata implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mfid;
    @Column(name = "mf_fata")
    private String mfFata;//Fata码
    @Column(name = "mf_front_fata")
    private String mfFrontFata;//前部分码
    @Column(name = "mf_later_fata")
    private String mfLaterFata;//后半部分码
    @Column(name = "mf_fata_type")
    private String mfFatatype;//Fata类型
    @Column(name = "mj_order_file")
    private String mjOrderfile;//订单文件

    @Column(name = "mj_parse_date")
    private Timestamp mjParsedate;//--开始时间
    @Column(name = "mj_end_time")
    private Timestamp mjEndtime;//理论结束时间
    @Column(name = "mj_endok_time")
    private Timestamp mjEndoktime;//实际结束时间

    @Column(name = "mj_num")
    private Integer mjNum;//生产数量
    @Column(name = "mj_date")
    private Integer mjDate;//所用时间

    @Column(name = "mj_level")
    private Integer mjLevel;//优先级
    @Column(name = "mj_sequence_number")
    private Integer mjSequencenumber;//,排序号
    @Column(name = "mj_identityings")
    private Integer mjIdentityings;//操作标识标识

    @Column(name = "mj_remark")
    private String mjRemark;//--备注

    @Size(max = 50)
    @Column(name = "create_user_id", length = 50)
    private String createUserId;//创建人id
    @Column(name = "create_time", length = 50)
    private Timestamp createTime;//创建时间
    @Size(max = 50)
    @Column(name = "update_user_id", length = 50)
    private String updateUserId;//修改人id
    @Column(name = "update_time", length = 50)
    private Timestamp updateTime;//修改时间
    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "mj_fata_fata")
    @JsonIgnore
    private FataOrderNum fataOrderNum;

    public Integer getMfid() {
        return mfid;
    }

    public void setMfid(Integer mfid) {
        this.mfid = mfid;
    }

    public String getMfFata() {
        return mfFata;
    }

    public void setMfFata(String mfFata) {
        this.mfFata = mfFata;
    }

    public String getMfFatatype() {
        return mfFatatype;
    }

    public void setMfFatatype(String mfFatatype) {
        this.mfFatatype = mfFatatype;
    }

    public String getMjOrderfile() {
        return mjOrderfile;
    }

    public void setMjOrderfile(String mjOrderfile) {
        this.mjOrderfile = mjOrderfile;
    }

    public Timestamp getMjParsedate() {
        return mjParsedate;
    }

    public void setMjParsedate(Timestamp mjParsedate) {
        this.mjParsedate = mjParsedate;
    }

    public Timestamp getMjEndtime() {
        return mjEndtime;
    }

    public void setMjEndtime(Timestamp mjEndtime) {
        this.mjEndtime = mjEndtime;
    }

    public Timestamp getMjEndoktime() {
        return mjEndoktime;
    }

    public void setMjEndoktime(Timestamp mjEndoktime) {
        this.mjEndoktime = mjEndoktime;
    }

    public Integer getMjNum() {
        return mjNum;
    }

    public void setMjNum(Integer mjNum) {
        this.mjNum = mjNum;
    }

    public Integer getMjDate() {
        return mjDate;
    }

    public void setMjDate(Integer mjDate) {
        this.mjDate = mjDate;
    }

    public Integer getMjLevel() {
        return mjLevel;
    }

    public void setMjLevel(Integer mjLevel) {
        this.mjLevel = mjLevel;
    }

    public Integer getMjSequencenumber() {
        return mjSequencenumber;
    }

    public void setMjSequencenumber(Integer mjSequencenumber) {
        this.mjSequencenumber = mjSequencenumber;
    }

    public Integer getMjIdentityings() {
        return mjIdentityings;
    }

    public void setMjIdentityings(Integer mjIdentityings) {
        this.mjIdentityings = mjIdentityings;
    }

    public String getMjRemark() {
        return mjRemark;
    }

    public void setMjRemark(String mjRemark) {
        this.mjRemark = mjRemark;
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

    public String getMfFrontFata() {
        return mfFrontFata;
    }

    public void setMfFrontFata(String mfFrontFata) {
        this.mfFrontFata = mfFrontFata;
    }

    public String getMfLaterFata() {
        return mfLaterFata;
    }

    public void setMfLaterFata(String mfLaterFata) {
        this.mfLaterFata = mfLaterFata;
    }

    public FataOrderNum getFataOrderNum() {
        return fataOrderNum;
    }


    public void setFataOrderNum(FataOrderNum fataOrderNum) {
        this.fataOrderNum = fataOrderNum;
    }


//    public MjFataFata(MjViceFata mjViceFata) {
//        this.mfFata = mjViceFata.getMfFata();
//        this.mfFrontFata = mjViceFata.getMfFrontFata();
//        this.mfLaterFata = mjViceFata.getMfLaterFata();
//        this.mfFatatype = mjViceFata.getMfFatatype();
//        this.mjOrderfile = mjViceFata.getMjOrderfile();
//        this.mjParsedate = mjViceFata.getMjParsedate();
//        this.mjEndtime = mjViceFata.getMjEndtime();
//        this.mjEndoktime = mjViceFata.getMjEndoktime();
//        this.mjNum = mjViceFata.getMjNum();
//        this.mjDate = mjViceFata.getMjDate();
//        this.mjLevel = mjViceFata.getMjLevel();
//        this.mjSequencenumber = mjViceFata.getMjSequencenumber();
//        this.mjIdentityings = mjViceFata.getMjIdentityings();
//        this.mjRemark = mjViceFata.getMjRemark();
//        this.createUserId = mjViceFata.getCreateUserId();
//        this.createTime = mjViceFata.getCreateTime();
//        this.updateUserId = mjViceFata.getUpdateUserId();
//        this.updateTime = mjViceFata.getUpdateTime();
//    }
}
