package com.fullkos.dish.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_type", columnDefinition = "TINYINT(1)")
    private Boolean orderType;
    private Integer amount;
    private String buyer;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company comapnyInTrading;

}