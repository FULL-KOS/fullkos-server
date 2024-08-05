package com.fullkos.dish.db.repository;

import com.fullkos.dish.db.dto.PortfolioAndCompanyDtoInterface;
import com.fullkos.dish.db.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    @Query(value = """ 
            SELECT c.id as companyId, c.company_name as companyName, p.amount as amount, c.company_code as companyCode, c.industry as industry
            FROM portfolio as p
            INNER JOIN company as c
                ON p.company_id = c.id
            WHERE p.user_id = :id
            ORDER BY p.amount DESC
            """, nativeQuery = true)
    List<PortfolioAndCompanyDtoInterface> findAllByUserId(Long id);
}
