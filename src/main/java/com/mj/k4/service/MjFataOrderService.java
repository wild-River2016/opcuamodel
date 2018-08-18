package com.mj.k4.service;

import com.mj.k4.dto.SumFataNumDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/7/24
 * Annotation:
 */
public interface MjFataOrderService {
    List<SumFataNumDTO> sumFataNum();

    void exclSaveOrder(String path);
}
