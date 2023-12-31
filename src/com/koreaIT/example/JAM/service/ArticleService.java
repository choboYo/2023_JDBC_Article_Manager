package com.koreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.koreaIT.example.JAM.dao.ArticleDao;
import com.koreaIT.example.JAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int doWrite(int memberId, String title, String body) {
		return articleDao.doWrite(memberId, title, body);
	}

	public List<Article> showList(String searchKeyword) {
		
		List<Map<String, Object>> articleListMap = articleDao.showList(searchKeyword);
		
		List<Article> articles = new ArrayList<>();
		
		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public Article showDetail(int id) {
		Map<String, Object> articleMap = articleDao.showDetail(id);
		
		if (articleMap.isEmpty()) {
			return null;
		}
		
		return new Article(articleMap);
	}

	public int getNumInCmd(String cmd) {
		return Integer.parseInt(cmd.split(" ")[2]);
	}

	public int getArticleCnt(int id) {
		return articleDao.getArticleCnt(id);
	}

	public void doModify(String title, String body, int id) {
		articleDao.doModify(title, body, id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public Article getArticleById(int id) {
		
		Map<String, Object> articleMap = articleDao.getArticleById(id);
				
		if (articleMap.isEmpty()) {
			return null;
		}
		
		return new Article(articleMap);
	}
}