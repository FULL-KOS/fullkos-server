package com.fullkos.dish.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullkos.dish.db.entity.News;
import com.fullkos.dish.api.Service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

	final NewsService newsService;

	@GetMapping("/company/{company_id}")
	public List<News> getNews(@PathVariable Long company_id) {
		return newsService.getNews(company_id);
	}
}
