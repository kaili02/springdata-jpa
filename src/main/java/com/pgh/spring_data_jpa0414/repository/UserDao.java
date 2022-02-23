package com.pgh.spring_data_jpa0414.repository;

import com.pgh.spring_data_jpa0414.entity.SStudent;
import com.pgh.spring_data_jpa0414.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

}