package com.mj.k4.web;

import com.mj.common.util.time.ResultVM;
import com.mj.k4.service.MjFataOrderService;
import com.mj.k4.util.Ex2PDF;
import com.mj.k4.util.PdfToImagePdfBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.mj.k4.util.Ex2PDF.convert2PDF;
import static com.mj.k4.util.PdfToImagePdfBox.pdfToImagePath;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/7/24
 * Annotation:
 */
@RestController
@RequestMapping(value = "/management")
public class MjFataOrderController {
    @Autowired
    MjFataOrderService mjFataOrderService;

    @RequestMapping(value = "/sumFataNum", method = RequestMethod.GET)
    public ResultVM sumFataNum() {
        return ResultVM.ok(mjFataOrderService.sumFataNum());
    }

    @RequestMapping(value = "/excl", method = RequestMethod.GET)
    public ResultVM exclSaveOrder() {
//        这是把excl转成PDF
        int time = convert2PDF("C:\\Users\\tang\\Desktop\\Funai-T24J50BCSDWA00-mozu-0310.xls", "C:\\Users\\tang\\Desktop\\test.pdf");
        if (time == -4) {
            System.out.println("转化失败，未知错误...");
        } else if (time == -3) {
            System.out.println("原文件就是PDF文件,无需转化...");
        } else if (time == -2) {
            System.out.println("转化失败，文件不存在...");
        } else if (time == -1) {
            System.out.println("转化失败，请重新尝试...");
        } else if (time < -4) {
            System.out.println("转化失败，请重新尝试...");
        } else {
            System.out.println("转化成功，用时：  " + time + "s...");
        }
        //这是pdf转成jpg
        String filePath = "C:\\Users\\tang\\Desktop\\test.pdf";
        List<String> imageList = pdfToImagePath(filePath);
        Iterator<String> iterator = imageList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return ResultVM.ok();
    }
}
