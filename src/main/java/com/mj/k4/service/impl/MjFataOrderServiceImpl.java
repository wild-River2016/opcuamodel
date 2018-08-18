package com.mj.k4.service.impl;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.mj.k4.config.websocket.WebSocketService;
import com.mj.k4.domain.MjFataFata;
import com.mj.k4.dto.StationDBDTO;
import com.mj.k4.dto.SumFataNumDTO;
import com.mj.k4.reques.FataOrderNumRepository;
import com.mj.k4.reques.MjFataFataRepository;
import com.mj.k4.service.MjFataOrderService;
import com.mj.k4.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/7/24
 * Annotation:
 */
@Slf4j
@Service
public class MjFataOrderServiceImpl implements MjFataOrderService {

    @Autowired
    FataOrderNumRepository fataOrderNumRepository;

    @Autowired
    MjFataFataRepository mjFataFataRepository;
    @Autowired
    WebSocketService webSocketService;
    @Autowired
    RedisUtil redisUtil;//redis缓存

    private static String dateString = "";

    @Override
    public List<SumFataNumDTO> sumFataNum() {
        List<MjFataFata> mjFataFata = null;
        List<SumFataNumDTO> sumFataNumDTO = null;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String d = sdf.format(new Date());
            if (dateString.equals("") && !dateString.equals(d)) {
                dateString = d;
                sumFataNumDTO = new ArrayList<>();
                for (int i = 10; i > 0; i--) {
                    Date date = sdf.parse(d);
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, -i);
                    String dates = sdf.format(calendar.getTime());
                    mjFataFata = mjFataFataRepository.SumFataNumOneDay(dates + " %");
                    SumFataNumDTO s = new SumFataNumDTO(dates);
                    for (MjFataFata m : mjFataFata) {
                        s.setSumNum(s.getSumNum() + m.getMjNum());
                        if (m.getFataOrderNum() != null) {
                            s.setSumGoNum(s.getSumGoNum() + m.getFataOrderNum().getFonGoNum());
                            s.setSumEndNum(s.getSumEndNum() + m.getFataOrderNum().getFonEndNum());
                            s.setSumNoOk(s.getSumNoOk() + m.getFataOrderNum().getFonNokNum());
                        }
                    }
                    sumFataNumDTO.add(s);
                }
                redisUtil.set("sumFataNum", sumFataNumDTO);
            } else {
                sumFataNumDTO = new ArrayList<>((List<SumFataNumDTO>) redisUtil.get("sumFataNum"));
                mjFataFata = mjFataFataRepository.SumFataNumOneDay(d + " %");
                SumFataNumDTO s = new SumFataNumDTO(d);
                for (MjFataFata m : mjFataFata) {
                    s.setSumNum(s.getSumNum() + m.getMjNum());
                    if (m.getFataOrderNum() != null) {
                        s.setSumEndNum(s.getSumEndNum() + m.getFataOrderNum().getFonEndNum());
                        s.setSumGoNum(s.getSumGoNum() + m.getFataOrderNum().getFonGoNum());
                        s.setSumNoOk(s.getSumNoOk() + m.getFataOrderNum().getFonNokNum());
                    }
                }
                sumFataNumDTO.add(s);
            }
            webSocketService.sendInfo("MainBoard", StationDBDTO.ROBOT);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sumFataNumDTO;
    }

    @Override
    public void exclSaveOrder(String path) {

    }
}
