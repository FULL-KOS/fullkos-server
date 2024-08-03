package com.fullkos.dish.db.repository;

import com.fullkos.dish.db.entity.Trading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradingRepository extends JpaRepository<Trading, Long> {
}
