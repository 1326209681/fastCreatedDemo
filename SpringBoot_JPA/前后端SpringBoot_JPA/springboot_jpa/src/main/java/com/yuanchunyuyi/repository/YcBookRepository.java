package com.yuanchunyuyi.repository;

import com.yuanchunyuyi.domain.YcBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * YcBookRepository
 * hasee
 * 2018/12/20
 * 18:27
 *
 * @Version 1.0
 **/

public interface YcBookRepository extends JpaRepository<YcBook,Integer> {
   /* public List<YcBook> findAll();*/
}
