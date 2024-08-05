package com.fullkos.dish.api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fullkos.dish.db.entity.News;
import com.fullkos.dish.db.entity.User;
import com.fullkos.dish.db.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {

	final NewsRepository newsRepository;

	public List<News> getNews(Long company_id) {
		return newsRepository.findByCompanyId(company_id);
	}
}
