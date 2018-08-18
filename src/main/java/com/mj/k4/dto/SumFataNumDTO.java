package com.mj.k4.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/7/24
 * Annotation:
 */
@Data
public class SumFataNumDTO implements Serializable {

    private String sumDate;//时间
    private Integer sumNum =0;//总数
    private Integer sumNoOk =0;//不合格数
    private Integer sumGoNum =0;//上线数
    private Integer sumEndNum =0;//下线数

    public SumFataNumDTO() {
    }

    public SumFataNumDTO(String sumDate) {
        this.sumDate = sumDate;
    }
}
