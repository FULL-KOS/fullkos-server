package com.fullkos.dish.api.dto;

import lombok.Data;

@Data
public class MonthlyCompanyTradingDto {
	private Long total;
	private int month;
	private boolean orderType;

	public MonthlyCompanyTradingDto(Long total, int month, boolean orderType) {
		this.total = total;
		this.month = month;
		this.orderType = orderType;
	}
}
