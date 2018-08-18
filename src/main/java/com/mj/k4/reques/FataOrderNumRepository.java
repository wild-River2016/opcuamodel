package com.mj.k4.reques;

import com.mj.k4.domain.FataOrderNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/2/24
 * Annotation:订单生产数量的Repository
 */
@Repository
public interface FataOrderNumRepository extends JpaRepository<FataOrderNum, Integer>, JpaSpecificationExecutor {

    /**
     * 根据开始时间来模糊查询
     *
     * @param fonTime
     * @return
     */
    @Query(value = "SELECT * FROM `fata_order_num` WHERE fon_time LIKE ? ", nativeQuery = true)
    List<FataOrderNum> findAllFataOrderNumfonTime(@Param("fonTime") String fonTime);

    /**
     * 根据id查询订单数量
     *
     * @param fonid
     * @return
     */
    FataOrderNum findByfonid(Integer fonid);

    /**
     * 根据订单编号查询订单数量
     *
     * @param fonName
     * @return
     */
    FataOrderNum findByfonName(String fonName);

    /**
     * 查询订单数量最后一个 以创建时间来排序
     *
     * @return
     */
    @Query(value = "SELECT * FROM `fata_order_num` ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
    FataOrderNum findAllFataOrderNumEnd();

    /**
     * 修改订单数量的数据 主要修改下线数 年，月，日产量
     *
     * @param fonEndNum
     * @param fonYearNum
     * @param fonMonthNum
     * @param fonDayNum
     * @param fonEndTime
     * @param updateTime
     * @param fonid
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_end_num=:fonEndNum ,fon_year_num=:fonYearNum ,fon_month_num=:fonMonthNum,fon_day_num=:fonDayNum,fon_end_time=:fonEndTime ,update_time=:updateTime,delayed_indentify=:delayedIndentify WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrderNum(@Param("fonEndNum") Integer fonEndNum, @Param("fonYearNum") Integer fonYearNum, @Param("fonMonthNum") Integer fonMonthNum, @Param("fonDayNum") Integer fonDayNum, @Param("fonEndTime") Timestamp fonEndTime, @Param("updateTime") Timestamp updateTime, @Param("delayedIndentify") Integer delayedIndentify, @Param("fonid") Integer fonid);


    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_go_num=:fonGoNum ,fon_end_num=:fonEndNum ,fon_nok_num=:fonNokNum,fon_year_num=:fonYearNum,fon_month_num=:fonMonthNum ,fon_day_num=:fonDayNum,fon_time=:fonTime,fon_end_time=:fonEndTime,delayed_indentify=:delayedIndentify WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrder(@Param("fonGoNum") Integer fonGoNum, @Param("fonEndNum") Integer fonEndNum, @Param("fonNokNum") Integer fonNokNum, @Param("fonYearNum") Integer fonYearNum, @Param("fonMonthNum") Integer fonMonthNum, @Param("fonDayNum") Integer fonDayNum, @Param("fonTime") Timestamp fonTime, @Param("fonEndTime") Timestamp fonEndTime, @Param("delayedIndentify") Integer delayedIndentify, @Param("fonid") Integer fonid);

    /**
     * 修改订单数量的不合格数
     *
     * @param fonNokNum
     * @param fonid
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_nok_num=:fonNokNum WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrderNumfonNokNum(@Param("fonNokNum") Integer fonNokNum, @Param("fonid") Integer fonid);


    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_nok_num=:fonNokNum,fon_end_num=:fonEndNum WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrderNumfonNokNumAndOk(@Param("fonNokNum") Integer fonNokNum, @Param("fonEndNum") Integer fonEndNum, @Param("fonid") Integer fonid);

    /**
     * 修改订单数量的开始数
     *
     * @param fonGoNum
     * @param fonid
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_go_num=:fonGoNum WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrderNumfonGoNum(@Param("fonGoNum") Integer fonGoNum, @Param("fonid") Integer fonid);

    /**
     * 修改订单的开始结束的时间（订单的上线时间，我们可以看到是多少结束的）
     *
     * @param fonTime
     * @param fonid
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE fata_order_num SET fon_time=:fonTime WHERE fonid=:fonid", nativeQuery = true)
    Integer updateFataOrderNumfonTime(@Param("fonTime") Timestamp fonTime, @Param("fonid") Integer fonid);


    /**
     * 删除关联的fata数据
     *
     * @param fonid
     * @param fonid
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM fata_order_num WHERE mj_fata_fata_mfid=:fonid", nativeQuery = true)
    Integer deleteFataOrderNumfonTime(@Param("fonid") Integer fonid);
}
