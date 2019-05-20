package com.yuanchunyuyi.repository;

import com.yuanchunyuyi.domain.YcBook;
import com.yuanchunyuyi.domain.YcBookContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
