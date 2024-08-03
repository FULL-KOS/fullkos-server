package com.fullkos.dish.db.repository;

import com.fullkos.dish.db.dto.BuySellDtoInterface;
import com.fullkos.dish.db.dto.VolumeDto;
import com.fullkos.dish.db.dto.VolumeDtoInterface;
import com.fullkos.dish.db.entity.Trading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingRepository extends JpaRepository<Trading, Long> {

    @Query(value = """
            SELECT c.id as companyId, c.company_name as companyName, SUM(ABS(t.amount)) as total
                FROM trading as t
                INNER JOIN company as c
                    ON c.id = t.company_id
                WHERE DATE_SUB(CURDATE(), INTERVAL '3' MONTH) < t.DATE
                    AND c.industry = :industry
                GROUP BY c.id
                ORDER BY total DESC
                LIMIT 10
            """, nativeQuery = true)
    List<VolumeDtoInterface> findTop10VolumeByIndustry(String industry);

    @Query(value = """
        SELECT c.id as companyId, c.company_name as companyName, SUM(if(t.order_type = 0, t.amount, 0)) as buyTotal, SUM(if(t.order_type = 1, t.amount, 0)) as sellTotal
            FROM trading as t
            INNER JOIN company as c
                ON c.id = t.company_id
            WHERE DATE_SUB(CURDATE(), INTERVAL '3' MONTH) < t.DATE
                AND c.industry = :industry
            GROUP BY c.id
            ORDER BY buyTotal + ABS(sellTotal) DESC
            LIMIT 10;
        """, nativeQuery = true)
    List<BuySellDtoInterface> findTop10BuySellByIndustry(String industry);
}
