package com.mj.k4.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/2/5
 * Annotation: 定时任务
 */
//@Component
@Slf4j
public class Timings {

    /**
     * *第一位，表示秒，取值0-59
     * 第二位，表示分，取值0-59
     * 第三位，表示小时，取值0-23
     * 第四位，日期天/日，取值1-31
     * 第五位，日期月份，取值1-12
     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
     * 另外：1表示星期天，2表示星期一。
     * 第7为，年份，可以留空，取值1970-2099
     */
//    @Scheduled(cron = "0 0 0 1 * ?")
    public void TimingSymbol() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00");
        String d = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String dates = sdf.format(c.getTime());
        System.out.println(dates);
        log.info("我是自动执行的代码现在时间是=" + dates);
    }

//    @Scheduled(cron = "0/9 * * * * ?")
//    public void TimingSymbol1() throws InterruptedException {
//        System.out.println("我是第二个");
//        Thread.sleep(8000);
//        Iterator iterator = mapModbus.getMapOne().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//            if (!String.valueOf(entry.getValue()).equals(String.valueOf(mapModbus.getCopyMapOne().get(entry.getKey())))) {
//                System.out.println("不行同的" + entry.getKey());
//                mapModbus.setMapNumOneCopy((Integer) entry.getKey(), (Number) entry.getValue());
//            }
//        }
//    }

}
