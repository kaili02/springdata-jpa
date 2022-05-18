package com.pgh.spring_data_jpa0414.service.impl;

import com.pgh.spring_data_jpa0414.entity.User;
import com.pgh.spring_data_jpa0414.repository.UserDao;
import com.pgh.spring_data_jpa0414.service.IUserService;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.annotations.Recurring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    @Transactional(propagation = Propagation.REQUIRED)
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
    @Transactional
    public User findById(Long userId) {
        String name = "test13";
        String desc = "desc13";
        User test2 = userDao.findByNameAndDesc(name, desc);
        System.out.println("test2:"+test2.toString());
        Optional<User> byId = userDao.findById(userId);
        return byId.isPresent() ? byId.get() : null;
    }

    @Recurring(id = "my-recurring-job", cron = "*/5 * * * * *")
    @Job(name = "My recurring job")
    public void job () {

        System.out.println("my job excute.");
    }
}
