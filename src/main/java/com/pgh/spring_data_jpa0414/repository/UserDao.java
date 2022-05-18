package com.pgh.spring_data_jpa0414.repository;

import com.pgh.spring_data_jpa0414.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByName(String name);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByNameAndDesc(String name, String desc);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findById(Long id);
}