package com.pgh.spring_data_jpa0414.service.impl;

import com.pgh.spring_data_jpa0414.entity.User;
import com.pgh.spring_data_jpa0414.repository.UserDao;
import com.pgh.spring_data_jpa0414.service.IUserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    public User save(User user) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        User save = null;
        try {
            save = userDao.save(user);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }
        entityManager.clear(); // clear session cache.next query from db
        save = findById(save.getId());
        System.out.println("getUpdateDt2: "+save.getUpdateDt());
        return save;
    }

    @Override
    public User findById(Long userId) {
        Optional<User> byId = userDao.findById(userId);
        return byId.isPresent() ? byId.get() : null;
    }
}
