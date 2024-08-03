package com.fullkos.dish.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_code")
    private int companyCode;
    @Column(name = "company_name")
    private String companyName;
    private String industry;
    @Column(name = "stock_code")
    private Integer stockCode;

    @OneToMany(mappedBy = "comapnyInTrading", fetch = FetchType.LAZY)
    private List<Trading> companyTradingList = new ArrayList<>();
    @OneToMany(mappedBy = "companyInMajorTrading", fetch = FetchType.LAZY)
    private List<MajorTrading> companyMajorTradingList = new ArrayList<>();
}