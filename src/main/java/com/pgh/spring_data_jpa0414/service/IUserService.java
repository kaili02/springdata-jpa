package com.pgh.spring_data_jpa0414.service;

import com.pgh.spring_data_jpa0414.entity.User;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
public interface IUserService {
    User save(User user);
    User findById(Long userId);
}
