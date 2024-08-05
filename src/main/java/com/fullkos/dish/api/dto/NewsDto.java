package com.fullkos.dish.api.dto;

import java.io.Serializable;


import lombok.Value;

@Value
public class NewsDto implements Serializable {
	Long id;
	Long companyId;
	String title;
	String content;
	String url;
	String sentiment;
}