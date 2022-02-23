package com.pgh.spring_data_jpa0414.repository;


import com.pgh.spring_data_jpa0414.entity.CommonShowFieldBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pgh
 * @data 2021/4/21 17:50
 */
@Repository
public interface CommonShowFieldBeanRepository extends JpaRepository<CommonShowFieldBean,Integer >
        , JpaSpecificationExecutor<CommonShowFieldBean> {

    @Query(value = "select * from common_show_field where type= ? ",nativeQuery = true)
    List<CommonShowFieldBean> findByType( String type);

}
