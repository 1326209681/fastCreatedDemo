package com.itheima.repository;

import com.itheima.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserRepository
 * hasee
 * 2018/12/20
 * 18:27
 *
 * @Version 1.0
 **/

public interface UserRepository  extends JpaRepository<User,Long> {
    public List<User> findAll();
}
