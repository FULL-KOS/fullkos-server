package com.fullkos.dish.db.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.fullkos.dish.db.entity.Company;
import com.fullkos.dish.db.entity.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	List<News> findByCompanyId(Long companyId);

	@Query("SELECT n FROM News n WHERE n.company.id = :companyId AND n.date BETWEEN :start AND :end")
	List<News> findByCompanyIdAndDateBetween(Long companyId, LocalDateTime start, LocalDateTime end);

}
