package com.fullkos.dish.api.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.MapKeyClass;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullkos.dish.api.dto.GPTResponse;
import com.fullkos.dish.db.dto.GPTRequest;
import com.fullkos.dish.db.entity.News;
import com.fullkos.dish.api.Service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

	final NewsService newsService;

	private final RestTemplate restTemplate;

	@GetMapping("/company/{company_id}")
	public List<News> getNews(@PathVariable Long company_id) {
		return newsService.getNews(company_id);
	}

	@GetMapping("/gpt")
	public Map<String, Object> getNews(@RequestParam Map<String, String> params) throws JsonProcessingException {

		String question = params.get("question");
		System.out.println("question = " + question);

		ZoneId koreaZone = ZoneId.of("Asia/Seoul");
		ZonedDateTime koreaTime = ZonedDateTime.now(koreaZone);



		String prompt = new ArrayList<String>() {{
			add("Today is: " + koreaTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")));
			add("You are provided with a question.");
			add("Use these questions to get the start and end dates you need.");
			add("Q: " + question);
			add("The response must be JSON only. not in markdown format.");
			add("""
				example result:
					{
					"startDate": [java.time.LocalDateTime],
					"endDate": [java.time.LocalDateTime]
					}
				""");
		}}.toString();

		GPTRequest request = new GPTRequest(
			"gpt-4o", prompt, 1, 4096, 1, 2, 2);

		GPTResponse gptResponse = restTemplate.postForObject(
			"https://api.openai.com/v1/chat/completions"
			, request
			, GPTResponse.class
		);

		String content = gptResponse.getChoices().get(0).getMessage().getContent();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(content, new TypeReference<Map<String, String>>() {
		});

		System.out.println("date content = " + content);

		LocalDateTime startDate = LocalDateTime.parse(map.get("startDate"));
		LocalDateTime endDate = LocalDateTime.parse(map.get("endDate"));


		Map<String, Object> news = new HashMap<>();

		for (News n : newsService.getNews(100653L, startDate, endDate)) {
			Map<String, Object> newsMap = new HashMap<>();
			newsMap.put("title", n.getTitle());
			newsMap.put("content", n.getContent());
			newsMap.put("url", n.getUrl());
			newsMap.put("sentiment", n.getSentiment());
			newsMap.put("date", n.getDate());
			news.put(n.getId().toString(), newsMap);
		}

		String prompt2 = new ArrayList<String>() {{
			add("The user will ask a question about current events or news topics.");
			add("You will be provided with the content of a relevant news article or report.");
			add("Craft a concise and informative response that directly answers the user's question based on the provided news content. Do not use markdown formatting in your response.");
			add("Ensure your answer is factual and objective, avoiding personal opinions or biases. Stick to the information presented in the news content.");
			add("If the provided news content doesn't fully answer the user's question, acknowledge this and explain what information is available based on the given content.");
			add("Use clear and simple language to make the information accessible to a wide audience.");
			add("If there are any technical terms or complex concepts in the news content, briefly explain them in layman's terms.");
			add("Conclude your response by suggesting a follow-up question or area for further exploration related to the topic, if appropriate.");
			add("Answer in korean");
			add("Please respond only using the information in News I provided");
			add("News: " + news);
			add("User question: " + question);
		}}.toString();

		GPTRequest request2 = new GPTRequest(
			"gpt-4o", prompt2, 1, 4096, 1, 2, 2);

		GPTResponse gptResponse2 = restTemplate.postForObject(
			"https://api.openai.com/v1/chat/completions"
			, request2
			, GPTResponse.class
		);

		String content2 = gptResponse2.getChoices().get(0).getMessage().getContent();
		Map<String, Object> map2 = new HashMap<>();
		map2.put("answer", content2);

		return map2;
	}
}
