package com.mj.k4.reques;

import com.mj.k4.domain.MjFataFata;
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
 * Date：2018/2/7
 * Annotation:解析零件定义Repository
 */
@Repository
public interface MjFataFataRepository extends JpaRepository<MjFataFata, Integer>, JpaSpecificationExecutor {

//    @Query(value = "SELECT * FROM mj_fata_fata fata LEFT JOIN fata_order_num fnum ON fata.mfid=fnum.mj_fata_fata_mfid WHERE mj_end_time > :date and mj_end_time < :datas", nativeQuery = true)
//    List<MjFataFata> SumFataNumDTO(@Param("date") String date,@Param("dates") String datas);

    @Query(value = "SELECT * FROM mj_fata_fata fata LEFT JOIN fata_order_num fnum ON fata.mfid=fnum.mj_fata_fata_mfid WHERE mj_end_time like :date", nativeQuery = true)
    List<MjFataFata> SumFataNumOneDay(@Param("date") String date);
}
