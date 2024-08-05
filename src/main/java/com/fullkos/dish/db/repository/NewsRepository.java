package com.fullkos.dish.db.repository;

import java.util.List;

import com.fullkos.dish.db.entity.Company;
import com.fullkos.dish.db.entity.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	List<News> findByCompanyId(Long companyId);

}
