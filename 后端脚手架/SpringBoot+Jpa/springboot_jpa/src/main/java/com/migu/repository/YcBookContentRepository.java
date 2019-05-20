package com.migu.repository;

import com.migu.domain.YcBookContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * YcBookRepository
 * hasee
 * 2018/12/20
 * 18:27
 *
 * @Version 1.0
 **/

public interface YcBookContentRepository extends JpaRepository<YcBookContent,Integer>,JpaSpecificationExecutor<YcBookContent>{



}
