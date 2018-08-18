package com.mj.k4.util;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/3/1
 * Annotation:
 */
public class StringUtil {

    public static final Integer IDETITYSINegativeOne = -1;
    public static final Integer IDETITYSIZero = 0;
    public static final Integer IDETITYSITY = 1;
    public static final Integer IDETITYSITWo = 2;
    public static final Integer IDETITYSIThree = 3;
    public static final Integer IDETITYSIFour = 4;
    public static final Integer IDETITYSIFive = 5;
    public static final Integer IDETITYSISix = 6;
    public static final Integer IDETITYSISeven = 7;
    public static final Integer IDETITYSIEight = 8;
    public static final Integer IDETITYSITen = 10;
    public static final Integer IDETITYSIEleven = 11;
    public static final Integer IDETITYSITwenty = 20;
    public static final Integer IDETITYSITwentyOne = 21;
    public static final Integer IDETITYSITwentyThree = 23;
    public static final Integer IDETITYSITwentyFour = 24;
    public static final Integer IDETITYSITwentySix = 26;


    public static Boolean isNotBlank(String value) {
        if (value == null)
            return false;
        else if (value.equals("") || value.isEmpty())
            return false;
        else
            return true;
    }

//    public static List<Predicate> JpaPageing(PagingDTO pagingDTO, Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//        List<Predicate> list = new ArrayList<>();
//        if (StringUtil.isNotBlank(pagingDTO.getLineNo()))
//            list.add(cb.equal(root.get(pagingDTO.getClassName()).as(String.class), pagingDTO.getLineNo()));
//        if (StringUtil.isNotBlank(pagingDTO.getLineName()))
//            list.add(cb.equal(root.get(pagingDTO.getClassNum()).as(String.class), pagingDTO.getLineName()));
//
//        List<String> lineTypeList = pagingDTO.getLineTypeList();
//        if (lineTypeList != null && lineTypeList.size() > 0) {
//            CriteriaBuilder.In<String> in = cb.in(root.<String>get(pagingDTO.getLineName()));
//            for (String lineType : lineTypeList)
//                in.value(lineType);
//            list.add(in);
//        }
//        List<String> lineStateList = pagingDTO.getLineSateList();
//        if (lineStateList != null && lineStateList.size() > 0) {
//            CriteriaBuilder.In<String> in = cb.in(root.<String>get(pagingDTO.getListClassNum()));
//            for (String lineState : lineStateList)
//                in.value(lineState);
//            list.add(in);
//        }
//        return list;
//    }
}
